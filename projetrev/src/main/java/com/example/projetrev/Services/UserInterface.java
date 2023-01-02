package com.example.projetrev.Services;

import java.util.List;

import com.example.projetrev.Models.UserApp;

public interface UserInterface {
    List<UserApp> getAllUsers();
    Object getUserById(long id);
    UserApp createUser(UserApp user);
    String updateUser(long id , UserApp user);
    String deleteUser(long id);
    String addRoleToUser(long id_role , long id_user);
    String addDepartementToUser(long id_user , long id_dep);
}
