package com.sda.werehouse.unit303.model.entity;

import com.sda.werehouse.unit303.model.Roles;

import javax.persistence.*;
import javax.persistence.Enumerated;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String personalMessege;
    private String pMessenger;

    public String getPersonalMessege() {
        return personalMessege;
    }

    public void setPersonalMessege(String personalMessege) {
        this.personalMessege = personalMessege;
    }

    public String getpMessenger() {
        return pMessenger;
    }

    public void setpMessenger(String pMessenger) {
        this.pMessenger = pMessenger;
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

    public Roles getRoles() {
        return roles;
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
}
