package com.leverx.controller;

import com.leverx.entity.User;
import com.leverx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") User user,
                                   @RequestParam("username") String username,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userService.registrationUser(user, username)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    private String activate(@PathVariable String code, Model model){

        boolean isActivate =userService.activateUser(code);
        if(isActivate){
            model.addAttribute("message", "User successfully activated");
        }else {
            model.addAttribute("message", "Activation code not found");
        }
        return "login";
    }
}
