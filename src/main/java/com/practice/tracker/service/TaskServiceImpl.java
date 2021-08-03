package com.practice.tracker.service;

import com.practice.tracker.entity.Task;
import com.practice.tracker.entity.User;
import com.practice.tracker.exception.ResourceNotFoundException;
import com.practice.tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
  private TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository){
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> findAllByUser(User user){
    return taskRepository.findAllByUser(user);
  }

  @Override
  public Optional<Task> findById(long id){
    return Optional.ofNullable(taskRepository.findById(id));
  }

  @Override
  public Task save(Task task) {
    task.setName(task.getName().trim());
    if(!task.getName().equals("")) {
      return taskRepository.save(task);
    }
    return task;
  }

  @Override
  public void deleteById(long id) {
    findById(id).orElseThrow(ResourceNotFoundException::new);
    taskRepository.deleteById(id);
  }
}
