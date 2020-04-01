/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ReservationEvent;
import Utiles.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NahlaJ
 */
public class ServiceReservationEvent {
    
    
     public static boolean participerEvent(int id_event,int id_user) throws SQLException {
            Connection cnx = DataSource.getInstance().getCnx();
            
        try {
                     String req = "insert into reservationevent (id_event,id_user)"
                         + " values" + " (?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id_event);
            pre.setInt(2, id_user);

            pre.executeUpdate();
            System.out.println("reservation event effectuée avec succès");
            return true;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            return false;
        }
        }
     
     
     
     
    
     public static boolean annulerParticipEvent(int id_event,int id_user) throws SQLException {
            Connection cnx = DataSource.getInstance().getCnx();
            
        try {
                String req = "DELETE from reservationevent WHERE id_event= ? and id_user=? ";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id_event);
            pre.setInt(2, id_user);

            pre.executeUpdate();
            System.out.println("reservation event annulée avec succès");
            return true;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors d'annulation " + ex.getMessage());
            return false;
        }
    }
     
     
     
     
     
     public static List<ReservationEvent> DisplayReservationByUser(int id) throws SQLException {
         Connection cnx = DataSource.getInstance().getCnx();
         List<ReservationEvent> listereservations = new ArrayList<ReservationEvent>();
         
         try {
             String req = ("select R.* from reservationevent R  join fos_user_table u on R.id_user=u.id where id_user=? ");
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs =  pre.executeQuery();

            while (rs.next()) {
                ReservationEvent reservation = new ReservationEvent(rs.getInt("id"),
                rs.getInt("id_event"),rs.getInt("id_user"));
                listereservations.add(reservation);
            }
            listereservations.forEach(System.out::println);
            return listereservations;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des events " + ex.getMessage());
            return null;
        }
    }
     
     
     
}
