package com.practice.tracker.service;

import com.practice.tracker.entity.Task;
import com.practice.tracker.entity.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {
  List<Task> findAllByUser(User user);
  Optional<Task> findById(long id);
  Task save(Task task);
  void deleteById(long id);
}
