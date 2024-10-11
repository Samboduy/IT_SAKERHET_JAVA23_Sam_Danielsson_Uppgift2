package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.Timecapsule;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.TimecapsuleDTO;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.TimecapsuleService;
import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimecapsuleController {
    @Autowired
    TimecapsuleService timecapsuleService;

    @PostMapping(value = "/timecapsule/create")
    @ResponseBody
    public void createTimecapsule(@RequestBody Token token) {
        System.out.printf("message:%s\ntoken:%s\n", token.getMessage(),token.getToken());
        String jwtToken = token.getToken();
        DecodedJWT decodedJWT =  timecapsuleService.checkToken(jwtToken);
        if (decodedJWT != null) {
            System.out.println("not null token");
            timecapsuleService.createNewTimecapsule(decodedJWT,token.getMessage());
        }
        else {
            System.out.println("null token");

        }

    }
    @PostMapping(value = "/get/all/timecapsules")
    @ResponseBody
    public ResponseEntity<List<TimecapsuleDTO>> getTimecapsule(@RequestBody Token token) {
        DecodedJWT decodedJWT = timecapsuleService.checkToken(token.getToken());
        if (decodedJWT != null) {
            System.out.println("not null token");
            return timecapsuleService.getAllTimecapsules(decodedJWT);
        }
        else {
            System.out.println("null token");
            return null;
        }
    }
    @PostMapping(value = "/get/one/timecapsules/message")
    @ResponseBody
    public ResponseEntity<String> getOneTimecapsule(@RequestBody Token token) {
        System.out.println(token.getMessageId());
        DecodedJWT decodedJWT = timecapsuleService.checkToken(token.getToken());
      Timecapsule timecapsule =   timecapsuleService.getOneTimecapsule(decodedJWT, token.getMessageId());
         String message = timecapsule.getMessage();
        System.out.println(timecapsule.getId());
        System.out.println(message);
        return ResponseEntity.ok(message);
    }


}
