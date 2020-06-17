package com.esprit.Entite;


import com.esprit.Utils.Role;
import com.esprit.Utils.Sexe;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class User {
    private int id;
    private String login,mdp,email;
    
    private Role role;
    
    private Boolean confirme;
    

    public User(int id, String login, String mdp, String email, Role role, Boolean confirme) {
        this.id = id;
        
        this.login = login;
        this.mdp = mdp;
        this.email = email;
       
        this.role = role;
        
        this.confirme = confirme;
    }

    public User() {
    }
    
    public Boolean getConfirme() {
        return confirme;
    }

    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }

    public int getCin() {
        return id;
    }

    public void setCin(int cin) {
        this.id = cin;
    }

    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

  

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

   
    @Override
    public String toString() {
        return "User{" + "cin=" + id + ", login=" + login + ", mdp=" + mdp + ", email=" + email  +  ", role=" + role +  " confirme=" + confirme + '}';
    }
}
