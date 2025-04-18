package com.yash.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.client.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends  JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
}
