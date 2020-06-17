/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Reparateur;
import com.esprit.Service.ServiceReparateur;
import com.esprit.Service.ServiceVeloARep;
import com.itextpdf.text.DocumentException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author OMEN
 */
public class Afficher_ReparateurController implements Initializable {

   
    @FXML
    private TableColumn<Reparateur, Integer> tfId;
    @FXML
    private TableColumn<Reparateur, String> tfNom;
    @FXML
    private TableColumn<Reparateur, String> tfPrenom;
    @FXML
    private TableColumn<Reparateur, Integer> tfNumTel;
    @FXML
    private TableColumn<Reparateur, Integer> tfNbr_velo_arep;
    @FXML
    private TableColumn<Reparateur, Integer> tfExperience;
     @FXML
    private TableColumn<Reparateur, String> tfstatus;
     @FXML
    private TableColumn<Reparateur, String> colAction;
     @FXML
    private TableView<Reparateur> table;
     
     ObservableList<Reparateur> data=FXCollections.observableArrayList();

   
    @FXML
    private Button supprimer;
    @FXML
    private Button annuler;
    @FXML
    private TextField tfNoma;
    @FXML
    private TextField tfPrenoma;
    @FXML
    private TextField tfNumTela;
    @FXML
    private Button bvalider;
    @FXML
    private TextField tfNbr_velo_arepa;
    @FXML
    private TextField tfExperiencea;
    @FXML
    private Button modifier;
    @FXML
    private ImageView pic;
    @FXML
    private Button Choosephoto;
    @FXML
    private Button pdf;
    @FXML
    private Button show;
    @FXML
    private TextField recherche;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceReparateur se= new ServiceReparateur();
            // List<Command> list = sp.readAll();
            
            data.addAll(se.readAll());

