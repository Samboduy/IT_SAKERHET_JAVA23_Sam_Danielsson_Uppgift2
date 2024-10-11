package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.JWT;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
     JWT jwt;
    @Autowired
     SecretKey secretKey;

    @Autowired
    UsersRepository usersRepository;
    public Users saveNewUser(Users users){
        usersRepository.save(users);
        return users;
    }
    public Users getUser(String email){
       return usersRepository.getUsersByEmail(email);
    }
    public ResponseEntity<String> checkUserLogin(Users user){
        System.out.println(user.getEmail());
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        Users DBUser = getUser(userEmail);
        if (userEmail.equals(DBUser.getEmail()) && userPassword.equals(DBUser.getPassword())){
            secretKey.generateSecretKey();
            jwt.generateJWT(DBUser.getEmail(),secretKey.getSecretKey());
            String generatedJWT = jwt.getGeneratedJWT();
            return  ResponseEntity.ok(generatedJWT);
        }
        else {
            return  ResponseEntity.notFound().build();
        }

    }
}
