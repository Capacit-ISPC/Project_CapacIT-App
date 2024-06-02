package com.capacitapp.models;



public class Usuario {
    int id;
    String email;
    String name;
    String lastname;
    String password;
    Boolean is_active;
    Boolean is_staff;

    public Usuario(int id, String email, String name, String lastname, String password, Boolean is_active, Boolean is_staff) {
        this.id= id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.is_active = is_active;
        this.is_staff = is_staff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(Boolean is_staff) {
        this.is_staff = is_staff;
    }
}

