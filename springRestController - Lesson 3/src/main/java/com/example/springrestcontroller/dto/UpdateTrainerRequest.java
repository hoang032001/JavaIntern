package com.example.springrestcontroller.dto;

public class UpdateTrainerRequest {
    private String newName;
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
