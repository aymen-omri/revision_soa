package com.example.projetrev.Contollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetrev.Models.Role;
import com.example.projetrev.Services.ServiceCode.RoleService;

@RestController
@RequestMapping(path="/api/role")
public class RoleController {
    final RoleService roleService ;
    
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    } 

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        if(roles.size() == 0){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping(path="/{id_role}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id_role") long id_role){
        Optional<Role> role = roleService.getRoleById(id_role);
        if(role.isPresent()){
            return ResponseEntity.ok(role.get());
        } 
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody Role role){
        try{
            Role role1 = roleService.createRole(role);
            return ResponseEntity.ok("added successfully: "+role1.toString());

        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path="/{id_role}")
    public ResponseEntity<String> deleteRole(@PathVariable("id_role") long id_role){
        if(roleService.deleteRole(id_role).equals("id not found!")){
            return ResponseEntity.status(500).body("id not found!");
        }else{
            return ResponseEntity.ok("deleted successfully!");
        }
    }

 }


    

