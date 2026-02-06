package com.example.converteraudio.controller;

import com.example.converteraudio.dto.OrderDto;
import com.example.converteraudio.model.Order;
import com.example.converteraudio.model.User;
import com.example.converteraudio.service.OrderService;
import com.example.converteraudio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDto dto, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        Order order = Order.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .user(user)
                .build();
        Order saved = orderService.create(order);
        dto.setId(saved.getId());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> list(Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        List<OrderDto> list = orderService.findByUser(user).stream().map(o -> {
            OrderDto d = new OrderDto();
            d.setId(o.getId());
            d.setAmount(o.getAmount());
            d.setDescription(o.getDescription());
            return d;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        var opt = orderService.findById(id).filter(o -> o.getUser().getId().equals(user.getId()));
        if (opt.isPresent()) {
            Order o = opt.get();
            OrderDto d = new OrderDto();
            d.setId(o.getId());
            d.setAmount(o.getAmount());
            d.setDescription(o.getDescription());
            return ResponseEntity.ok(d);
        }
        return ResponseEntity.status(404).body("Not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderDto dto, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        var opt = orderService.findById(id).filter(o -> o.getUser().getId().equals(user.getId()));
        if (opt.isPresent()) {
            Order o = opt.get();
            o.setDescription(dto.getDescription());
            o.setAmount(dto.getAmount());
            orderService.update(o);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(404).body("Not found or not allowed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        var opt = orderService.findById(id).filter(o -> o.getUser().getId().equals(user.getId()));
        if (opt.isPresent()) {
            orderService.delete(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(404).body("Not found or not allowed");
    }
}
