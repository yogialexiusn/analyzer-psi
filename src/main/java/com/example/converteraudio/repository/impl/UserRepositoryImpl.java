package com.example.converteraudio.repository.impl;


import com.example.converteraudio.model.User;
import com.example.converteraudio.repository.UserRepository;
import com.google.cloud.firestore.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Firestore firestore;

    private static final String COLLECTION = "users";

    @Override
    public User save(User user) {

        try {
            firestore.collection(COLLECTION)
                    .document(user.getId())
                    .set(user)
                    .get();

            return user;

        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public User findById(String id) {

        try {
            DocumentSnapshot snapshot = firestore.collection(COLLECTION)
                    .document(id)
                    .get()
                    .get();

            return snapshot.exists() ? snapshot.toObject(User.class) : null;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user", e);
        }
    }

    @Override
    public User findByEmail(String email) {

        try {
            QuerySnapshot snapshot = firestore.collection(COLLECTION)
                    .whereEqualTo("email", email)
                    .limit(1)
                    .get()
                    .get();

            if (snapshot.isEmpty()) {
                return null;
            }

            return snapshot.getDocuments()
                    .get(0)
                    .toObject(User.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user by email", e);
        }
    }

    @Override
    public List<User> findAll() {

        try {
            QuerySnapshot snapshot = firestore.collection(COLLECTION)
                    .get()
                    .get();

            return snapshot.getDocuments()
                    .stream()
                    .map(doc -> doc.toObject(User.class))
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    @Override
    public void deleteById(String id) {

        try {
            firestore.collection(COLLECTION)
                    .document(id)
                    .delete()
                    .get();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}
