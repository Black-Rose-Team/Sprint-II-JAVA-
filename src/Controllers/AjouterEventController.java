/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.ServiceEvent;
import Entities.Event;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author NahlaJ
 */
public class AjouterEventController implements Initializable {

    @FXML
    private DatePicker dateEvent;
    @FXML
    private TextField lieuEvent;
    @FXML
    private TextField capEvent;
    @FXML
    private TextField nomEvent;
    @FXML
    private TextArea description;
    @FXML
    private TextField ticketPrice;
    @FXML
    private Button eventImg;
    @FXML
    private Button ajouterEvent;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Upload(ActionEvent event) {
    }

    @FXML
    private void ajoutEventbtn(ActionEvent event) throws SQLException {
        
        java.sql.Date dateE = java.sql.Date.valueOf( dateEvent.getValue() );
        String lieuE = lieuEvent.getText();
         int capE = Integer.parseInt(capEvent.getText());
        String nomE = nomEvent.getText();
        String descriptionE = description.getText();
        float ticketPriceE = Float.parseFloat(ticketPrice.getText());
        String imageE = eventImg.getText();
        
        
        Event e = new Event(dateE,lieuE,capE,nomE,descriptionE,ticketPriceE,imageE);
        ServiceEvent ev = new ServiceEvent();
        ev.ajouterEvent(e);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Votre evenement est ajouter  avec succeeees");
        alert.setHeaderText("Evenement ajouter");
       
        
        //alert.showAndWait();
        
    }

}
