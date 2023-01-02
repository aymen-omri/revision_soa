package com.example.projetrev.Models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class UserApp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user ; 
    private String name ; 
    @Column(unique = true)
    @NonNull 
    private String email ;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password ;  
    private int age ;
    @Enumerated(EnumType.STRING) 
    private Genre genre ; 
    
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<Role>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dep")
    Departement departement ; 


    public UserApp(long id_user, String name, String email, String password, int age, Genre genre,
            Collection<Role> roles ,Departement departement ) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.genre = genre;
        this.roles = roles;
        this.departement = departement ; 
    }

    public UserApp(){}

    

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserApp [id_user=" + id_user + ", name=" + name + ", email=" + email + ", password=" + password
                + ", age=" + age + ", genre=" + genre + ", roles=" + roles + ", departement=" + departement + "]";
    }
 


}
