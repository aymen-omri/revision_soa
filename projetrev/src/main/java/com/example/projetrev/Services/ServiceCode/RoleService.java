package com.example.projetrev.Services.ServiceCode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetrev.Models.Role;
import com.example.projetrev.Repositories.RoleRepo;
import com.example.projetrev.Services.RoleInterface;

@Service
public class RoleService implements RoleInterface {
    
    final RoleRepo roleRepo ;
    
    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<Role> getRoleById(long id) {
        return roleRepo.findById(id);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public String deleteRole(long id) {
        if(!roleRepo.existsById(id)){
            return "id not found!";
        }
        roleRepo.deleteById(id);
        return "deleted successfully!";
    } 
    
    
}
