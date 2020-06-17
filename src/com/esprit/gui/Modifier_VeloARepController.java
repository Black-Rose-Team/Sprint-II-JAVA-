/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.VeloARep;
import com.esprit.Service.ServiceVeloARep;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author OMEN
 */
public class Modifier_VeloARepController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    @FXML
    private TextField vid;
    @FXML
    private TextField vb;
    @FXML
    private TextField va;
    @FXML
    private TextField vd;
    @FXML
    private TextField vp;
    @FXML
    private TextField vs;
    @FXML
    private ComboBox<?> combo1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        int id=Integer.parseInt(vid.getText());
        String Marque=vb.getText();
        String Description=vd.getText();
        String Probleme=vb.getText();
        int age=Integer.parseInt(va.getText());
        String status=vs.getText();
        int id_reparateur=Integer.parseInt((String) combo1.getValue());


      
        VeloARep va = new VeloARep(id,Marque,Description,Probleme,age,status,id_reparateur);
        ServiceVeloARep e =ServiceVeloARep.getInstance();


e.update(va);
    }

    @FXML
    private void annuler(ActionEvent event) {
    }
    
}
