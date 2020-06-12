/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import crud.ProviderCrud;
import entities.Provider;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Crow
 */
public class LoginController implements Initializable {

    @FXML
    private TextField bId;
    @FXML
    private TextField bPassword;
    @FXML
    private Button bLogin;
    @FXML
    private Label bLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void loginAction(ActionEvent event) 
    {
        
           try {
               ProviderCrud pc = new ProviderCrud();
               
                          if (("admin".equals(bId.getText())) && ("admin".equals(bPassword.getText()))) {  

            Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) bLogin.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.show();

           }
                          
                          
                          else 
                          {
                              try {
                              if (pc.login(bId.getText(),Integer.parseInt(bPassword.getText()))) {
                                  
                                  Stage primaryStage =new Stage();
                                  FXMLLoader loader =new FXMLLoader();
                                  Pane root = loader.load(getClass().getResource("ProviderSpace.fxml").openStream());
                                  ProviderSpaceController ps =(ProviderSpaceController) loader.getController();
                                  ps.GetProvider(bId.getText());
                                  
                                  
                                  Scene scene = new Scene(root);
                                  Stage stage = (Stage) bLogin.getScene().getWindow();
                                  stage.close();
                                  stage.setScene(scene);
                                  stage.show();
                                  bLabel.setText("Success");
                                  
                              }
                              
                              
                              else{
                                  bLabel.setText("Wrong Informations");
                              }
               } catch (SQLException ex) {
                   bLabel.setText("Wrong Informations");
               }}
    
        } catch (IOException ex) {
            
        bLabel.setText("Wrong Informations");
        }
        
        
    }      
}
