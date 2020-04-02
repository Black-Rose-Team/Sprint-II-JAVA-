/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintjava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Services.ServiceEvent;
import Entities.Event;
import Services.Upload;
import java.io.File;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
//import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author NahlaJ
 */
public class AjouteventFXMLController implements Initializable {

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
    private TextField desc;
    @FXML
    private Button img;
    File selectedfile;
    String path_img;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
}
    
 @FXML
    private void ajouterEvent(ActionEvent event) throws SQLException, IOException {
        ServiceEvent se = new ServiceEvent();
        Event e = new Event();
        
    

        e.setDateevent(java.sql.Date.valueOf(date.getValue()));
        e.setLieuevent(lieu.getText());
        e.setCapevent( Integer.parseInt(cap.getText()));
        e.setNomevent(nom.getText());
        e.setDescription(desc.getText());
        e.setTicketprice( Float.parseFloat(prix.getText()));
        e.setEventImg(selectedfile.getName());
        
        se.ajouterEvent(e);
        
    }
/*
 public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\Users\\Khalil\\Documents\\NetBeansProjects\\HuntKingdom\\src\\images");
        String name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    } */
           
    @FXML
    private void uploadImage(ActionEvent event) throws IOException{
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
    
