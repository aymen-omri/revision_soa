package com.example.projetrev.Services.ServiceCode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetrev.Models.Departement;
import com.example.projetrev.Models.Role;
import com.example.projetrev.Models.UserApp;
import com.example.projetrev.Repositories.DepartementRepo;
import com.example.projetrev.Repositories.RoleRepo;
import com.example.projetrev.Repositories.UserRepo;
import com.example.projetrev.Services.UserInterface;

@Service
public class UserService implements UserInterface {

    final UserRepo userRepo ; 
    final RoleRepo roleRepo ;
    final DepartementRepo departementRepo ; 
    
    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo , DepartementRepo departementRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.departementRepo =  departementRepo ; 
    }

    @Override
    public List<UserApp> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Object getUserById(long id) {
        Optional<UserApp> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get() ; 
        }
        return "id not found!";
    }

    @Override
    public UserApp createUser(UserApp user) {
        Optional<UserApp> user1 = userRepo.findUserAppByEmail(user.getEmail());
        if(user1.isPresent()){
            return null ; 
        }
        return userRepo.save(user);  
    }

    @Override
    public String updateUser(long id, UserApp user) {
        Optional<UserApp> user1 = userRepo.findById(id);
        if(!user1.isPresent()){
            return "id not found" ; 
        }
        Optional<UserApp> user2 = userRepo.findUserAppByEmail(user.getEmail());  
        if(user2.isPresent()){
            return "email taken!" ; 
        }

        UserApp userr = user1.get();
        userr.setAge(user.getAge());
        userr.setEmail(user.getEmail());
        userr.setGenre(user.getGenre());
        userr.setName(user.getName());
        userr.setPassword(user.getPassword());

        userRepo.save(userr);

        return "updated successfully!";
    }

    @Override
    public String deleteUser(long id) {
        if(!userRepo.existsById(id)){
            return "id not found !";
        }
        userRepo.deleteById(id);
        return "deleted successfully!";
    }

    @Override
    public String addRoleToUser(long id_role, long id_user) {
        UserApp user = userRepo.findById(id_user).get();
        Role role = roleRepo.findById(id_role).get() ; 
        user.getRoles().add(role);
        userRepo.save(user);
        return "role added!";
    }

    @Override
    public String addDepartementToUser(long id_user, long id_dep) {
        UserApp user = userRepo.findById(id_user).get();
        Departement departement = departementRepo.findById(id_dep).get();
        user.setDepartement(departement);
        userRepo.save(user);
        return "Departement added!";
    }
    
}
