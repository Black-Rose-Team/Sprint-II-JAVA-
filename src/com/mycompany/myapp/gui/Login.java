/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceProvider;
import com.mycompany.myapp.services.ServicesUsers;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class Login extends Form{


    Form current;
    private static User User;
    private  static Provider pro;
Form cuurent;
           private Resources theme;

    private Resources themeee;

    private com.codename1.social.Login login;



    public Login(Form p) {
        
        current = this; //Récupérsation de l'interface(Form) en cours
        setTitle("Welcome To Cycle");
        Toolbar.setGlobalToolbar(true);
        
   getToolbar().addMaterialCommandToSideMenu("Cycle",FontImage.MATERIAL_IMPORTANT_DEVICES, e -> {});

   getToolbar().addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {Display.getInstance().exitApplication();}); 
        
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        Container cnt = new Container(BoxLayout.y());
          theme = UIManager.initFirstTheme("/themeee");  
                Image im2 = theme.getImage("logo + slogan.png");
            ImageViewer img2 = new ImageViewer(im2);   
 
        TextField username = new TextField(null, "username");
  
        TextField password = new TextField(null, "password");
                    

        password.setConstraint(TextField.PASSWORD);
        Button login = new Button("Login");
cnt.add(im2);
        cnt.add(username);
        cnt.add(password);
        cnt.add(login);
     

        add(cnt);
  //cn.addActionListener(e-> new Clock().show());
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                {
                  if (username.getText().equals("admin") && password.getText().equals("admin")) {
                                                  new Accueil(current).show();

                    }
             
                     
                        else {
                                 pro = ServiceProvider.getInstance().Login(username.getText(), password.getText());

                  if (pro != null) {
                       

                        username.setText("");
                        password.setText("");
                        Session.start(pro);
                 
                          new ProviderSpace(current).show();
                  }
                          else {
                        Dialog.show("Alert", "This is already your home", "OK", null);
                                  }}
                    }

                

            }
        });
    }    




    
}
