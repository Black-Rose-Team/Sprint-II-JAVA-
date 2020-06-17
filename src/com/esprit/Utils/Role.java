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
public enum Role {

  ADMIN( "USER_ADMIN"), CLIENT( "USER_CLIENT");
 
    private String role;
    
 
    private Role(String text) {
        this.role = text;
        
    }
 
    
 
    public String getRole() {
        return role;
    }
 
    public static Role getByCode(String str) {
        char[] ch = str.toCharArray();
        String strRole = "";
        int i = 20;
            while(ch[i] != '"'){
                 strRole += ch[i];
                 i++;
            }
        Role role = Role.CLIENT;
        if (strRole.equals("ADMIN")){
            role = Role.ADMIN;
        }else if (strRole.equals("CLIENT")){
            role = Role.CLIENT;
        }
        return role;

    }
 
    @Override
    public String toString() {
        return this.role;
    }
}
