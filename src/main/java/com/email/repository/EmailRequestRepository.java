package com.email.repository;

import com.email.Entity.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRequestRepository extends JpaRepository<EmailRequest,Long> {

}
