package com.prkng.svc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prkng.svc.dto.UserDto;
import com.prkng.svc.model.Users;
import com.prkng.svc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Users addUsers(@RequestBody UserDto userDto) {
        
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public Users login(@RequestBody UserDto userDto ) {
        
        return userService.getUserByUsername(userDto.getUsername(), userDto.getPassword());
    }
    
    
}
