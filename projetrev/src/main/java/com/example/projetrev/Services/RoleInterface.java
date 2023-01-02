package com.example.projetrev.Services;

import java.util.List;
import java.util.Optional;

import com.example.projetrev.Models.Role;

public interface RoleInterface {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(long id);
    Role createRole(Role role);
    String deleteRole(long id);
}
