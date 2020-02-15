/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;
import com.esprit.Entite.Examen;
import com.esprit.IService.IServiceExamen;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BEN SAID
 */
public class ServiceExamen implements IServiceExamen<Examen> {

    private Connection con;
    private Statement ste;

    public ServiceExamen() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Examen t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit`.`examen` (`id_examen`,`id_matiere`,`date_examen`,`coefficient`) VALUES (NULL, '" + t.getId_matiere() + "', '" + t.getDate_examen()+ "','" + t.getCoefficient() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Examen e) throws SQLException
    {
   
       PreparedStatement pre=con.prepareStatement("INSERT INTO `esprit`.`examen` (`id_examen`,`id_matiere`,`date_examen`,`coefficient`) VALUES ( ?, ?, ?,?);");
   pre.setInt(1, e.getId_examen());
       pre.setInt(2,e.getId_matiere());
    pre.setDate(3, e.getDate_examen());
    pre.setFloat(4, e.getCoefficient());
      
   
    pre.executeUpdate();
    }
            


    public void delete(Examen  t) throws SQLException {
        String sql = "DELETE FROM `esprit`.`examen` where (id_examen ="+t.getId_examen()+");";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }
    

    }
    public void update(Examen t) throws SQLException {
          String sql ="UPDATE `esprit`.`examen` SET `id_matiere`='"+t.getId_matiere() + "',`Date_examen`='"+t.getDate_examen() + "' ,`coefficient`='"+t.getCoefficient() + "' WHERE `id_examen`='"+t.getId_examen()+"' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}

   
    public List<Examen> readAll() throws SQLException {
    List<Examen> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from examen");
     while (rs.next()) {                
               int id_examen=rs.getInt(1);
               int id_matiere=rs.getInt("id_matiere");     
                Date date_examen=rs.getDate("date_examen");
               float coefficient=rs.getFloat("coefficient");
              
             
               Examen e=new Examen(id_examen,id_matiere,date_examen,coefficient);
     arr.add(e);
     }
    return arr;
    }
}