package com.example.notasapp.model.entity;

import jakarta.persistence.*;

@Entity(name = "papeis")
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String papel;

    public Papel() {}

    public Papel(Long id, String papel) {
        this.id = id;
        this.papel = papel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}
