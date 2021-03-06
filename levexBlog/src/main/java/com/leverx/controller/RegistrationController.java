package com.leverx.controller;

import com.leverx.entity.User;
import com.leverx.exception_handling.NoSuchException;
import com.leverx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String registrationUser(@Valid @ModelAttribute("user") User user,
                                   @RequestParam("username") String username,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/registration";
        }

        if(!(userService.findUserByUsername(username)==true)){
            userService.registrationUser(user);
        }else {
            throw new NoSuchException("there is user  = "
                    + username + " in database, chose another name");
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    private String activate(@PathVariable String code, Model model) {

        boolean isActivate = userService.activateUser(code);
        if (isActivate) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code not found");
        }
        return "login";
    }
}
