/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.gui;

import com.esprit.Service.ServiceUser;
import com.esprit.Utils.DataBase;
import com.esprit.Utils.Role;
import com.esprit.Utils.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author BEN SAID
 */
public class AcceuilController implements Initializable {
   
    @FXML
    private Button tfV;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfpwd;
    
     private Connection con;
     private Statement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
        // TODO
    }  



    @FXML
    private void gestionV(ActionEvent event) throws SQLException {
      
        ServiceUser us = new ServiceUser();
        
        
        if (us.login(tfname.getText(), tfpwd.getText())){
            if (Session.getCurrentUser().get().getRole().equals(Role.ADMIN)){
                try {
                    Parent blah = FXMLLoader.load(getClass().getResource("Afficher_Reparateur.fxml"));
                    
                Notifications notification = Notifications.create();
                         notification.title("Cycle");
                         notification.text("Admin, you have gone ONLINE");
                         Platform.runLater(() -> notification.showInformation());
                
                    Scene scene = new Scene(blah);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                     
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{
            try {
                    Parent blah = FXMLLoader.load(getClass().getResource("Ajouter_VeloARep.fxml"));
                    
                         
                Notifications notification = Notifications.create();
                         notification.title("Cycle");
                         notification.text("You have gone ONLINE, have a good day!");
                         Platform.runLater(() -> notification.showInformation());
                    Scene scene = new Scene(blah);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                     
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
             System.out.println("login fails");
        }
         
    }
    
}
 