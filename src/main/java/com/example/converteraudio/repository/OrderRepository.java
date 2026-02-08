package com.example.converteraudio.repository;

import com.example.converteraudio.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order) throws Exception;

    Order findById(String id) throws Exception;

    List<Order> findByUserId(String userId) throws Exception;

    void deleteById(String id) throws Exception;
}
