package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.JWT;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.SecretKey;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.Users;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.UsersService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimecapsuleService {
    @Autowired
    UsersService usersService;
    @Autowired
    TimecapsuleRepository timecapsuleRepository;
    @Autowired
    JWT jwt;
    @Autowired
    SecretKey secretKey;

    public void createNewTimecapsule(DecodedJWT decodedJWT,String message) {
        //System.out.println(jwt.extractAllClaims(token, String.valueOf(secretKey.getSecretKey())).getSubject());
        System.out.println(message);
        Timecapsule timecapsule = new Timecapsule();
        timecapsule.setMessage(message);
         Users user = usersService.getUser(decodedJWT.getSubject());
        timecapsule.setUsers(user);
        timecapsuleRepository.save(timecapsule);
    }

    public DecodedJWT checkToken(String token){
            return jwt.verifyJWT(token, secretKey.getSecretKey());
    }
    public ResponseEntity<List<TimecapsuleDTO>> getAllTimecapsules(DecodedJWT decodedJWT){
        List<TimecapsuleDTO> timecapsuleDTOS = new ArrayList<>();
        Users user = usersService.getUser(decodedJWT.getSubject());
        timecapsuleRepository.getTimecapsulesByUsers(user)
                .forEach(timecapsule -> timecapsuleDTOS.add(this.convertToTimecapsuleDTO(timecapsule)));
        return ResponseEntity.ok(timecapsuleDTOS);
    }

    private TimecapsuleDTO convertToTimecapsuleDTO(Timecapsule timecapsule){
        TimecapsuleDTO timecapsuleDTO = new TimecapsuleDTO();
        timecapsuleDTO.setId(timecapsule.getId());
        timecapsuleDTO.setMessage(timecapsule.getMessage());
        return timecapsuleDTO;
    }

    public Timecapsule getOneTimecapsule(DecodedJWT decodedJWT,long id){
        Users user =  usersService.getUser(decodedJWT.getSubject());
        return timecapsuleRepository.getTimecapsulesByIdAndAndUsers(id,user);
    }

}
