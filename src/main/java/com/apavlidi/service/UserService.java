package com.apavlidi.service;

import com.apavlidi.api.exceptions.DataNotFoundException;
import com.apavlidi.domain.User;
import com.apavlidi.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private EntityManager entityManager;

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public User findUserById(Long noteId) {
        Optional<User> userById = userRepository.findUserById(noteId);
        if (userById.isPresent()) {
            return userById.get();
        } else {
            throw new DataNotFoundException("User with id:" + userById + " not found");
        }
    }

    public void updateUserById(Long noteId, User newUser) {
        Optional<User> userById = userRepository.findUserById(noteId);
        if (userById.isPresent()) {
            newUser.setUserId(userById.get().getUserId());
            persist(newUser);
        } else {
            throw new DataNotFoundException("User with id:" + userById + " not found");
        }
    }

    public void deleteUserById(Long noteId) {
        Optional<User> userById = userRepository.findUserById(noteId);
        if (userById.isPresent()) {
            entityManager.remove(userById.get());
        } else {
            throw new DataNotFoundException("User with id:" + userById + " not found");
        }
    }

    public void persist(User user) {
        userRepository.persist(user);
    }

}
