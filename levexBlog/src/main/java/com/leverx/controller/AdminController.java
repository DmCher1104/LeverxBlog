package com.leverx.controller;

import com.leverx.entity.User;
import com.leverx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    public String getAllUsers(Model model) {

        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);

        return "admin";
    }

    @GetMapping("/updateUserByAdmin")
    public String updateUserByAdmin(@RequestParam("userId") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "updateUserPage";
    }

    @PostMapping("/updateUserByAdmin")
    public String updateUserByAdmin(@ModelAttribute("user") User user){
        userService.updateUserByAdmin(user);
        return "redirect:/getUsers";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser (@RequestParam("userId") int id, Model model) {
        userService.deleteUser(id);
        return "redirect:/getUsers";
    }


}
