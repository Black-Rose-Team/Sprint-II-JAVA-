/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintjava;

import Entities.Event;
import Services.ServiceEvent;
import Services.Upload;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import sun.plugin2.message.Conversation;
import javax.management.Notification;
import  org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.tools.Utils;

/**
 * FXML Controller class
 *
 * @author NahlaJ
 */
public class ModifeventFXMLController implements Initializable {
public static Event ab;

    //private Button img;
    @FXML
    private Button btn_modifE;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField cap;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextArea desc;
    @FXML
    private Button retourbtn;
    
    File selectedfile;
    String path_img;
    @FXML
    private Button imgg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 
    
    public void setEvent(Event a){
    ab=a;
    
      //this.date.setDayCellFactory((Callback<DatePicker, DateCell>) ab.getDateevent());
      //this.date.setText(ab.getDateevent().toString().);
      this.date.getEditor().setText(String.valueOf(ab.getDateevent()));
      // date.getEditor().setText(String.valueOf(e.getDateevent()));
      this.lieu.setText(ab.getLieuevent());
      this.cap.setText(ab.getCapevent()+"");
      this.nom.setText(ab.getNomevent());
      this.desc.setText(ab.getDescription());
      this.prix.setText(ab.getTicketprice()+"");
      this.imgg.setText(ab.getEventImg());
      //this.img.setText(selectedfile.getName());
        BooleanBinding booleanBinding = 
      (
        date.dayCellFactoryProperty().isEqualTo("")).or(
        lieu.textProperty().isEqualTo("")).or(
        cap.textProperty().isEqualTo("")).or(
        nom.textProperty().isEqualTo("")).or(
        desc.textProperty().isEqualTo("")).or(
        prix.textProperty().isEqualTo("")).or(
        imgg.textProperty().isEqualTo(""));
       
    btn_modifE.disableProperty().bind(booleanBinding);
    }

  

    @FXML
    private void modifierEvent(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
         ServiceEvent as = new ServiceEvent();
       Event e = new Event();
       
    //e.setDateevent(java.sql.Date.valueOf(date.getValue()));
    date.getEditor().setText(String.valueOf(e.getDateevent()));
        e.setLieuevent(lieu.getText());
        e.setCapevent( Integer.parseInt(cap.getText()));
        e.setNomevent(nom.getText());
        e.setDescription(desc.getText());
        e.setTicketprice( Float.parseFloat(prix.getText()));
        //e.setEventImg(selectedfile.getName());
        e.setEventImg(selectedfile.getName());
    try {
        as.modifierEvent(e, ab.getIdevent());
       
       
       /*Notifications.create()
              .title("Cycle")
              .text("Event Has been edited")
              .showWarning();*/
        Notifications notification = Notifications.create();
  //notification.hideAfter(new Duration(SHOW_TIME / 2));
  notification.title("Cycle");
  notification.text("Event Has been edited");
       Platform.runLater(() -> notification.showInformation());
       
        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Cycle");
            alert0.setHeaderText(null);
            alert0.setContentText("event modifié avec succèes ");
            alert0.show();
             ((Node)event.getSource()).getScene().getWindow().hide();
           /* ((Node)event.getSource()).getScene().getWindow().hide();*/
    } catch (SQLException ex) {
        //Logger.getLogger(ModifeventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }    }
 

    @FXML
    private void BackListEvent(ActionEvent event) {
    }

    @FXML
    private void imgupload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image","*.jpg", "*.png")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println("aaaaaaaaaa");
            Upload u= new Upload();
             try {
                 u.upload(selectedfile);
             } catch (IOException ex) {
                // Logger.getLogger(AjouterHebergementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
             //imageupload.getItems().add(selectedfile.getName());

            path_img = selectedfile.getAbsolutePath();
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }
    

            }
    }
    

