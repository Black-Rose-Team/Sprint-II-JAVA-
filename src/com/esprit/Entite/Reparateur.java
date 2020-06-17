/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Entite;

import java.util.Random;


/**
 *
 * @author BEN SAID
 */
public class Reparateur {
    int id;
    String Nom;
    String Prenom;
    int NumTel;
    int nbr_velo_arep;
    int Experience;
    String status;
    String image;
    
      public  int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

    public Reparateur(String Nom, String Prenom, int NumTel, int nbr_velo_arep, int Experience, String status) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.NumTel = NumTel;
        this.nbr_velo_arep = nbr_velo_arep;
        this.Experience = Experience;
        this.status = status;
    }
    
    public Reparateur(int id, String Nom, String Prenom, int NumTel, int nbr_velo_arep, int Experience, String status, String image) {
        this.id=id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.NumTel = NumTel;
        this.nbr_velo_arep = nbr_velo_arep;
        this.Experience = Experience;
        this.status = status;
        this.image =image;
    }
    public Reparateur(String Nom, String Prenom, int NumTel, int nbr_velo_arep, int Experience, String status, String image) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.NumTel = NumTel;
        this.nbr_velo_arep = nbr_velo_arep;
        this.Experience = Experience;
        this.status = status;
        this.image =image;
    }

     public Reparateur(String Nom, String Prenom, int NumTel, int nbr_velo_arep, int Experience) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.NumTel = NumTel;
        this.nbr_velo_arep = nbr_velo_arep;
        this.Experience = Experience;
    }
   
    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }
    
      public int getNbr_velo_arep() {
        return nbr_velo_arep;
    }

    public void setNbr_velo_arep(int nbr_velo_arep) {
        this.nbr_velo_arep = nbr_velo_arep;
    }
    
      public int getExperience() {
        return Experience;
    }

    public void setExperience(int Experience) {
        this.Experience = Experience;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public Reparateur(int id, String Nom, String Prenom, int NumTel, int nbr_velo_arep, int Experience, String status) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.NumTel = NumTel;
        this.nbr_velo_arep = nbr_velo_arep;
        this.Experience = Experience;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reparateur{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", NumTel=" + NumTel + ", Nombre de vélo à réparer=" + nbr_velo_arep + ", Experience=" + Experience + ", Statut=" + status + '}';
    }

    public Reparateur() {
    }

}
