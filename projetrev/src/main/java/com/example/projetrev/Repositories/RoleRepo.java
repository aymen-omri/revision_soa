package com.example.projetrev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetrev.Models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    
}
