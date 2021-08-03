package com.practice.tracker.repository;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUserName(String userName);
  User findById(long id);
}