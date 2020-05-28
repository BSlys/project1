package com.sda.werehouse.unit303.model.dto;

import com.sda.werehouse.unit303.model.Roles;

public class UserDto {

    public Long id;
    public String name;
    public String surname;
    public int age;
    public String password;
    public Roles roles;
    public String message;
    public String pMessanger;

    public String getpMessanger() {
        return pMessanger;
    }

    public void setpMessanger(String pMessanger, String sender) {
        this.pMessanger = sender;
        this.pMessanger = pMessanger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Roles getRoles() {
        return roles;
    }

    public String getRolesName() {
        return roles.name();
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
