/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.gui;
import com.esprit.Entite.Reparateur;
import com.esprit.Service.ServiceReparateur;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BEN SAID
 */
public class Modifier_ReparateurController implements Initializable {
    @FXML
    private Button modifier;
    private Button annulere;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNumTel;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNbr_velo_arep;
    @FXML
    private TextField tfExperience;
    @FXML
    private TextField tfstatus;
    @FXML
    private Button annuler;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
       
       
        int id=Integer.parseInt(tfId.getText());
        String Nom=tfNom.getText();
        String Prenom=tfPrenom.getText();
        int NumTel=Integer.parseInt(tfNumTel.getText());
        int nbr_velo_arep=Integer.parseInt(tfNbr_velo_arep.getText());
        int Experience=Integer.parseInt(tfExperience.getText());
        String status=tfstatus.getText();


      
        Reparateur re = new Reparateur(id,Nom,Prenom,NumTel,nbr_velo_arep,Experience,status);
        ServiceReparateur e =ServiceReparateur.getInstance();


e.update(re);
            JOptionPane.showMessageDialog(null, "Updated successfully");

    }
 
    
   


    @FXML
    private void annuler(ActionEvent event) {
              try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Afficher_Reparateur.fxml"));
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
