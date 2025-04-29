package com.example.llibreria.models;

/**
 * Classe que representa un autor de llibres.
 *
 * Conté l'identificador i el nom de l'autor.
 */
public class Autor {
    private Integer id;
    private String nom;

    // Constructors
    public Autor() {
    }

    public Autor(String nom) {
        this.nom = nom;
    }

    public Autor(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters i Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // toString
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
