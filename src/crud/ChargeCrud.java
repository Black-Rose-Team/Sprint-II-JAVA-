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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
   
       PreparedStatement pre=con.prepareStatement("INSERT INTO `coco`.`Charge` (`date`,`provider`,`datem`) VALUES ( ?, ?, ?);");
         pre.setDate(1, e.getDate());
         pre.setString(2, e.getProvider());
         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
         String strDate = dateFormat.format(e.getDate());  
         pre.setString(3, strDate);
       pre.executeUpdate();
    }
            


     @Override
    public void delete(Charge  e) throws SQLException 
    {
        String sql = "DELETE FROM `coco`.`Charge` where (id ="+e.getId()+");";
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
          String sql ="UPDATE `coco`.`charge` SET `date`='"+e.getDate()+ "',`provider`='" +e.getProvider()+"' WHERE `id`='"+e.getId()+ "' ";
  
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
    
    public void accept(Charge e,float pr) throws SQLException 
    {
          String sql ="UPDATE `coco`.`charge` SET `state`='Accepted' , `price`='"+pr+"' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    
    public void refuse(Charge e) throws SQLException 
    {
          String sql ="UPDATE `coco`.`charge` SET `state`='Refused' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    
    public List<Charge> tri(String status) throws SQLException {
          System.out.println(status+"ya3tik 3asba :p");
             List<Charge> arr=new ArrayList<>();
             PreparedStatement preparedStatement;
             ResultSet resultSet;
           
             String sql = "select * from charge where state = ? ";
       
             preparedStatement=con.prepareStatement(sql);
             preparedStatement.setString(1, status);
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
    
     public List<Charge> trix(String status,String idp) throws SQLException {
          System.out.println(status+"ya3tik 3asba :p");
             List<Charge> arr=new ArrayList<>();
             PreparedStatement preparedStatement;
             ResultSet resultSet;
           
             String sql = "select * from charge where state = ? and provider = ? ";
       
             preparedStatement=con.prepareStatement(sql);
             preparedStatement.setString(1, status);
             preparedStatement.setString(2, idp);
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
    
    
}
