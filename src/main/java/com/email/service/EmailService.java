package com.email.service;

import com.email.Entity.EmailRequest;

public interface EmailService {

     boolean emailSend(EmailRequest emailRequest);
}
