/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import crud.ChargeCrud;
import entities.Charge;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Crow
 */
public class ProviderSpaceController implements Initializable {

    @FXML
    private TableColumn<Charge, Integer> tfId;
    @FXML
    private TableColumn<Charge, Float> tfPrice;
    @FXML
    private TableColumn<Charge, Date> tfDate;
    @FXML
    private TableColumn<Charge, String> tfState;
     @FXML
    private TableView<Charge> table;
    
    ObservableList<Charge> data=FXCollections.observableArrayList();
    @FXML
    private Label lporovider;
    @FXML
    private Button bSee;
    @FXML
    private Button baccept;
    @FXML
    private Button brefuse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      table.setOpacity(0);
    }    

    public void GetProvider(String p)
    {
        lporovider.setText(p);
    }
    @FXML
    private void fill(MouseEvent event) {
    }

    @FXML
    private void seeAction(ActionEvent event) {
        
          try {
              bSee.setOpacity(0);
              table.setOpacity(100);
               ChargeCrud se= new ChargeCrud();
           // List<Command> list = sp.readAll();
            data.addAll(se.readSome(lporovider.getText()));            
            tfId.setCellValueFactory(new PropertyValueFactory<Charge,Integer>("id"));
            tfState.setCellValueFactory(new PropertyValueFactory<Charge,String>("state"));
            tfPrice.setCellValueFactory(new PropertyValueFactory<Charge,Float>("price"));
            tfDate.setCellValueFactory(new PropertyValueFactory<Charge,Date>("date"));
            table.setItems(data);
            
            bSee.setDisable(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML 
    private void acceptAction(ActionEvent event) {
        try {
            Charge c = (Charge) table.getSelectionModel().getSelectedItem();
            ChargeCrud sc = new ChargeCrud();
            sc.accept(c);
            
            data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readSome(lporovider.getText()));
            table.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProviderSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML 
    private void refuseAction(ActionEvent event) {
        try {
            Charge c = (Charge) table.getSelectionModel().getSelectedItem();
            ChargeCrud sc = new ChargeCrud();
            sc.refuse(c);
            
            data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readSome(lporovider.getText()));
            table.setItems(data);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProviderSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    
}
