package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

     JWT jwt;

    @Autowired
    UsersRepository usersRepository;
    public Users saveNewUser(Users users){
        usersRepository.save(users);
        return users;
    }
    public Users getUser(String email){
       return usersRepository.getUsersByEmail(email);
    }
    public void checkUserLogin(Users user){
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        Users DBUser = getUser(userEmail);
        if (userEmail.equals(DBUser.getEmail()) && userPassword.equals(DBUser.getPassword())){
            jwt.generateSecretKey(user.getId());
        }

    }
}
