/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author BEN SAID
 */
public class Examen {
    int id_examen;
    int id_matiere;
    Date date_examen;
    float coefficient;

    public Date getDate_examen() {
        return date_examen;
    }

    public void setDate_examen(Date date_examen) {
        this.date_examen = date_examen;
    }

    
    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public Examen(int id_examen, int id_matiere, Date date_examen, float coefficient) {
        this.id_examen = id_examen;
        this.id_matiere = id_matiere;
        this.date_examen = date_examen;
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Examen{" + "id_examen=" + id_examen + ", id_matiere=" + id_matiere + ", date_examen=" + date_examen + ", coefficient=" + coefficient + '}';
    }

   

    
    
   
}
