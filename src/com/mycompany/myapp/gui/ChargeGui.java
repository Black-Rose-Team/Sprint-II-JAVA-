/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class ChargeGui extends Form{

  
    public ChargeGui(Form previous) {
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
       
        Button btnAddCharge = new Button("Add Charge");
        Button btnListCharge = new Button("Charges List");
        Button btnEditCharge = new Button("Edit Charge");
        Button btnDelCharge = new Button("Delete Charge");
       /* Button btnFindPro = new Button("Find Charge");*/


        
       
        btnAddCharge.addActionListener(e-> new AddCharge(previous).show());
        btnListCharge.addActionListener(e-> new ListCharges(previous).show());
        btnEditCharge.addActionListener(e-> new ModifierCharge(previous).show());
        btnDelCharge.addActionListener(e-> new DeleteCharge(previous).show());
        /*btnFindPro.addActionListener(e-> new FindProvider(current).show());*/


        addAll(btnAddCharge,btnListCharge
                ,btnEditCharge,btnDelCharge);
        
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new Accueil(previous).show());
    }
    
    
}
