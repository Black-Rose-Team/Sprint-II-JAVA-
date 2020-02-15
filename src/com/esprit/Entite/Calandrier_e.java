/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;

/**
 *
 * @author BEN SAID
 */
public class Calandrier_e {
    int id_calandrier;
    int id_examen;
    int id_matiere;
    int id_salle;
    int id_classe;
    String nom;

    public int getId_calandrier() {
        return id_calandrier;
    }

    public int getId_examen() {
        return id_examen;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public int getId_salle() {
        return id_salle;
    }

    public int getId_classe() {
        return id_classe;
    }

    public String getNom() {
        return nom;
    }

    public void setId_calandrier(int id_calandrier) {
        this.id_calandrier = id_calandrier;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Calandrier_e(int id_calandrier, int id_examen, int id_matiere, int id_salle, int id_classe, String nom) {
        this.id_calandrier = id_calandrier;
        this.id_examen = id_examen;
        this.id_matiere = id_matiere;
        this.id_salle = id_salle;
        this.id_classe = id_classe;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Calandrier_e{" + "id_calandrier=" + id_calandrier + ", id_examen=" + id_examen + ", id_matiere=" + id_matiere + ", id_salle=" + id_salle + ", id_classe=" + id_classe + ", nom=" + nom + '}';
    }

    public Calandrier_e() {
    }
    
    
}
