package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.controller;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.Users;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping(value = "/createuser")
    @ResponseBody
    public void createUser(@RequestBody Users users) {
        System.out.println(users.toString());
       Users savedUser =  usersService.saveNewUser(users);
    }
    @PostMapping(value = "/user/login")
    @ResponseBody
    public void loginUser(@RequestBody Users users) {
        usersService.checkUserLogin(users);


    }

}
