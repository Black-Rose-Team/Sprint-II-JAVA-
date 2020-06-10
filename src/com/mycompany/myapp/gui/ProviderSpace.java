/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Charge;
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.services.ServiceCharge;
import com.mycompany.myapp.services.ServiceProvider;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ProviderSpace extends Form{

     public ProviderSpace(Form previous) {
         
         
     
        setTitle("Home");
                setLayout(BoxLayout.y());

                String name=Session.getCurrentSession().getId();
        Label l =new Label("Welcome Mr "+name);
         add(new Label("Choose an option"));
        Button btnC = new Button("Access to My Charges");
        Button btnP = new Button("Show My Profile");
      


        
        btnC.addActionListener(e-> new ProCharges(previous).show());
        btnP.addActionListener(e-> new Profile(previous).show());

      

 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new Login(previous).show()); // Revenir vers l'interface précédente



        addAll(l,btnC,btnP);
        
        
    
}}
