/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.VeloARep;
import com.esprit.Service.ServiceVeloARep;
import com.esprit.Utils.Role;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OMEN
 */
public class Afficher_VeloARepController implements Initializable {

    @FXML
    private TableColumn<VeloARep, Integer> vid;
    @FXML
    private TableColumn<VeloARep, String> vb;
    @FXML
    private TableColumn<VeloARep, String> vp;
    @FXML
    private TableColumn<VeloARep, String> vd;
    @FXML
    private TableColumn<VeloARep, Integer> va;
    @FXML
    private TableColumn<VeloARep, String> vs;
    @FXML
    private TableView<VeloARep> tab;
    
         ObservableList<VeloARep> data=FXCollections.observableArrayList();
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button annuler;
    @FXML
    private Button pdf;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            ServiceVeloARep sv= new ServiceVeloARep();
            // List<Command> list = sp.readAll();
            
            
                
            vid.setCellValueFactory(new PropertyValueFactory<VeloARep,Integer>("id"));
            vb.setCellValueFactory(new PropertyValueFactory<VeloARep,String>("marque"));
            vd.setCellValueFactory(new PropertyValueFactory<VeloARep,String>("description"));
            vp.setCellValueFactory(new PropertyValueFactory<VeloARep,String>("probleme"));
            va.setCellValueFactory(new PropertyValueFactory<VeloARep,Integer>("age"));
            vs.setCellValueFactory(new PropertyValueFactory<VeloARep,String>("status"));
            
            
            if(Session.getCurrentUser().get().getRole().equals(Role.ADMIN)){
                data.addAll(sv.readAll());
                tab.setItems(data);
            }else{
                data.addAll(sv.readOnlyUserVelo(Session.getCurrentUser().get().getCin()));
                tab.setItems(data);
            }
            
          
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_VeloARepController.class.getName()).log(Level.SEVERE, null, ex);
        } }
        // TODO
    public void setTab(TableView<VeloARep> tab) {
        this.tab = tab;
    }

    public void setId(TableColumn<VeloARep, Integer> id) {
        this.vid = id;
    }

    public void setMarque(TableColumn<VeloARep, String> Marque) {
        this.vb = Marque;
    }
    
    public void setDescription(TableColumn<VeloARep, String> Description) {
        this.vd = Description;
    }
    
    public void setProbleme(TableColumn<VeloARep, String> Probleme) {
        this.vp = Probleme;
    }
    
    public void setAge(TableColumn<VeloARep, Integer> age) {
        this.va = age;
    }

     public void setStatus(TableColumn<VeloARep, String> status) {
        this.vs = status;
     }

    @FXML
    private void modifier(ActionEvent event) {
          try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Modifier_VeloARep.fxml"));
           Scene scene = new Scene(root);
            Stage stage = (Stage) modifier.getScene().getWindow();
            stage.close();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try {
             VeloARep v = (VeloARep) tab.getSelectionModel().getSelectedItem();
             ServiceVeloARep sv = new ServiceVeloARep();
            // as.delete(e);
             
           //  ArrayList arraylist = (ArrayList) as.afficher(e.getId_employe());
               sv.delete(v);
                      tab.getItems().removeAll(v);
                                  JOptionPane.showMessageDialog(null, "Deleted successfully");


         } catch (SQLException ex) {
             Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void annuler(ActionEvent event) {
         try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Ajouter_VeloARep.fxml"));
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
    private void pdf(ActionEvent event) {
    }
    
}
