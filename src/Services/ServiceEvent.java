/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Utiles.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NahlaJ
 */
public class ServiceEvent {
    
    public static void ajouterEvent(Event e) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();

        try {
            String req = "insert into `event` (`dateevent`, `lieuevent`, `nbrepersonnes`, `capevent`,"
                    + " `nomevent`, `description`, `ticketprice`, `eventImg`) values (?,?,?,?,?,?,?,?)";

            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setDate(1, e.getDateevent());
            pre.setString(2, e.getLieuevent());
            pre.setInt(3, e.getNbrepersonnes());
            pre.setInt(4, e.getCapevent());
            pre.setString(5, e.getNomevent());
            pre.setString(6, e.getDescription());
            pre.setFloat(7, e.getTicketprice());
            pre.setString(8, e.getEventImg());

            pre.executeUpdate();
            System.out.println("event ajouté avec succès");

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex.getMessage());
        }
    }
    
    
    public static void modifierEvent(Event e, int id) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();

        try {
            String req = "UPDATE event SET `dateevent`=?,`lieuevent`=?,`nbrepersonnes`=?,`capevent`=?,"
                    + "`nomevent`=?,`description`=?,`ticketprice`=?,`eventImg`=? WHERE idevent = ?";

            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setDate(1, e.getDateevent());
            pre.setString(2, e.getLieuevent());
            pre.setInt(3, e.getNbrepersonnes());
            pre.setInt(4, e.getCapevent());
            pre.setString(5, e.getNomevent());
            pre.setString(6, e.getDescription());
            pre.setFloat(7, e.getTicketprice());
            pre.setString(8, e.getEventImg());
            pre.setInt(9, id);

            pre.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    
    public static void supprimerEvent(int id) throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();

        try {
            String req = "DELETE from event WHERE idevent = ? ";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id);
            pre.executeUpdate();
            System.out.println("event supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    
    public static List<Event> afficherEvent() throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();      
        List<Event> LE = new ArrayList<>();

        try {
            String req = "select * from event ";
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                LE.add(new Event(
                        rs.getInt("idevent"),
                        rs.getDate("dateevent"),
                        rs.getString("lieuevent"),
                        rs.getInt("nbrepersonnes"),
                        rs.getInt("capevent"),
                        rs.getString("nomevent"),
                        rs.getString("description"),
                        rs.getFloat("ticketprice"),
                        rs.getString("eventImg")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        LE.forEach(System.out::println);
        return LE;
    }
    
    
    public static Event FindEventById(int id) throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();
        Event E = null;        

        try {
            String req = "Select * from event where idevent= ?" ;
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                 E = new Event();
                 E.setIdevent(rs.getInt(1));
                 E.setDateevent(rs.getDate(2));
                 E.setLieuevent(rs.getString(3));
                 E.setNbrepersonnes(rs.getInt(4));
                 E.setCapevent(rs.getInt(5));
                 E.setNomevent(rs.getString(6));
                 E.setDescription(rs.getString(7));
                 E.setTicketprice(rs.getInt(8));
                 E.setEventImg(rs.getString(9)); 
            }           
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(E);
        return E; 
    }   
    
    
    //find event par nom
    public static Event FindEventByName(String nom) throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();
        Event E = null;

     try {
        //List<Event> list = new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from event WHERE nomEvent='" + nom + "'");
        
        while (rs.next()) {
            E = new Event();
                 E.setIdevent(rs.getInt(1));
                 E.setDateevent(rs.getDate(2));
                 E.setLieuevent(rs.getString(3));
                 E.setNbrepersonnes(rs.getInt(4));
                 E.setCapevent(rs.getInt(5));
                 E.setNomevent(rs.getString(6));
                 E.setDescription(rs.getString(7));
                 E.setTicketprice(rs.getInt(8));
                 E.setEventImg(rs.getString(9));
        }
        
     }catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(E);
        return E;
    }
    
    
    
       //trie par date 
    public static List<Event> TrierParDateEvent() throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();
        Event com = null;
        List<Event> LE = new ArrayList<>();
        Statement ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select * from event ORDER BY dateEvent DESC");
        
        while (rs.next()) {
            com = new Event(rs.getInt(1),
                    rs.getDate(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9));
            LE.add(com);
        }
        LE.forEach(System.out::println);
        return LE;
    }
    
    
    public static List<Event> TrierParTicketPriceEvent() throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();
        List<Event> LE = new ArrayList<>();
        Statement ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select * from event ORDER BY ticketprice ASC");
        Event com = null;
        while (rs.next()) {
            com = new Event(rs.getInt(1),
                    rs.getDate(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9));
            LE.add(com);

        }
        LE.forEach(System.out::println);
        return LE;
    }

    
    
    //tri par nom
    public static List<Event> TrierParNomEvent() throws SQLException {

        Connection cnx = DataSource.getInstance().getCnx();
        List<Event> LE = new ArrayList<>();
        Statement ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select * from event ORDER BY nomevent DESC");
        Event com = null;
        while (rs.next()) {
            com = new Event(rs.getInt(1),
                    rs.getDate(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9));
            LE.add(com);

        }
        LE.forEach(System.out::println);
        return LE;
    }
    
    
    
    
}
