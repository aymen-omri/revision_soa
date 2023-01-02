package com.example.projetrev.Contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetrev.Models.UserApp;
import com.example.projetrev.Services.ServiceCode.UserService;


@RestController
@RequestMapping(path="/api/userapp")
public class UserController {

    final UserService userService ;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } 

    @GetMapping
    public ResponseEntity<List<UserApp>> getAllUsers(){
        List<UserApp> users = userService.getAllUsers() ; 
        if(users.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ; 
        }
        return ResponseEntity.ok(users); 
    }


    @GetMapping(path = "/{id_user}")
    public ResponseEntity<?> getUserById(@PathVariable("id_user") long id_user){
        return ResponseEntity.ok(userService.getUserById(id_user)); 
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserApp user) {
        try{
            UserApp user1 = userService.createUser(user);
            if(user1 == null){
                 return ResponseEntity.status(404).body("email taken !");
        } 
            return ResponseEntity.ok("added successfully!");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping(path="/{id_user}") 
    public String deleteUser(@PathVariable("id_user") long id){
        return userService.deleteUser(id);
    }


    @PutMapping(path="/{id_user}")
    public ResponseEntity<String> updateUser(@RequestBody UserApp user , @PathVariable("id_user") long id_user){
        return ResponseEntity.ok(userService.updateUser(id_user, user));
    }
    
    @PutMapping(path="/{id_user}/role/{id_role}")
    public String addRoleToUser(@PathVariable("id_user") long id_user, @PathVariable("id_role") long id_role){
        return userService.addRoleToUser(id_role, id_user);
    } 

    @PutMapping(path="/{id_user}/dep/{id_dep}")
    public String addDepartementToUser(@PathVariable("id_user") long id_user, @PathVariable("id_dep") long id_dep) {
        return userService.addDepartementToUser(id_user, id_dep);
    }

    
    
}
