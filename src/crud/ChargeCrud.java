/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entities.Charge;
import icrud.IChargeCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;
import java.sql.Date;

/**
 *
 * @author Crow
 */
public class ChargeCrud implements IChargeCrud<Charge>{
    
     private static ChargeCrud instance;
     
    public static ChargeCrud getInstance()
    {
        if(instance==null) 
            instance=new ChargeCrud();
        return instance;    
    }

    private Connection con;
    private Statement ste;

    public ChargeCrud() 
    {
        con = DataBase.getInstance().getConnection();
    }

    
    
   /*
    public void ajouter(Charge e) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `cycle`.`Charge` (`cin`,`nom`,`prenom`,`age`,`role`,`salaire`) "
                + "VALUES (NULL, '" + e.getCin() + "', '" + e.getNom()+ "','" + e.getPrenom() + "','" + e.getAge()+ "','" + e.getRole()+ "','" + e.getSalaire()+ "');";
        ste.executeUpdate(requeteInsert);
    }
    */
    
    
     @Override
    public void ajouter(Charge e) throws SQLException
    {
   
       PreparedStatement pre=con.prepareStatement("INSERT INTO `cycle`.`Charge` (`date`,`provider`) VALUES ( ?, ?);");
         pre.setDate(1, e.getDate());
         pre.setString(2, e.getProvider());
         
         
      
   
       pre.executeUpdate();
    }
            


     @Override
    public void delete(Charge  e) throws SQLException 
    {
        String sql = "DELETE FROM `cycle`.`Charge` where (id ="+e.getId()+");";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }
    

    }
    
    
     @Override
    public void update(Charge e) throws SQLException 
    {
          String sql ="UPDATE `cycle`.`charge` SET `date`='"+e.getDate()+ "',`provider`='" +e.getProvider()+"' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}

    
   
     @Override
    public List<Charge> readAll() throws SQLException {
    List<Charge> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Charge ");
     while (rs.next()) {                
               int id=rs.getInt("id");
               float price=rs.getFloat("price");     
               String cprovider=rs.getString("provider");
               String state=rs.getString("state");
               Date date=rs.getDate("date");

               Charge e=new Charge(id,price,state,date,cprovider);
     arr.add(e);
     }
    return arr;
    }
    
    public List<Charge> readSome(String idp) throws SQLException {
          System.out.println(idp+"ya3tik 3asba :p");
             List<Charge> arr=new ArrayList<>();
             PreparedStatement preparedStatement;
             ResultSet resultSet;
           
             String sql = "select * from charge where provider = ? ";
       
             preparedStatement=con.prepareStatement(sql);
             preparedStatement.setString(1, idp);
             resultSet = preparedStatement.executeQuery();
     while (resultSet.next()) {                
                int id=resultSet.getInt("id");
               float price=resultSet.getFloat("price");     
               String cprovider=resultSet.getString("provider");
               String state=resultSet.getString("state");
               Date date=resultSet.getDate("date");
               Charge e=new Charge(id,price,state,date,cprovider);
               arr.add(e);
     }
   return arr;     
    }
    
    
    public List<String> readProvider() throws SQLException {
    List<String> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select id from provider ");
     while (rs.next()) {                
           
               String id=rs.getString("id");
               
            
     arr.add(id);
     }
    return arr;
    }
    
    public void accept(Charge e) throws SQLException 
    {
          String sql ="UPDATE `cycle`.`charge` SET `state`='Accepted' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    
    public void refuse(Charge e) throws SQLException 
    {
          String sql ="UPDATE `cycle`.`charge` SET `state`='Refused' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    
    
}
