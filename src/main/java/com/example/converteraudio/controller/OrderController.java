package com.example.converteraudio.controller;

import com.example.converteraudio.dto.OrderRequest;
import com.example.converteraudio.model.Order;
import com.example.converteraudio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequest order) throws Exception {
        Order created = orderService.create(order);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id) throws Exception {
        Order order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable String userId) throws Exception {
        List<Order> orders = orderService.getByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable String id,
            @RequestParam String status
    ) throws Exception {
        Order updated = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
