package com.example.girasecondtry.models.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;


    private String password;


    @Column(unique = true, nullable = false)
    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
