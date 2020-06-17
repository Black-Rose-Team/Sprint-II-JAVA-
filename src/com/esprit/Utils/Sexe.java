/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Utils;

/**
 *
 * @author hamza
 */
public enum Sexe {
  
  femme( "femme"), homme( "homme");
 
    private String sexe;
    
 
    private Sexe(String text) {
        this.sexe = text;
        
    }
 
    
 
    public String getSexe() {
        return sexe;
    }
 
    public static Sexe getByCode(String str) {
        for (Sexe g : Sexe.values()) {
            if (g.sexe.equals(str)) {
                return g;
            }
        }
        return null;
    }
 
    @Override
    public String toString() {
        return this.sexe;
    }
}
