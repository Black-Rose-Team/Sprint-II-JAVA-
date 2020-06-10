/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.Charge;
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.services.ServiceCharge;
import com.mycompany.myapp.services.ServiceProvider;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListCharges extends Form{

     public ListCharges(Form previous) {
         
         
        setTitle("Charges Lists");
        
       SpanLabel sp = new SpanLabel();
       //  List<AddTaskForm> sp = new ArrayList<AddTaskForm>();
        sp.setText(ServiceCharge.getInstance().getAllTasks().toString());
       // add(sp);
        ArrayList<Charge> ss;
        ss=ServiceCharge.getInstance().getAllTasks();
        for (int i=0; i < ss.size();i++){
        SpanLabel  l = new SpanLabel ();    
  
                
           String  ch =
                   "id : "+ss.get(i).getId()+" "+
                   " date : "+ss.get(i).getDatem()+" "+
                   " provider : "+ss.get(i).getProvider()+" "+
                   " price : "+ss.get(i).getPrice()+" "+
                   " state : "+ss.get(i).getState()
                   +" ==================== "
                  ;
                               
                               
                               
                            l.setText(ch);
                            
                            this.add(l);
                          
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ChargeGui(previous).show()); // Revenir vers l'interface précédente
        
                             
    
    }
        
     }
    
}
