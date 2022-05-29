package com.email.Entity.controller;

import com.email.Entity.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        boolean res = emailService.emailSend(emailRequest);
        if(res==true) {
            return ResponseEntity.ok("Email sent successfully..!");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent!!");
        }
    }


}
