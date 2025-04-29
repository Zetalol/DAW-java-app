package com.example.llibreria.models;

/**
 * Classe que representa un gènere literari.
 *
 * Conté l'identificador i el nom del gènere.
 */
public class Genere {
    private Integer id;
    private String nom;

    // Constructors
    public Genere() {
    }

    public Genere(String nom) {
        this.nom = nom;
    }

    public Genere(Integer id, String nom) {
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
        return "Genere{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
