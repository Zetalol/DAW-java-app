package com.example.llibreria.models;

public class Editorial {
    private Integer id; // ID de l'editorial
    private String nom; // Nom de l'editorial

    // Constructor buit (necessari per frameworks o us general)
    public Editorial() {
    }

    // Constructor amb paràmetres
    public Editorial(Integer id, String nom) {
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

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
