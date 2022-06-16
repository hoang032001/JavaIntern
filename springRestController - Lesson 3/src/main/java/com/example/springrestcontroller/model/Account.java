package com.example.springrestcontroller.model;

import javax.persistence.*;

@Entity
@NamedQuery(name ="find account by id" , query = "select a from Account a where a.id= :id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, unique = true, nullable = false)
    private String username;
    @Column(length = 150, nullable = false)
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
