/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.User;
import com.esprit.Utils.DataBase;
import com.esprit.Utils.Role;
import com.esprit.Utils.Sexe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import BCrypt.BCrypt;
import com.esprit.Utils.Session;
/**
 *
 * @author OMEN
 */
public class ServiceUser {
      private Connection con = DataBase.getInstance().getConnection();
    private Statement ste;
     public ServiceUser(){
         
     }
      public boolean login(String login,String password){
        ServiceUser repo  = new ServiceUser();
        Optional<User> u = repo.getUserBy(login,password);
        if(u != null){
            Session.start(u.get());
            return true;
        }
        return false;
    }

    public Optional<User> getUserBy(String login , String mdp )
    {
    String requete = "SELECT * FROM fos_user_table"
            + " WHERE ( username = ? )";
        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                User us = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email") , Role.getByCode(rs.getString("roles")) ,  rs.getBoolean("enabled"));
                if (BCrypt.checkpw(mdp , us.getMdp()  )){
                    System.out.println(us);
                    return Optional.ofNullable(us);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
