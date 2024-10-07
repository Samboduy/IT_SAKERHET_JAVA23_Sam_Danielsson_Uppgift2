package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
}
