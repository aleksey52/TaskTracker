package com.practice.tracker.controller;

import com.practice.tracker.entity.SubTask;
import com.practice.tracker.exception.ResourceNotFoundException;
import com.practice.tracker.service.CardServiceImpl;
import com.practice.tracker.service.SubTaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subtasks")
public class SubTaskController {

    private final SubTaskServiceImpl subTaskService;
    private final CardServiceImpl cardService;

    public SubTaskController(SubTaskServiceImpl subTaskService, CardServiceImpl cardService) {
        this.subTaskService = subTaskService;
        this.cardService = cardService;
    }

    @GetMapping("/card/{cardId}")
    public List<SubTask> findByCardId(@PathVariable long cardId) {
        return subTaskService.findAllByCard(cardService.findById(cardId).orElseThrow(ResourceNotFoundException::new));
    }

    @GetMapping("/{id}")
    public SubTask findOne(@PathVariable long id) {
        return subTaskService.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubTask create(@RequestBody SubTask subTask) {
        return subTaskService.save(subTask);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        long cardId = subTaskService.findById(id).orElseThrow(ResourceNotFoundException::new).getCard().getId();
        subTaskService.deleteById(id);

        return "redirect:/cards/" + cardId;
    }

    @PutMapping("/{id}")
    public SubTask updateSubTask(@RequestBody SubTask subTask, @PathVariable long id) {
        subTaskService.findById(id);
        return  subTaskService.save(subTask);
    }

    @GetMapping("/changeStatus/{id}")
    public String changeSubTaskStatus(@PathVariable("id") long id) {
        subTaskService.changeStatus(id);
        return "cards_page";
    }

    @GetMapping("/card/{cardId}/add")
    public String newSubTask(@PathVariable("cardId") long cardId, Model model) {
        cardService.findById(cardId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("subTask", new SubTask());
        return "adding_sub_task_page";
    }

    @PostMapping("/card/{cardId}/add")
    public String addingSubTask(@PathVariable("cardId") long cardId, @ModelAttribute SubTask subTask, Model model) {
        model.addAttribute("subTask", subTask);
        subTask.setCard(cardService.findById(cardId).orElseThrow(ResourceNotFoundException::new));
        subTaskService.save(subTask);

        return "redirect:/cards/" + cardId;
    }
}

