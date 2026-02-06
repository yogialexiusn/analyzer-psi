package com.example.converteraudio.repository;

import com.example.converteraudio.model.Order;
import com.example.converteraudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
