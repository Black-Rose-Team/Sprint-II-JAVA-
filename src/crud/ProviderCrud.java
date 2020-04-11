/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entities.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;
import icrud.IProviderCrud;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Crow
 */
public class ProviderCrud implements IProviderCrud<Provider>{
    
     private static ProviderCrud instance;
     
    public static ProviderCrud getInstance()
    {
        if(instance==null) 
            instance=new ProviderCrud();
        return instance;    
    }

    private Connection con;
    private Statement ste;

    public ProviderCrud() 
    {
        con = DataBase.getInstance().getConnection();
    }

    
    
   /*
    public void ajouter(Provider e) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `cycle`.`Provider` (`cin`,`nom`,`prenom`,`age`,`role`,`salaire`) "
                + "VALUES (NULL, '" + e.getCin() + "', '" + e.getNom()+ "','" + e.getPrenom() + "','" + e.getAge()+ "','" + e.getRole()+ "','" + e.getSalaire()+ "');";
        ste.executeUpdate(requeteInsert);
    }
    */
    
    
     @Override
    public void ajouter(Provider e) throws SQLException
    {
   
       PreparedStatement pre=con.prepareStatement("INSERT INTO `cycle`.`Provider` (`id`,`name`,`mail`,`tel`,`password`) VALUES ( ?, ?, ?, ?,?);");
         
         e.setId(e.getAlphaNumericString(8));
         pre.setString(1, e.getId());
         pre.setString(2, e.getName());
         pre.setString(3, e.getMail());
         pre.setInt(4,e.getTel());
         e.setPassword(e.getRandomNumberInRange(100000000, 999999999));
         pre.setInt(5, e.getPassword());
         
       
       pre.executeUpdate();
       
        sendMAil(e);
            
            
    }
            
    public void sendMAil(Provider e)
    {
       final String username ="youssefxhaffar@gmail.com";
        final String password ="animelover3";
        String fromEmail ="youssefxhaffar@gmail.com";
        String toEmail =e.getMail();
        
        Properties properties =new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session =Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication()
              {
                  return new PasswordAuthentication(username, password);
              }
        });
        
        //Start our mail message
        
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setText("it's our honor that you are  one of our providers .\n" +
                               "this is Your ID :"+e.getId()+".\n"
                             + "this is Your Password :"+e.getPassword()+"");
            Transport.send(msg);
            System.out.println("success");
            
        } catch (MessagingException t) {
            t.printStackTrace();
        }
}

     @Override
    public void delete(Provider  e) throws SQLException 
    {
        String sql = "DELETE FROM `cycle`.`provider` where (id =\'"+e.getId()+"\');";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }
    

    }
    
    
     @Override
    public void update(Provider e) throws SQLException 
    {
          String sql ="UPDATE `cycle`.`Provider` SET `name`='"+e.getName()+ "',`mail`='" +e.getMail()+"',`tel`='" +e.getTel()+"',`password`='" +e.getPassword()+"' WHERE `id`='"+e.getId()+ "' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}

    
   
     @Override
    public List<Provider> readAll() throws SQLException {
    List<Provider> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from provider");
     while (rs.next()) {                
               String id=rs.getString("id");
               int tel=rs.getInt("tel"); 
              int password=rs.getInt("password");     
               String name=rs.getString("name");
               String mail=rs.getString("mail");

               Provider e=new Provider(id,name,mail,tel,password);
               
     arr.add(e);
     }
    return arr;
    }
    
    
    
    public boolean  login(String id ,int password) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "select * from provider where id = ? and password = ?";
         try {
             preparedStatement=con.prepareStatement(sql);
             preparedStatement.setString(1, id);
            preparedStatement.setInt(2,password);
            resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                 return  true;  
             }
             else{
                 return false;
             }
         } catch (SQLException ex) {
             return false;
         }
        
    }
    
}
