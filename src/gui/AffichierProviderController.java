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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    @FXML
    private TextField tfsearch;
    @FXML
    private Button mod;
        
   
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
               ProviderCrud se= new ProviderCrud();
           // List<Command> list = sp.readAll();
            
            
            tfId.setCellValueFactory(new PropertyValueFactory<Provider,String>("id"));
            tfMail.setCellValueFactory(new PropertyValueFactory<Provider,String>("mail"));
            tfName.setCellValueFactory(new PropertyValueFactory<Provider,String>("name"));
            tfTel.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("tel"));
            tfPassword.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("password"));

               data.addAll(se.readAll());

                
               FilteredList<Provider> filteredData =new FilteredList<>(data,b-> true);
               
                tfsearch.textProperty().addListener(((observable,oldValue,newValue) -> {
                    filteredData.setPredicate(provider -> {
                        if (newValue == null || newValue.isEmpty())
                        {
                            return true;
                        }
                        
                        String lowerCaseFilter =newValue.toLowerCase();
                        
                        if(provider.getName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        {
                            return true;
                        }
                        else if(provider.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        {
                            return true;
                        }
                        else if(String.valueOf( provider.getTel()).indexOf(lowerCaseFilter) != -1)
                        {
                            return true;
                        }
                        else if(provider.getId().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        {
                            return true;
                        }
                        else if(String.valueOf( provider.getPassword()).indexOf(lowerCaseFilter) != -1)
                        {
                            return true;
                        }
                        else 
                            return false;
                    });
                }));
                SortedList<Provider> sortedData =new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
                
                
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
        
       
     Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
            try {
        Provider c =  table.getSelectionModel().getSelectedItem();
             ProviderCrud sc = new ProviderCrud();
            // as.delete(e);
             
           //  ArrayList arraylist = (ArrayList) as.afficher(e.getId_Provider());
               sc.delete(c);
               table.getItems().removeAll(c);
                    Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Provider Has been Removed !");
        alert2.showAndWait();

         } catch (SQLException ex) {
             Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
        
    }
    
    

   
    
    
        public static String id;
        public static Integer name;
        public static String mail;
        public static String tel;
        public static String password;

        
           
    @FXML
    private void modifierex(ActionEvent event) {
   
           
              Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("editing Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to edit this provider ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
               try {
                String iid=bId.getText();
                int itel=Integer.parseInt(bTel.getText());
                String iname=bName.getText();
                String imail=bMail.getText();
                int ipassword=Integer.parseInt(bPassword.getText());

                
            if (imail.contains("@")&& imail.contains(".")) {
                 if (String.valueOf(itel).length() == 8 ) {
                     

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
            
                 Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Provider Has been Edited !");
        alert2.showAndWait();
            }
                 else{
                      Alert alert2 =new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Warning dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Phone Number is not Valid !!!");
        alert2.showAndWait();
                 }
           
            
            }
            else{
                   Alert alert2 =new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Warning dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Email is not Valid !!!");
        alert2.showAndWait();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
         Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("adding Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to add a provider ?");
        
        Optional<ButtonType> result = alert.showAndWait();
             
        if (result.get() == ButtonType.OK) {
        try {
             
            
                int atel=Integer.parseInt(bTel.getText());
                String aname=bName.getText();
                String amail=bMail.getText();
            
            if (amail.contains("@")&& amail.contains(".")) {
                 if (String.valueOf(atel).length() == 8 ) {
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
            
                 Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Information dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Provider Has been Added !");
        alert2.showAndWait(); 
                }
                 else{
                      Alert alert2 =new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Warning dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Phone Number is not Valid !!!");
        alert2.showAndWait();
                 }
           
            
            }
            else{
                   Alert alert2 =new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Warning dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Email is not Valid !!!");
        alert2.showAndWait();
                
            }
            
            
           
             
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichierProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
