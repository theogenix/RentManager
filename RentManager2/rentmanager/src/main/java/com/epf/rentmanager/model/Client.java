package com.epf.rentmanager.model;
import java.time.LocalDate;

public class Client{
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private int id;

    public Client(){
        super();
    }
    public Client(String nom, String prenom, String email, LocalDate birthday) {
        this.id=id;
        this.name = nom;
        this.surname = prenom;
        this.email = email;
        this.birthday = birthday;
    }

    public Client(int id, String nom, String prenom, String email, LocalDate birthday) {
        this.id=id;
        this.name = nom;
        this.surname = prenom;
        this.email = email;
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
