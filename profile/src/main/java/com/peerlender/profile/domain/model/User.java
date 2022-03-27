package com.peerlender.profile.domain.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    private String firstname;
    private String lastname;
    private int age;
    private String occupation;
    private LocalDate registerdSince;

    public void setRegisterdSince(LocalDate registerdSince) {
        this.registerdSince = registerdSince;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(occupation, user.occupation) && Objects.equals(registerdSince, user.registerdSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstname, lastname, age, occupation, registerdSince);
    }

    public User(String username, String firstname, String lastname, int age, String occupation, LocalDate registerdSince) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.occupation = occupation;
        this.registerdSince = registerdSince;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", registerdSince=" + registerdSince +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public LocalDate getRegisterdSince() {
        return registerdSince;
    }
}