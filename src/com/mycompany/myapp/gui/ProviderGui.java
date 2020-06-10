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
public class ProviderGui extends Form{

    public ProviderGui(Form previous) {
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddPro = new Button("Add Provider");
        Button btnListPro = new Button("Providers List");
        Button btnModPro = new Button("Edit Provider");
        Button btnDelPro = new Button("Delete Provider");
       


        
        btnAddPro.addActionListener(e-> new AddProvider(previous).show());
        btnListPro.addActionListener(e-> new ListProvider(previous).show());
        btnModPro.addActionListener(e-> new ModifierProvider(previous).show());
        btnDelPro.addActionListener(e-> new DeleteProvider(previous).show());
        


        addAll(btnAddPro,btnListPro,btnModPro
                ,btnDelPro);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new Accueil(previous).show());
        
    }
    
    
}
