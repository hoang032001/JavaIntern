package com.example.springrestcontroller.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name = "horse")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=255)
    @NotEmpty(message = "Horse Name cannot be null")
    private String name;

    private java.sql.Timestamp foaled;

    public Horse(){

    }

    public Horse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getFoaled() {
        return foaled;
    }

    public void setFoaled(Timestamp foaled) {
        this.foaled = foaled;
    }
}
