/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.Service;
import com.esprit.Entite.Reparateur;
import com.esprit.IService.IServiceReparateur;
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
public class ServiceReparateur implements IServiceReparateur<Reparateur> {
    
    private static ServiceReparateur instance;
    public static ServiceReparateur getInstance() {
   if(instance==null) 
            instance=new ServiceReparateur();
        return instance;    }

    private Connection con;
    private Statement ste;

    public ServiceReparateur() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Reparateur e) throws SQLException
    {
   
       PreparedStatement pre=con.prepareStatement("INSERT INTO `coco`.`reparateur` (`id`, `Nom`, `Prenom`, `NumTel`, `nbr_velo_repare`, `Experience`, `status`, `image` ) VALUES ( ?, ?, ?, ?, ?, ?, 'Libre', ?);");
       pre.setInt(1, e.getId());
       pre.setString(2,e.getNom());
       pre.setString(3, e.getPrenom());
       pre.setInt(4, e.getNumTel());
       pre.setInt(5, e.getNbr_velo_arep());
       pre.setInt(6, e.getExperience());
       pre.setString(7, e.getImage());


      
   
    pre.executeUpdate();
    }
            


    public void delete(Reparateur  t) throws SQLException {
        String sql = "DELETE FROM `coco`.`reparateur` where (id ="+t.getId()+");";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }
    

    }
    public void update(Reparateur t) throws SQLException {
          String sql ="UPDATE `coco`.`reparateur` SET `Nom`='"+t.getNom() + "',`Prenom`='"+t.getPrenom() + "' ,`NumTel`='"+t.getNumTel() + "' ,`nbr_velo_repare`='"+t.getNbr_velo_arep() + "' ,`Experience`='"+t.getExperience() + "' ,`status`='"+t.getStatus() + "' ,`image`='"+t.getImage() + "' WHERE `id`='"+t.getId()+"' ";
  
    try {
            Statement stl = con.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
    }}
    
   
    public List<Reparateur> readAll() throws SQLException {
    List<Reparateur> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reparateur");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String Nom=rs.getString("Nom");
               String Prenom=rs.getString("Prenom");
               int NumTel=rs.getInt("NumTel");
               int nbr_velo_arep=rs.getInt("nbr_velo_repare");
               int Experience=rs.getInt("Experience");
               String status=rs.getString("status");
               String image=rs.getString("image");

              
             
               Reparateur e=new Reparateur(id,Nom,Prenom,NumTel,nbr_velo_arep,Experience,status,image);
     arr.add(e);
     }
    return arr;
    }
     public Reparateur rechercheReparateur(int id)
 {  Reparateur S = new Reparateur();
        

            

           try {
            
                 String req4="SELECT * FROM coco.`reparateur` WHERE id_classe='" + id + "';";
          Statement st = con.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
           while(rs.next()){
              
                S.setId(rs.getInt("id"));
                S.setNom(rs.getString("Nom"));
                S.setPrenom(rs.getString("Prenom"));
                S.setNumTel(rs.getInt("NumTel"));
                S.setNbr_velo_arep(rs.getInt("nbr_velo_repare"));
                S.setExperience(rs.getInt("Experience"));
                S.setStatus(rs.getString("status"));


                  System.out.println("Searched");
             
   
               
            }
           
            return S;
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReparateur.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return null;
 }
}
   