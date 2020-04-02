/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionevent;

import Entities.Event;
import Entities.ReservationEvent;
import Services.ServiceEvent;
import Services.ServiceReservationEvent;
import Utiles.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author NahlaJ
 */
public class GestionEvent extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AjouterEvent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            
            
       /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //WidgetsFlutterBinding.ensureInitialized();
        launch(args);
        //DataSource ds = DataSource.getInstance();
        //Connection cnx = DataSource.getInstance().getCnx();
        
        
        //Event e1 = new Event(new Date(2020, 10, 03), "Hawaria", 8, "be there!", "jhgfds", 15, "esrdtf");
        //Event e2 = new Event(new Date(2020, 03, 11), "Rafraf", 17, "Perfection", "il est temps", 10, "szqes");
        //ServiceEvent ev = new ServiceEvent();
        
            //ev.ajouterEvent(e1);
            //ev.modifierEvent(e1,17);
            //ev.supprimerEvent(17);
            //List<Event> L = ev.afficherEvent();
            //Event e = ev.FindEventById(3);
            //Event f = ev.FindEventByName("Perfection");
            //List<Event> T = ev.TrierParDateEvent();
            //List<Event> P = ev.TrierParTicketPriceEvent();
            //List<Event> N = ev.TrierParNomEvent();
            
            
            
            //ServiceReservationEvent res = new ServiceReservationEvent();
        
            //boolean l =res.participerEvent(19,6);
            //boolean an =res.annulerParticipEvent(19,6);
            //List<ReservationEvent> LRE = res.DisplayReservationByUser(6);
       
            
            
    }
    
}
