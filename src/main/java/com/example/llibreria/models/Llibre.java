package com.example.llibreria.models;

import java.util.List;

/**
 * Classe que representa un llibre.
 *
 * Conté informació bàsica com el títol, ISBN, any de publicació i
 * identificadors
 * de relació amb l’editorial, l’autor i el gènere. També inclou camps amb els
 * noms
 * associats per facilitar la visualització.
 */
public class Llibre {
    private Integer id;
    private String titol;
    private String isbn;
    private Integer anyPublicacio;
    private Integer idEditorial;
    private Integer autorId;
    private Integer genereId;
    private String autorNom;
    private String genereNom;
    private String editorialNom;

    // Constructors
    public Llibre() {
    }

    public Llibre(String titol, String isbn, Integer anyPublicacio, Integer idEditorial) {
        this.titol = titol;
        this.isbn = isbn;
        this.anyPublicacio = anyPublicacio;
        this.idEditorial = idEditorial;
    }

    public Llibre(Integer id, String titol, String isbn, Integer anyPublicacio, Integer idEditorial) {
        this.id = id;
        this.titol = titol;
        this.isbn = isbn;
        this.anyPublicacio = anyPublicacio;
        this.idEditorial = idEditorial;

    }

    public Llibre(Integer id, String titol, String isbn, Integer anyPublicacio, Integer idEditorial, String autorNom,
            String genereNom, String editorialNom) {
        this.id = id;
        this.titol = titol;
        this.isbn = isbn;
        this.anyPublicacio = anyPublicacio;
        this.editorialNom = editorialNom;
        this.genereNom = genereNom;
        this.autorNom = autorNom;
    }

    // Getters i Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(Integer anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getGenereId() {
        return genereId;
    }

    public void setGenereId(Integer genereId) {
        this.genereId = genereId;
    }

    public String getAutorNom() {
        return autorNom;
    }

    public void setAutorNom(String autorNom) {
        this.autorNom = autorNom;
    }

    public String getGenereNom() {
        return genereNom;
    }

    public void setGenereNom(String genereNom) {
        this.genereNom = genereNom;
    }

    public String getEditorialNom() {
        return editorialNom;
    }

    public void setEditorialNom(String editorialNom) {
        this.editorialNom = editorialNom;
    }

    // toString
    @Override
    public String toString() {
        return "Llibre{" +
                "id=" + id +
                ", titol='" + titol + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anyPublicacio=" + anyPublicacio +
                ", idEditorial=" + idEditorial +
                ", autorId=" + autorId +
                ", genereId=" + genereId +
                '}';
    }
}
