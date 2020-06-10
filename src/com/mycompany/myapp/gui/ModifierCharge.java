/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Charge;
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.services.ServiceCharge;
import com.mycompany.myapp.services.ServiceProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



/**
 *
 * @author User
 */
public class ModifierCharge extends Form{
                 ArrayList<Provider> user ;
                 ArrayList<Charge> chr;

       public ModifierCharge(Form previous) {
        
        setTitle("Edit Charge");
        setLayout(BoxLayout.y());
          
         chr =ServiceCharge.getInstance().getAllTasks();
        ComboBox<Integer> tfId = new ComboBox<>();
        for (int i=0; i < chr.size();i++){
           tfId.addItem(chr.get(i).getId());               
        }  
        /*TextField tfId= new TextField("", "Charge Id");*/
        Picker tfdate= new Picker();
        tfdate.setType(Display.PICKER_TYPE_DATE);
 user =ServiceProvider.getInstance().getAllTasks();
        ComboBox<String> tfprovider = new ComboBox<>();
        for (int i=0; i < user.size();i++){
      tfprovider.addItem(user.get(i).getId());               
        }  
        
        Button btnValider = new Button("Edit Charge");
                getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
         ///////////////////////////////////////////////////////
              Dialog dlg = new Dialog("Warning");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
  

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Are you sure to add this Charge !");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("Yes"));
    Button non = new Button(new Command("Cancel"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    non.getAllStyles().setBorder(Border.createEmpty());
    non.getAllStyles().setFgColor(0);
    dlg.add(non);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setMessage(" Edit the  Charge ... ");
                            status.setShowProgressIndicator(true);
                            status.setIcon(createIcon(FontImage.MATERIAL_WORK)); 
          
                       
                      
                     btnValider.addActionListener(e -> {
  
            dlg.showDialog();
                  status.show();
                  status.setExpires(1500); 
                          
         
});
/////////////////////////////////////////////
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  
             
                    try {
                         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
                         String strDate = dateFormat.format(tfdate.getDate());  
                       Charge t;
                            t = new Charge(tfId.getSelectedItem(),strDate,tfprovider.getSelectedItem()); 
                            if( ServiceCharge.getInstance().Modifiercl(t))
                            {
                                                             
                            Dialog.show("Success","Charge has been Edited !",new Command("OK"));

                            }
                            else
                            Dialog.show("Warning", "Charge dose not Exist !!", new Command("OK"));
                            
                    } catch (NumberFormatException e) {                                                                                                                                               // con.addArgument("type", combobox.getSelectedItem().toString());                                    
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }             
                }
            
        }); 
        addAll(tfId,tfprovider,tfdate,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ChargeGui(previous).show()); // Revenir vers l'interface précédente

        

    }
      private Image createIcon(char charcode) {
         
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
    
}
