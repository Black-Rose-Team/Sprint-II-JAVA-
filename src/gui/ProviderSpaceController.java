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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
     
     @FXML
    private TableView<Provider> tablep;
    
    ObservableList<Charge> data=FXCollections.observableArrayList();
    ObservableList<Charge> datax=FXCollections.observableArrayList();
    ObservableList<Provider> datap=FXCollections.observableArrayList();

    ObservableList<String> statex=FXCollections.observableArrayList("All","Not Seen","Accepted","Refused"); 

    @FXML
    private Label lporovider;
    @FXML
    private Button bSee;
    @FXML
    private Button baccept;
    @FXML
    private Button brefuse;
    @FXML
    private TextField bid;
    @FXML
    private TextField bname;
    @FXML
    private TextField bmail;
    @FXML
    private TextField bpwd;
    @FXML
    private TextField btel;
    @FXML
    private Button bseep;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private TableColumn<Provider, String> a1;
    @FXML
    private TableColumn<Provider, String> a2;
    @FXML
    private TableColumn<Provider, String> a3;
    @FXML
    private TableColumn<Provider, Integer> a4;
    @FXML
    private TableColumn<Provider, Integer> a5;
    @FXML
    private Button bedit;
    @FXML
    private ComboBox<String> c7;
    @FXML
    private TextField tfpr;
    @FXML
    private Label l55;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      table.setOpacity(0);
      baccept.setOpacity(0);
      brefuse.setOpacity(0);
      bid.setOpacity(0);
      bmail.setOpacity(0);
      bname.setOpacity(0);
      bpwd.setOpacity(0);
      btel.setOpacity(0);
      l1.setOpacity(0);
      l2.setOpacity(0);
      l3.setOpacity(0);
      l4.setOpacity(0);
      l5.setOpacity(0);
      tfpr.setOpacity(0);
      c7.setOpacity(0);
      l55.setOpacity(0);
      tablep.setOpacity(0);
      


      

    }  
    
    
    @FXML
    private void edit(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Editing Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to edit your Profile ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
        try {
           
                String iid=bid.getText();
                String iname= bname.getText();
                String imail= bmail.getText();
                int itel=Integer.parseInt(btel.getText());
                int ipassword=Integer.parseInt(bpwd.getText());

                
            
            Provider e= new Provider(iid,iname,imail,itel,ipassword);
            ProviderCrud ec= new ProviderCrud(); 
            ec.update(e);
            
            bid.clear();
            bmail.clear();
            bname.clear();
            btel.clear();
            bpwd.clear();
           
            
             
            
            datap.clear();
            ProviderCrud se= new ProviderCrud();
            datap.addAll(se.readSome(lporovider.getText()));            
            tablep.setItems(datap);
            Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Profile Has been updated !");
        alert2.showAndWait();
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
    }

    public void GetProvider(String p)
    {
        lporovider.setText(p);
    }
    
    @FXML
    private void fill(MouseEvent event) {
         Provider e =tablep.getSelectionModel().getSelectedItem();
        bid.setText(String.valueOf(e.getId()));
        bmail.setText(String.valueOf(e.getMail()));
        bname.setText(String.valueOf(e.getName()));
        btel.setText(String.valueOf(e.getTel()));
        bpwd.setText(String.valueOf(e.getPassword()));
    }

   /* @FXML
    private void fillp(MouseEvent event) {
         Charge e =table.getSelectionModel().getSelectedItem();
        idpr.setText(String.valueOf(e.getId()));
        
    }*/
    
    @FXML
    private void seeAction(ActionEvent event) {
        
          try {
              tfpr.setOpacity(100);
      c7.setOpacity(100);
      l55.setOpacity(100);
              bSee.setOpacity(0);
              table.setOpacity(100);
              baccept.setOpacity(100);
              brefuse.setOpacity(100);
               ChargeCrud se= new ChargeCrud();
           // List<Command> list = sp.readAll();
            data.addAll(se.readSome(lporovider.getText()));            
            tfId.setCellValueFactory(new PropertyValueFactory<Charge,Integer>("id"));
            tfState.setCellValueFactory(new PropertyValueFactory<Charge,String>("state"));
            tfPrice.setCellValueFactory(new PropertyValueFactory<Charge,Float>("price"));
            tfDate.setCellValueFactory(new PropertyValueFactory<Charge,Date>("date"));
            table.setItems(data);
            c7.setItems(statex);

            
            bSee.setDisable(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML 
    private void acceptAction(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to Accept this Charge ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
        try {
            if (!tfpr.getText().equals("")) {
                Charge c = (Charge) table.getSelectionModel().getSelectedItem();
            ChargeCrud sc = new ChargeCrud();
            float pr=Float.valueOf(tfpr.getText());
            sc.accept(c,pr);
            
            data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readSome(lporovider.getText()));
            table.setItems(data);
            Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Charge Has been Accepted !");
        alert2.showAndWait();
            }
            else{
                Alert alert2 =new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Warning dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("You must put a price !!! ");
        alert2.showAndWait(); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProviderSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
    }
    @FXML 
    private void refuseAction(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to Refuse this Charge ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
  
        try {
            Charge c = (Charge) table.getSelectionModel().getSelectedItem();
            ChargeCrud sc = new ChargeCrud();
            sc.refuse(c);
            
            data.clear();
            ChargeCrud se= new ChargeCrud();
            data.addAll(se.readSome(lporovider.getText()));
            table.setItems(data);
            
            Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Charge Has been Refused !");
        alert2.showAndWait();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProviderSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
    }

    @FXML
    private void seeProfile(ActionEvent event) {
        
    
       try {
                 tablep.setOpacity(100);

           tablep.selectionModelProperty().getValue().isSelected(1);
              bseep.setOpacity(0);
              bid.setOpacity(100);
      bmail.setOpacity(100);
      bname.setOpacity(100);
      bpwd.setOpacity(100);
      btel.setOpacity(100);
      l1.setOpacity(100);
      l2.setOpacity(100);
      l3.setOpacity(100);
      l4.setOpacity(100);
      l5.setOpacity(100);
    

               ProviderCrud se= new ProviderCrud();
           // List<Command> list = sp.readAll();
            datap.addAll(se.readSome(lporovider.getText()));            
            a1.setCellValueFactory(new PropertyValueFactory<Provider,String>("id"));
            a2.setCellValueFactory(new PropertyValueFactory<Provider,String>("name"));
            a3.setCellValueFactory(new PropertyValueFactory<Provider,String>("mail"));
            a4.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("tel"));
            a5.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("password"));
            tablep.setItems(datap);
            
            bseep.setDisable(true);
       

            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    

    /*@FXML
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
    }*/

   @FXML
    private void tri(ActionEvent event) {
        
        try {
            datax.clear();
            ChargeCrud se= new ChargeCrud();
            // List<Command> list = sp.readAll();
            if(c7.getSelectionModel().getSelectedItem().equals("All"))
            {
                datax.addAll(se.readSome(lporovider.getText()));
            }
            else
            {
                datax.addAll(se.trix((String)c7.getSelectionModel().getSelectedItem(),lporovider.getText()));
            }
            
            tfId.setCellValueFactory(new PropertyValueFactory<Charge,Integer>("id"));
            tfState.setCellValueFactory(new PropertyValueFactory<Charge,String>("state"));
            tfPrice.setCellValueFactory(new PropertyValueFactory<Charge,Float>("price"));
            tfDate.setCellValueFactory(new PropertyValueFactory<Charge,Date>("date"));
            table.setItems(datax);
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
      
}
