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
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.services.ServiceProvider;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListProvider extends Form{

     public ListProvider(Form previous) {
         
         
        setTitle("List Reps");
        
       SpanLabel sp = new SpanLabel();
       //  List<AddTaskForm> sp = new ArrayList<AddTaskForm>();
        sp.setText(ServiceProvider.getInstance().getAllTasks().toString());
       // add(sp);
        ArrayList<Provider> ss;
        ss=ServiceProvider.getInstance().getAllTasks();
        for (int i=0; i < ss.size();i++){
        Label l = new Label();    
  
                
           String  ch =   "id:   "+ss.get(i).getId()+" "+
                   "name :  "+ss.get(i).getName()+" "+
                   "mail :  "+ss.get(i).getMail()+" "+
                   "tel :  "+ss.get(i).getTel()+" "+
                   "password :  "+ss.get(i).getPassword()
                   +" ==================== ";
                               
                               
                                //  "experienceCondidat:"+s.get(i).getExperience_Candidat();
                               
                            l.setText(ch);
                            
                            this.add(l);
                          
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ChargeGui(previous).show()); // Revenir vers l'interface précédente
                        
                             
    
    }
        
     }
    
}
