package com.example.projetrev.Models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Departement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_dep ; 
    private String name ;
    
    @JsonIgnore
    @OneToMany(mappedBy = "departement")
    Collection<UserApp> users = new ArrayList<UserApp>() ; 

    public Departement(long id_dep, String name) {
        this.id_dep = id_dep;
        this.name = name;
    } 

    public Departement(){}

    public long getId_dep() {
        return id_dep;
    }

    public void setId_dep(long id_dep) {
        this.id_dep = id_dep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Departement [id_dep=" + id_dep + ", name=" + name + ", users=" + users + "]";
    }

  

    
    
    

}
