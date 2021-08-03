package com.practice.tracker.service;

import com.practice.tracker.entity.User;

import java.util.Optional;

public interface UserService {
  User findUser(String userName);
  Optional<User> findById(long id);
  User save(User user);
  void deleteById(long id);
}
