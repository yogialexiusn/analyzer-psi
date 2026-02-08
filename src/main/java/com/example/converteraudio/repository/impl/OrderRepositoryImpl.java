package com.example.converteraudio.repository.impl;

import com.example.converteraudio.model.Order;
import com.example.converteraudio.repository.OrderRepository;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final Firestore firestore;
    private static final String COLLECTION = "orders";

    @Override
    public Order save(Order order) throws Exception {
        firestore.collection(COLLECTION)
                .document(order.getId())
                .set(order)
                .get();
        return order;
    }

    @Override
    public Order findById(String id) throws Exception {
        DocumentSnapshot snapshot = firestore.collection(COLLECTION)
                .document(id)
                .get()
                .get();

        return snapshot.exists() ? snapshot.toObject(Order.class) : null;
    }

    @Override
    public List<Order> findByUserId(String userId) throws Exception {
        return firestore.collection(COLLECTION)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Order.class))
                .toList();
    }

    @Override
    public void deleteById(String id) throws Exception {
        firestore.collection(COLLECTION)
                .document(id)
                .delete()
                .get();
    }
}
