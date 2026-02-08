package com.example.converteraudio.service;


import com.example.converteraudio.exception.ResourceNotFoundException;
import com.example.converteraudio.model.Order;
import com.example.converteraudio.repository.OrderRepository;
import com.google.cloud.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(Order order) throws Exception {
        if (order.getUserId() == null || order.getUserId().isBlank()) {
            throw new IllegalArgumentException("UserId is required");
        }
        order.setId(UUID.randomUUID().toString());
        order.setCreatedAt(Timestamp.now());
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }

    public Order getById(String id) throws Exception {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return order;
    }

    public List<Order> getByUserId(String userId) throws Exception {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("UserId is required");
        }
        return orderRepository.findByUserId(userId);
    }

    public Order updateStatus(String id, String newStatus) throws Exception {
        Order order = getById(id);
        if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void delete(String id) throws Exception {
        Order order = getById(id); // memastikan order ada
        orderRepository.deleteById(order.getId());
    }
}
