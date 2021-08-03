package com.practice.tracker.controller;

import com.practice.tracker.entity.User;
import com.practice.tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String firstPage(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginOpen(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        User foundUser = userService.findUser(user.getUserName());

        if (foundUser != null) {
            return "redirect:/tasks/user/" + foundUser.getId();
        }

        return "login";
    }

}
