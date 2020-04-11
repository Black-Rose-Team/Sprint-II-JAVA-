/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Crow
 */
public class Charge {
    
    int id;
    float price;
    String state;
    Date date;
    String Provider;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String Provider) {
        this.Provider = Provider;
    }
    
     public Charge(){}

    public Charge(Date date, String Provider) {
        this.date = date;
        this.Provider = Provider;
    }
    
     
     public Charge(float price, String state, Date date, String Provider) {
        this.price = price;
        this.state = state;
        this.date = date;
        this.Provider = Provider;
    }

    public Charge(int id, float price, String state, Date date, String Provider) {
        this.id = id;
        this.price = price;
        this.state = state;
        this.date = date;
        this.Provider = Provider;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    
    
}
