package com.practice.tracker.service;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.Task;

import java.util.List;
import java.util.Optional;

public interface CardService {
  List<Card> findAllByTask(Task task);
  Optional<Card> findById(long id);
  Card save(Card card);
  void deleteById(long id);
}
