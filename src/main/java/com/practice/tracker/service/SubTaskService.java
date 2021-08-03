package com.practice.tracker.service;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.SubTask;

import java.util.List;
import java.util.Optional;

public interface SubTaskService {
  List<SubTask> findAllByCard(Card card);
  Optional<SubTask> findById(long id);
  SubTask save(SubTask subTask);
  void deleteById(long id);
  void changeStatus(long id);
}
