package com.practice.tracker.controller;

import com.practice.tracker.entity.Task;
import com.practice.tracker.exception.ResourceNotFoundException;
import com.practice.tracker.service.TaskServiceImpl;
import com.practice.tracker.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;

    public TaskController(TaskServiceImpl taskService, UserServiceImpl userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public String findByUserId(@PathVariable("userId") long userId, Model model) {
        List<Task> tasks = taskService.findAllByUser(userService.findById(userId).orElseThrow(ResourceNotFoundException::new));
        model.addAttribute("tasks", tasks);
        return "tasks_page";
    }

    @GetMapping("/user/{userId}/add")
    public String newTask(@PathVariable("userId") long userId, Model model) {
        userService.findById(userId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("task", new Task());
        return "adding_task_page";
    }

    @PostMapping("/user/{userId}/add")
    public String addingTask(@PathVariable("userId") long userId, @ModelAttribute Task task, Model model) {
        model.addAttribute("task", task);
        task.setUser(userService.findById(userId).orElseThrow(ResourceNotFoundException::new));
        taskService.save(task);
        model.addAttribute("task", new Task());
        return "adding_task_page";
    }

    @PostMapping("/{userId}/delete/{taskId}")
    public String delete(@PathVariable long userId, @PathVariable long taskId) {
        taskService.deleteById(taskId);
        return "redirect:/tasks/user/" + userId;
    }
}
