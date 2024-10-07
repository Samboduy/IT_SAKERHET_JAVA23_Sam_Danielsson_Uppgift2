package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping(value = "/createuser")
    public void createUser() {
        System.out.println("wow!");
    }

}
