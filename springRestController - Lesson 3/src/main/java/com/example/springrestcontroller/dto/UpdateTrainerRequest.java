package com.example.springrestcontroller.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateTrainerRequest {
    @NotEmpty
    private String newName;
    @NotEmpty
    private int id;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
