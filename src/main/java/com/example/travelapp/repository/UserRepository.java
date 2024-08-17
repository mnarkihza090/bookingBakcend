package com.example.travelapp.repository;


import com.example.travelapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query("select u from User u where u.id = :x")
    User findUserById(@Param("x") Long id);
    User findByUsername(String username);
    User findByPhoneNumber(String phoneNumber);
}
