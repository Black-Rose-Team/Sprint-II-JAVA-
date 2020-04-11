/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import crud.ChargeCrud;
import crud.ProviderCrud;
import entities.Charge;
import entities.Provider;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crow
 */
public class AffichierChargeController implements Initializable {

    @FXML
    private Button supprimer;
    @FXML
    private Button modifierex;
    @FXML
    private Button annuler;
   
    @FXML
    private TableColumn<Charge, Integer> tfId;
    @FXML
    private TableColumn<Charge, String> tfProvider;
    @FXML
    private TableColumn<Charge, Float> tfPrice;
    @FXML
    private TableColumn<Charge, Date> tfDate;
    @FXML
    private TableColumn<Charge, String> tfState;
     @FXML
    private TableView<Charge> table;
    
    ObservableList<Charge> data=FXCollections.observableArrayList();
    ObservableList<String> options=FXCollections.observableArrayList(); 
    
    @FXML
    private Button bvalider;
    @FXML
    private TextField bId;
   
    @FXML
    private ComboBox<String> bPro;
    
    @FXML
    private DatePicker bDate;
    @FXML
    private TextField bPrice;
    @FXML
    private TextField bState;
    
        ResultSet re=null;
   
               
    
  

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
         
               ChargeCrud se= new ChargeCrud();
           // List<Command> list = sp.readAll();
            data.addAll(se.readAll());            
            tfId.setCellValueFactory(new PropertyValueFactory<Charge,Integer>("id"));
            tfProvider.setCellValueFactory(new PropertyValueFactory<Charge,String>("provider"));
            tfState.setCellValueFactory(new PropertyValueFactory<Charge,String>("state"));
            tfPrice.setCellValueFactory(new PropertyValueFactory<Charge,Float>("price"));
            tfDate.setCellValueFactory(new PropertyValueFactory<Charge,Date>("date"));
            table.setItems(data);
            options.addAll(se.readProvider());
            bPro.setItems(options);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
           
    }
    
    
    
     public void setTab(TableView<Charge> table) {
        this.table = table;
    }

    public void setId(TableColumn<Charge, Integer> id) {
        this.tfId = id;
    }

    public void setState(TableColumn<Charge, String> state) {
        this.tfState = state;
    }

    public void setPrice(TableColumn<Charge, Float> price) {
        this.tfPrice = price;
    }
    
    public void setDate(TableColumn<Charge, Date> date) {
        this.tfDate = date;
    } 
    
    public void setProvider(TableColumn<Charge, String> cprovider) {
        this.tfProvider = cprovider;
    } 
  
  
     
   

    @FXML
    private void supprimer(ActionEvent event) {
        
        try {
             Charge c = (Charge) table.getSelectionModel().getSelectedItem();
             ChargeCrud sc = new ChargeCrud();
            // as.delete(e);
             
           //  ArrayList arraylist = (ArrayList) as.afficher(e.getId_Charge());
               sc.delete(c);
                      table.getItems().removeAll(c);

         } catch (SQLException ex) {
             Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
        public static Integer id;
        public static String state;
        public static Float price;
        public static String cprovider;
        public static Date date;
        
        
           
    @FXML
    private void modifierex(ActionEvent event) {
       try {
           
                int iid=Integer.parseInt(bId.getText());
                String istate=bState.getText();
                String iprovider=bPro.getValue();
                float iprice=Float.parseFloat(bPrice.getText());
                Date idate=Date.valueOf(bDate.getValue());

                
            
            Charge e= new Charge(iid,iprice,istate,idate,iprovider);
             ChargeCrud ec= new ChargeCrud(); 
            ec.update(e);
            
            
            bId.clear();
            bPrice.clear();
           
            bState.clear();

            
             
            
            data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readAll());
            table.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
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
                String iprovider=bPro.getValue();
                Date idate=Date.valueOf(bDate.getValue());
            

            
            
            
            Charge e= new Charge(idate,iprovider);
             ChargeCrud ec= new ChargeCrud(); 
            ec.ajouter(e);
            
            bPrice.clear();
            bState.clear();
            
             data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readAll());
            table.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void fill(javafx.scene.input.MouseEvent event) {
       Charge e =table.getSelectionModel().getSelectedItem();
        bId.setText(String.valueOf(e.getId()));
        bDate.setValue(e.getDate().toLocalDate());
        bPrice.setText(String.valueOf(e.getPrice()));
        bState.setText(String.valueOf(e.getState()));
        bPro.setValue(String.valueOf(e.getProvider()));
   

    }
    
}
