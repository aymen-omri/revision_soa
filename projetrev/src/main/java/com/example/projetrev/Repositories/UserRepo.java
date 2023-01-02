package com.example.projetrev.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetrev.Models.UserApp;

@Repository
public interface UserRepo extends JpaRepository<UserApp,Long> {
    Optional<UserApp> findUserAppByEmail(String email) ; 
}
