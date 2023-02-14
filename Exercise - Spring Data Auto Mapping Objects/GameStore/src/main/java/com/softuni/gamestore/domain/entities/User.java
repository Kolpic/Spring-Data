package com.softuni.gamestore.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

import static com.softuni.gamestore.constants.Validations.PASSWORD_VALIDATOR;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false)
    @Email(regexp = PASSWORD_VALIDATOR)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;
    @OneToMany(targetEntity = Order.class,mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Order> orders;
    @Column
    private boolean isAdmin;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
