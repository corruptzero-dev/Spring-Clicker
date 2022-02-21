package com.game.clicker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.game.clicker.entity.User;
import com.game.clicker.services.UserService;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users/get")
    public Page<User> getUsers(@RequestParam Integer page,
                               @RequestParam Integer size){return userService.getUsers(PageRequest.of(page,size));}

    @PostMapping("api/users/register")
    public String register(@RequestParam String username,
                           @RequestParam String password)
                           {
        return userService.addUser(new User(username, password));
    }



}
