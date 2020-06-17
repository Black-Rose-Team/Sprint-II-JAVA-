/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author OMEN
 */
public class VeloARep {
    private int id;
    private String Marque;
    private String Description;
    private String Probleme;
    private int age;
    private String status;
    private int reparateur_id;
    private int user_id;
    
    public VeloARep(String Marque, String Description, String Probleme, int age, String status, int reparateur_id) {
        this.Marque = Marque;
        this.Description = Description;
        this.Probleme = Probleme;
        this.age = age;
        this.status = status;
        this.reparateur_id = reparateur_id;
    }
     
    
     public VeloARep(String Marque, String Description, String Probleme, int age, String status, int reparateur_id, int user_id) {
        this.Marque = Marque;
        this.Description = Description;
        this.Probleme = Probleme;
        this.age = age;
        this.status = status;
        this.reparateur_id = reparateur_id;
        this.user_id = user_id;
    }


    /**
     * @return the id
     */
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int id) {
        this.user_id = id;
    }
    
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Marque
     */
    public String getMarque() {
        return Marque;
    }

    /**
     * @param Marque the Marque to set
     */
    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Probleme
     */
    public String getProbleme() {
        return Probleme;
    }

    /**
     * @param Probleme the Probleme to set
     */
    public void setProbleme(String Probleme) {
        this.Probleme = Probleme;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getReparateur_id() {
        return reparateur_id;
    }
    
    public void setReparateur_id(int reparateur_id){
        this.reparateur_id = reparateur_id;
    }
    public void setRepNull(){
        this.reparateur_id = -1;
    }
    
    
    public VeloARep(int id, String Marque, String Description, String Probleme, int age, String status, int reparateur_id) {
        this.id = id;
        this.Marque = Marque;
        this.Description = Description;
        this.Probleme = Probleme;
        this.age = age;
        this.status = status;
        this.reparateur_id = reparateur_id;
        
    }
    
    public VeloARep() {
    }

    
}
