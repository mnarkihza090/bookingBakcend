package com.example.travelapp.repository;


import com.example.travelapp.entity.User;
import com.example.travelapp.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUserId(Long userId);
}
