/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintjava;

import Entities.Event;
import Services.ServiceEvent;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.management.Notification;
import org.controlsfx.control.action.Action;
import org.controlsfx.tools.Utils;

/**
 * FXML Controller class
 *
 * @author NahlaJ
 */
public class AfflisteventFXMLController implements Initializable {

    //ObservableList<Event> listEvent = FXCollections.observableArrayList();
    public static Event ab;
    @FXML
    private TableView<Event> tableEvent;
    @FXML
    private TableColumn<Event, Integer> id;
    @FXML
    private TableColumn<Event, Date> date;
    @FXML
    private TableColumn<Event, String> lieu;
    @FXML
    private TableColumn<Event, Integer> cap;
    @FXML
    private TableColumn<Event, String> nom;
    @FXML
    private TableColumn<Event, String> desc;
    @FXML
    private TableColumn<Event, Float> prix;
    @FXML
    private TableColumn<Event, ImageView> img;
    @FXML
    private Button modifE;
    @FXML
    private Button suppE;
    @FXML
    private Button ajoutE;
    @FXML
    private TextField rechercheEvent;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //aff
        try {
            rafrechir();
        } catch (SQLException ex) {
            Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //supp
        suppE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableEvent.getSelectionModel().getSelectedItem() != null) {
            try {
                int id = tableEvent.getSelectionModel().getSelectedItem().getIdevent();
                
                ServiceEvent s = new ServiceEvent();
                s.supprimerEvent(id);
            } catch (SQLException ex) {
               // Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    try {
                        rafrechir();
                    } catch (SQLException ex) {
                      //  Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un event");
            alert.show();
        }
            
            }
        });
        
        
        //modif
        modifE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableEvent.getSelectionModel().getSelectedItem() != null) {
                    
               try {
          Event a = tableEvent.getSelectionModel().getSelectedItem();
        ab = a;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifeventFXML.fxml"));
        Parent root = loader.load();
        ModifeventFXMLController hc = loader.getController();
        hc.setEvent(a);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
                   try {
                       rafrechir();
                   } catch (SQLException ex) {
                      // Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   }
        } catch (IOException ex) {
           // Logger.getLogger(AfficheractiviteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un event");
            alert.show();
        }
    }  
         });
        
        
        //ajout
        ajoutE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("ajouteventFXML.fxml"));
                } catch (IOException ex) {
                   // Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        //stage.show();
        stage.showAndWait();
        try {
                       rafrechir();
                   } catch (SQLException ex) {
                      // Logger.getLogger(AfflisteventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        });
        
    }

    
  
   
         
public void rafrechir() throws SQLException
    {
        ServiceEvent SE = new ServiceEvent(); 
        /* add column to the tableview and set its items */
        ObservableList<Event> listEvent = FXCollections.observableArrayList(SE.afficherEvent());
       tableEvent.setItems(listEvent); 
       
        id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Event, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getIdevent()); 
            }
        });
        
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Date>,ObservableValue<Date>>(){
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Event, Date> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getDateevent()); 
            }
        });
        
        lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getLieuevent()); 
            }
           
        });
        
        cap.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Event, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCapevent()); 
            }
        });
        
         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getNomevent()); 
            }
        });
         
        desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }           
        });
        
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Float>,ObservableValue<Float>>(){
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Event, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTicketprice()); 
            }
        });
        
        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, ImageView>,ObservableValue<ImageView>>(){
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Event, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEventImg()); 
            }
        });
}





    @FXML
    private void rechercheEvent(ActionEvent event) throws SQLException {
          
        String ad=rechercheEvent.getText();
       ServiceEvent ser = new ServiceEvent();
        
        /* add column to the tableview and set its items */
        ObservableList<Event> listeEvents = FXCollections.observableArrayList((ser.FindEventByName(ad)));
       tableEvent.setItems(listeEvents);
       
        id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Event, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getIdevent()); 
            }
        });
        
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Date>,ObservableValue<Date>>(){
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Event, Date> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getDateevent()); 
            }
        });
        
        lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getLieuevent()); 
            }
           
        });
        
        cap.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Event, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCapevent()); 
            }
        });
        
         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
               return new ReadOnlyObjectWrapper(param.getValue().getNomevent()); 
            }
        });
         
        desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }           
        });
        
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, Float>,ObservableValue<Float>>(){
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Event, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTicketprice()); 
            }
        });
        
        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, ImageView>,ObservableValue<ImageView>>(){
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Event, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEventImg()); 
            }
        });
    }
    
}
