package com.example.travelapp.repository;

import com.example.travelapp.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByUserIdAndCode(Long userId, String resetCode);

    VerificationCode findByUserId(Long userId);
}