            tfId.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("id"));
            tfNom.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("Nom"));
            tfPrenom.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("Prenom"));
            tfNumTel.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("NumTel"));
            tfNbr_velo_arep.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("nbr_velo_arep"));
            tfExperience.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("Experience"));
            tfstatus.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("status"));
            
            
        colAction.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
            Callback<TableColumn<Reparateur, String>, TableCell<Reparateur, String>> cellFactory
                = //
                new Callback<TableColumn<Reparateur, String>, TableCell<Reparateur, String>>() {
            @Override
            public TableCell call(final TableColumn<Reparateur, String> param) {
                final TableCell<Reparateur, String> cell = new TableCell<Reparateur, String>() {

                    final Button btn = new Button("End Task");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Reparateur person = getTableView().getItems().get(getIndex());
                                ServiceVeloARep svr = new ServiceVeloARep();
                                try {
                                    svr.setRep(person.getId());
                                    svr.updateRepStatus(person.getId());
                                         
                Notifications notification = Notifications.create();
                         notification.title("Cycle");
                         notification.text("Task ended successfully");
                         Platform.runLater(() -> notification.showInformation());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
            colAction.setCellFactory(cellFactory);
            table.setItems(data);
          
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    
        FilteredList<Reparateur> filteredData = new FilteredList<>(data, b -> true);
          recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rep -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rep.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				
				}
				else if (String.valueOf(rep.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reparateur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
table.setItems(sortedData);
        // TODO
    } 

        // TODO
    public void setTab(TableView<Reparateur> table) {
        this.table = table;
    }

    public void setId(TableColumn<Reparateur, Integer> id) {
        this.tfId = id;
    }

    public void setNom(TableColumn<Reparateur, String> Nom) {
        this.tfNom = Nom;
    }
    
    public void setPrenom(TableColumn<Reparateur, String> Prenom) {
        this.tfPrenom = Prenom;
    }
    
    public void setNumTel(TableColumn<Reparateur, Integer> NumTel) {
        this.tfNumTel = NumTel;
    }
    
    public void setNbr_velo_arep(TableColumn<Reparateur, Integer> nbr_velo_arep) {
        this.tfNbr_velo_arep = nbr_velo_arep;
    }

    public void setExperience(TableColumn<Reparateur, Integer> Experience) {
        this.tfExperience = Experience;
    }
     public void setStatus(TableColumn<Reparateur, String> status) {
        this.tfstatus = status;
    }

    @FXML
    private void supprimer(ActionEvent event) {
         try {
             Reparateur c = (Reparateur) table.getSelectionModel().getSelectedItem();
             ServiceReparateur sc = new ServiceReparateur();
            // as.delete(e);
             
           //  ArrayList arraylist = (ArrayList) as.afficher(e.getId_employe());
               sc.delete(c);
                      table.getItems().removeAll(c);

         } catch (SQLException ex) {
             Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    public static Integer id_r;
    public static String nom;
    public static String prenom;
    public static Integer tel;
    public static Integer rep;
    public static Integer exp;
    public static String sta;
    
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
                String Nom=tfNoma.getText();
                String Prenom=tfPrenoma.getText();
                int NumTel=Integer.parseInt(tfNumTela.getText());
                int nbr_velo_arep=Integer.parseInt(tfNbr_velo_arepa.getText());
                int Experience=Integer.parseInt(tfExperiencea.getText());
                String status="";
                String getImageUrl="98999649_1900533683414522_5504102380548915200_n"; 
                

            
            
            
            System.out.println(Nom);
            Reparateur e= new Reparateur(Nom,Prenom,NumTel,nbr_velo_arep,Experience,status,getImageUrl);
            ServiceReparateur se= new ServiceReparateur(); 
            se.ajouter(e);
            JOptionPane.showMessageDialog(null, "Added successfully");
            data.clear();
            data.addAll(se.readAll());
                  tfId.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("id"));
            tfNom.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("Nom"));
            tfPrenom.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("Prenom"));
            tfNumTel.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("NumTel"));
            tfNbr_velo_arep.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("nbr_velo_arep"));
            tfExperience.setCellValueFactory(new PropertyValueFactory<Reparateur,Integer>("Experience"));
            tfstatus.setCellValueFactory(new PropertyValueFactory<Reparateur,String>("status"));
            
            
        colAction.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
            Callback<TableColumn<Reparateur, String>, TableCell<Reparateur, String>> cellFactory
                = //
                new Callback<TableColumn<Reparateur, String>, TableCell<Reparateur, String>>() {
            @Override
            public TableCell call(final TableColumn<Reparateur, String> param) {
                final TableCell<Reparateur, String> cell = new TableCell<Reparateur, String>() {

                    final Button btn = new Button("End Task");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Reparateur person = getTableView().getItems().get(getIndex());
                                ServiceVeloARep svr = new ServiceVeloARep();
                                try {
                                    svr.setRep(person.getId());
                                    svr.updateRepStatus(person.getId());
                                         
                Notifications notification = Notifications.create();
                         notification.title("Cycle");
                         notification.text("Task ended successfully");
                         Platform.runLater(() -> notification.showInformation());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
            colAction.setCellFactory(cellFactory);
            table.setItems(data);


        } catch (SQLException ex) {
            Logger.getLogger(Afficher_ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
       modifier.setOnAction(n-> {
 Reparateur ref = table.getSelectionModel().getSelectedItem();

 Afficher_ReparateurController.id_r=ref.getId();
 Afficher_ReparateurController.nom=ref.getNom();
 Afficher_ReparateurController.prenom=ref.getPrenom();
 Afficher_ReparateurController.tel=ref.getNumTel();
 Afficher_ReparateurController.rep=ref.getNbr_velo_arep();
 Afficher_ReparateurController.exp=ref.getExperience();
 Afficher_ReparateurController.sta=ref.getStatus();



 
 
           // AfficheremployeController.vv = selectedItems.toString().split(",")[0].substring(1);
             
            try {
            Parent root = FXMLLoader.load(getClass().getResource("Modifier_Reparateur.fxml"));
            Stage stage = (Stage) modifier.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
        }
                });
        
    }


    @FXML
    private void Choosephoto(ActionEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             pic.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Choosephoto(DragEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
                 pdf Pdf=new pdf();
   Pdf.add("Liste de reparateurs.pdf");
    }

    @FXML
    private void show(ActionEvent event) {
         try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Afficher_VeloARep.fxml"));
           Scene scene = new Scene(root);
            Stage stage = (Stage) annuler.getScene().getWindow();
            stage.close();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
        
    }

  
    
    
}
