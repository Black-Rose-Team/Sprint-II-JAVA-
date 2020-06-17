/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.VeloARep;
import com.esprit.Service.ServiceVeloARep;
import com.esprit.Utils.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OMEN
 */
public class Ajouter_VeloARepController implements Initializable {

    @FXML
    private TextField tfMarque;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfD;
    @FXML
    private Button bvalider;
    @FXML
    private TextField tfP;
   
    @FXML
    private Button annuler;
    @FXML
    private Button show;
    @FXML
    private ComboBox<String> combo1;
    
    ObservableList<String> options=FXCollections.observableArrayList();
    @FXML
    private Label usr;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    ServiceVeloARep sv= new ServiceVeloARep(); 
        try {
            options.addAll(sv.readReparateur());
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_VeloARepController.class.getName()).log(Level.SEVERE, null, ex);
        }
            combo1.setItems(options);
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
          try {
                String Marque=tfMarque.getText();
                String Description=tfD.getText();
                String Probleme=tfP.getText();
                int age=Integer.parseInt(tfAge.getText());
                String status="";
                int id_reparateur=Integer.parseInt((String) combo1.getValue());
            

            
            
            
            System.out.println(Marque);
            VeloARep v= new VeloARep(Marque,Description,Probleme,age,status,id_reparateur);
            v.setUser_id(Session.getCurrentUser().get().getCin());
            ServiceVeloARep sv= new ServiceVeloARep(); 
            sv.ajouter(v);
            JOptionPane.showMessageDialog(null, "Added successfully");

        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_VeloARepController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void annuler(ActionEvent event) {
           try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
           Scene scene = new Scene(root);
            Stage stage = (Stage) annuler.getScene().getWindow();
            stage.close();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void show(ActionEvent event) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Afficher_VeloARep.fxml"));
           Scene scene = new Scene(root);
            Stage stage = (Stage) annuler.getScene().getWindow();
            stage.close();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
        
    }
    
}
