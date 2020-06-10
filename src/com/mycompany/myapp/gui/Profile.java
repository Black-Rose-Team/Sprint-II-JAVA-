/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class Profile extends Form{

     public Profile(Form previous) {
         
         
        setTitle("My Profile");
        
      
        ArrayList<Provider> ss;
        /*ComboBox<String> C = new ComboBox("All","Not Seen","Accepted","Refused");*/
        
        ss=ServiceProvider.getInstance().getOneTasksU(Session.getCurrentSession().getId()/*,C.getSelectedItem()*/);
        
        for (int i=0; i < ss.size();i++){
        SpanLabel  l = new SpanLabel ();
        SpanLabel  l2 = new SpanLabel ();
        SpanLabel  l3 = new SpanLabel ();
        SpanLabel  l4 = new SpanLabel ();
        SpanLabel  l5 = new SpanLabel ();

  
                
          
                            l.setText(" Id : "+ss.get(i).getId()+"           ");
                            l2.setText(" Name : "+ss.get(i).getName()+"          ");
                            l3.setText(" Mail : "+ss.get(i).getMail()+" ");
                            l4.setText(" Tel : "+ss.get(i).getTel()+"              ");
                            l5.setText(" Password : "+ss.get(i).getPassword()+"           ");
                            
                            this.addAll(l,l2,l3,l4,l5);
                          

        
                             
    
    }
        
                     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                             , e-> new ProviderSpace(previous).show()); // Revenir vers l'interface précédente

        /* addAll(C);*/
        
     }
    
}
