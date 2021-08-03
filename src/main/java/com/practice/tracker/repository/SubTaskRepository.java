package com.practice.tracker.repository;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.SubTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends CrudRepository<SubTask, Long> {
  List<SubTask> findAllByCard(Card card);
  SubTask findById(long id);
}