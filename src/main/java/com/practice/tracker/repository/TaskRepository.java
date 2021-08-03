package com.practice.tracker.repository;

import com.practice.tracker.entity.Task;
import com.practice.tracker.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
  List<Task> findAllByUser(User user);
  Task findById(long id);
}
