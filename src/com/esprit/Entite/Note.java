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
public class Note {
    int id_eleve;
    int id_examen;
    int id_matiere;
    float note;

   

    public int getId_eleve() {
        return id_eleve;
    }

    public int getId_examen() {
        return id_examen;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public float getNote() {
        return note;
    }

    public void setId_eleve(int id_eleve) {
        this.id_eleve = id_eleve;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" + "id_eleve=" + id_eleve + ", id_examen=" + id_examen + ", id_matiere=" + id_matiere + ", note=" + note + '}';
    }

    public Note(int id_eleve, int id_examen, int id_matiere, float note) {
        this.id_eleve = id_eleve;
        this.id_examen = id_examen;
        this.id_matiere = id_matiere;
        this.note = note;
    }

    public Note() {
    }
    
    
    
}
