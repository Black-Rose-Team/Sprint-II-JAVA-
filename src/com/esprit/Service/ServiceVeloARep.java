/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;
import com.esprit.Entite.VeloARep;
import com.esprit.IService.IServiceVeloARep;
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
public class ServiceVeloARep implements IServiceVeloARep<VeloARep> {
    
    private static ServiceVeloARep instance;
    public static ServiceVeloARep getInstance() {
   if(instance==null) 
            instance=new ServiceVeloARep();
        return instance;    }

    private Connection con;
    private Statement ste;

    public ServiceVeloARep() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(VeloARep v) throws SQLException
    {
   
    PreparedStatement pre=con.prepareStatement("INSERT INTO `coco`.`velo_a_reparer` (`id`, `marque`, `description`, `probleme`, `age`, `status`,`reparateur_id`,user_id ) VALUES ( ?,?, ?, ?, ?, 'Work In Progress', ?,?);");
    pre.setInt(1, v.getId());
    pre.setString(2, v.getMarque());
    pre.setString(3, v.getDescription());
    pre.setString(4, v.getProbleme());
    pre.setInt(5, v.getAge());
    pre.setInt(6, v.getReparateur_id());
    pre.setInt(7, v.getUser_id());
    pre.executeUpdate();
    
    PreparedStatement pr=con.prepareStatement("UPDATE reparateur SET status='Occupé', nbr_velo_repare = nbr_velo_repare+1 WHERE id = (?)");
    
    pr.setInt(1, v.getReparateur_id());
   
    pr.executeUpdate();
    
   
    }
            


    public void delete(VeloARep  v) throws SQLException {
        String sql = "DELETE FROM `coco`.`velo_a_reparer` where (id ="+v.getId()+");";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }
    

    }
    public void update(VeloARep v) throws SQLException {
          String sql ="UPDATE `coco`.`velo_a_reparer` SET `marque`='"+v.getMarque() + "',`Description`='"+v.getDescription() + "' ,`Probleme`='"+v.getProbleme() + "',`age`='"+v.getAge() + "',`status`='"+v.getStatus() + "',`id_reparateur`='"+v.getReparateur_id() + "' WHERE `id`='"+v.getId()+"' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
public void setRep(int id) throws SQLException {
          String sql ="UPDATE `coco`.`velo_a_reparer` SET `reparateur_id` = null , `status` = 'done' WHERE `reparateur_id`="+id;
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
   public void updateRepStatus(int id) throws SQLException {
          String sql ="UPDATE `coco`.`reparateur` SET `status` = 'Libre'  WHERE `id`="+id;
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    public List<VeloARep> readAll() throws SQLException {
    List<VeloARep> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from velo_a_reparer ");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String Marque=rs.getString("marque");
               String description=rs.getString("description");
               String probleme=rs.getString("probleme");
               int age=rs.getInt("age");
               String status=rs.getString("status");
               int reparateur_id=rs.getInt("reparateur_id");
             
               VeloARep e=new VeloARep(id,Marque,description,probleme,age,status,reparateur_id);
     arr.add(e);
     }
    return arr;
    }
    public List<VeloARep> readOnlyUserVelo(int idu) throws SQLException {
    List<VeloARep> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from velo_a_reparer where user_id =  "+idu);
     while (rs.next()) {                
               int id=rs.getInt("id");
               String Marque=rs.getString("marque");
               String description=rs.getString("description");
               String probleme=rs.getString("probleme");
               int age=rs.getInt("age");
               String status=rs.getString("status");
               int reparateur_id=rs.getInt("reparateur_id");
             
               VeloARep e=new VeloARep(id,Marque,description,probleme,age,status,reparateur_id);
     arr.add(e);
     }
    return arr;
    }
   public List<String> readReparateur() throws SQLException {
    List<String> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select id from reparateur where status='Libre' ");
     while (rs.next()) {                
           
               String id=rs.getString("id");
               
            
     arr.add(id);
     }
    return arr;
    }
}