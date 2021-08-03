package com.practice.tracker.controller;

import com.practice.tracker.entity.Card;
import com.practice.tracker.exception.ResourceNotFoundException;
import com.practice.tracker.service.CardServiceImpl;
import com.practice.tracker.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardServiceImpl cardService;
    private final TaskServiceImpl taskService;

    public CardController(CardServiceImpl cardService, TaskServiceImpl taskService) {
        this.cardService = cardService;
        this.taskService = taskService;
    }

    @GetMapping("/task/{taskId}")
    public String findByTaskId(@PathVariable("taskId") long taskId, Model model) {
        List<Card> cards = cardService.findAllByTask(taskService.findById(taskId).orElseThrow(ResourceNotFoundException::new));
        model.addAttribute("cards", cards);

        return "cards_page";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") long id, Model model) {
        Card card = cardService.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("card", card);

        return "open_card_page";
    }

    @GetMapping("/task/{taskId}/add")
    public String newCard(@PathVariable("taskId") long taskId, Model model) {
        taskService.findById(taskId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("card", new Card());
        return "adding_card_page";
    }

    @PostMapping("/task/{taskId}/add")
    public String addingCard(@PathVariable("taskId") long taskId, @ModelAttribute Card card, Model model) {
        model.addAttribute("card", card);
        card.setTask(taskService.findById(taskId).orElseThrow(ResourceNotFoundException::new));
        cardService.save(card);

        return "redirect:/subtasks/card/" + card.getId() + "/add";
    }

    @GetMapping("/task/{taskId}/back")
    public String back(@PathVariable("taskId") long taskId, Model model) {

        return "redirect:/tasks/user/" + taskService.findById(taskId).orElseThrow(ResourceNotFoundException::new).getUser().getId();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Card create(@RequestBody Card card) {
        return cardService.save(card);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        long taskId = cardService.findById(id).orElseThrow(ResourceNotFoundException::new).getTask().getId();
        cardService.deleteById(id);

        return "redirect:/cards/task/" + taskId;
    }

    @PutMapping("/{id}")
    public Card updateCard(@RequestBody Card card, @PathVariable long id) {
        cardService.findById(id);
        return cardService.save(card);
    }
}
