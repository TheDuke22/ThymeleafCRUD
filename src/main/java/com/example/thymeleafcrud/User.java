package com.example.thymeleafcrud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private boolean active = true;
    private LocalDateTime creatoil = LocalDateTime.now();
    private LocalDateTime aggiornatoil = LocalDateTime.now();

    public User(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
}
