package com.practice.tracker.repository;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
  List<Card> findAllByTask(Task task);
  Card findById(long id);
}