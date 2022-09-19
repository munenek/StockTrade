package com.example.stocktrade.model;


import javax.persistence.*;

@Entity
@Embeddable
@Table(name = "user_table")
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)

    private Long id;
    private String name;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
