package com.practice.tracker.service;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.SubTask;
import com.practice.tracker.repository.CardRepository;
import com.practice.tracker.repository.SubTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubTaskServiceImpl implements SubTaskService {
  private SubTaskRepository subTaskRepository;
  private CardRepository cardRepository;

  public SubTaskServiceImpl(SubTaskRepository subTaskRepository, CardRepository cardRepository){
    this.subTaskRepository = subTaskRepository;
    this.cardRepository = cardRepository;
  }

  @Override
  public List<SubTask> findAllByCard(Card card){
    return subTaskRepository.findAllByCard(card);
  }

  @Override
  public Optional<SubTask> findById(long id){
    return Optional.ofNullable(subTaskRepository.findById(id));
  }

  @Override
  public SubTask save(SubTask subTask) {
    subTask.setName(subTask.getName().trim());
    if(!subTask.getName().equals("")) {
      return subTaskRepository.save(subTask);
    }
    return subTask;
  }

  @Override
  public void deleteById(long id) {
    subTaskRepository.deleteById(id);
  }

  @Override
  public void changeStatus(long id) {
    SubTask subTask = subTaskRepository.findById(id);
    subTask.setDone(!subTask.isDone());
    subTaskRepository.save(subTask);
  }
}
