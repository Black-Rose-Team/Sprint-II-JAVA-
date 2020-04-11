/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import crud.ProviderCrud;
import entities.Provider;
import java.awt.event.MouseEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.table.TableModel;

/**
 * FXML Controller class
 *
 * @author Crow
 */
public class AffichierProviderController implements Initializable {
    
    @FXML
    private TableColumn<Provider,String> tfId;
    @FXML
    private TableColumn<Provider,String> tfName;
    @FXML
    private TableColumn<Provider,String> tfMail;
    @FXML
    private TableColumn<Provider,Integer> tfTel;
    @FXML
    private TableColumn<Provider, Integer> tfPassword;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifierex;
    @FXML
    private Button annuler;
    ObservableList<Provider> data=FXCollections.observableArrayList();
    @FXML
    private TableView<Provider> table;
    @FXML
    private Button bvalider;
     @FXML
    private TextField bId;
    @FXML
    private TextField bMail;
    @FXML
    private TextField bTel;
    @FXML
    private TextField bName;
    @FXML
    private TextField bPassword;
   
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
               ProviderCrud se= new ProviderCrud();
           // List<Command> list = sp.readAll();
            
            data.addAll(se.readAll());
            
            tfId.setCellValueFactory(new PropertyValueFactory<Provider,String>("id"));
            tfMail.setCellValueFactory(new PropertyValueFactory<Provider,String>("mail"));
            tfName.setCellValueFactory(new PropertyValueFactory<Provider,String>("name"));
            tfTel.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("tel"));
            tfPassword.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("password"));

           
            table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
           
    }
    
    
    
     public void setTab(TableView<Provider> table) {
        this.table = table;
    }

    public void setId(TableColumn<Provider, String> id) {
        this.tfId = id;
    }

    public void setName(TableColumn<Provider, Integer> tel) {
        this.tfTel = tel;
    }

    public void setMail(TableColumn<Provider, String> mail) {
        this.tfMail = mail;
    }
    
     public void setTel(TableColumn<Provider, String> name) {
        this.tfName = name;
    } 
     
     public void setPassword(TableColumn<Provider, Integer> password) {
        this.tfPassword = password;
    } 
  
   
     
   

    @FXML
    private void supprimer(ActionEvent event) {
        
        try {
             Provider c =  table.getSelectionModel().getSelectedItem();
             ProviderCrud sc = new ProviderCrud();
            // as.delete(e);
             
           //  ArrayList arraylist = (ArrayList) as.afficher(e.getId_Provider());
               sc.delete(c);
               table.getItems().removeAll(c);

         } catch (SQLException ex) {
             Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
        public static String id;
        public static Integer name;
        public static String mail;
        public static String tel;
        public static String password;

        
           
    @FXML
    private void modifierex(ActionEvent event) {
       try {
           
                String iid=bId.getText();
                int itel=Integer.parseInt(bTel.getText());
                String iname=bName.getText();
                String imail=bMail.getText();
                int ipassword=Integer.parseInt(bPassword.getText());

                
            
            Provider e= new Provider(iid,iname,imail,itel,ipassword);
             ProviderCrud ec= new ProviderCrud(); 
            ec.update(e);
            bMail.clear();
            bName.clear();
            bTel.clear();
            bPassword.clear();
           
            
             
            
            data.clear();
            ProviderCrud se= new ProviderCrud();
            data.addAll(se.readAll());
            table.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void ajouter(ActionEvent event) {
        try {
                
                int atel=Integer.parseInt(bTel.getText());
                String aname=bName.getText();
                String amail=bMail.getText();
            

            
            
            
            Provider e= new Provider(aname,amail,atel);
             ProviderCrud ec= new ProviderCrud(); 
            ec.ajouter(e);
            bMail.clear();
            bName.clear();
            bTel.clear();
            
            
             data.clear();
            ProviderCrud se= new ProviderCrud();
            data.addAll(se.readAll());
            table.setItems(data);
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void fill(javafx.scene.input.MouseEvent event) {
        Provider e =table.getSelectionModel().getSelectedItem();
        bId.setText(String.valueOf(e.getId()));
        bTel.setText(String.valueOf(e.getTel()));
        bMail.setText(e.getMail());
        bName.setText(e.getName());
        bPassword.setText(String.valueOf(e.getPassword()));

   

    }
    
}
