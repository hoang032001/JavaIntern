package com.example.springrestcontroller.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name ="find account by id" , query = "select a from Account a where a.id= :id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, unique = true, nullable = false)
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username must not contain whitespace")
    private String username;

    @Column(length = 150, nullable = false)
    @Size(min = 8, max = 50, message = "Password must be at least 8 character")
    @NotEmpty(message = "Password cannot be null")
    @NotBlank(message = "Password must not contain whitespace")
    private String password;

    private int status;

    public Account(){

    }

    public Account(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
