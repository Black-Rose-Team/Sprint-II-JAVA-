/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author Crow
 */
public class Provider {
    String id;
    String name;
    String mail;
    int tel;
    int password;
    
   
    
    public Provider(){}
    
    public Provider(String id) {
       this.id =id;
    }
    
     public Provider(String name, String mail, int tel) {
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }

    public Provider(String id,String name, String mail, int tel) {
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.id=id;
    }
    
    

    public Provider(String id, String name, String mail, int tel,int password) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.password =password;
    }

    public Provider(String name, String mail, int tel, int password) {
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.password =password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    
    
   

    /**
     *
     * @param min
     * @param max
     * @return
     */
  /*  public  int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}*/
    
    
    /*public  String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } */
    
}
