/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Charge;
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceCharge;
import com.mycompany.myapp.services.ServiceProvider;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author bhk
 */
public class AddCharge extends Form{
        ArrayList<Provider> user ;

    public AddCharge(Form previous) {
        
        setTitle("Add a new Charge");
        
        setLayout(BoxLayout.y());
                
        Picker tfdate= new Picker();
        tfdate.setType(Display.PICKER_TYPE_DATE);
         user =ServiceProvider.getInstance().getAllTasks();
        ComboBox<String> tfprovider = new ComboBox<>();
        for (int i=0; i < user.size();i++){
      tfprovider.addItem(user.get(i).getId());               
        }
        /*TextField tfprovider = new TextField("","Provider");*/
        Button btnValider = new Button("Add Provider");
        
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
    title.getUnselectedStyle().setAlignment(Component.CENTER);

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
                            status.setMessage(" Add a new Charge ... ");
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
                  
                if ((tfprovider.getSelectedItem().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
                         String strDate = dateFormat.format(tfdate.getDate());  
                       Charge t;
                            t = new Charge(strDate,tfprovider.getSelectedItem()); 
                            if( ServiceCharge.getInstance().addTask(t))
                            {
                                                             
                            Dialog.show("Success","Charge has been added !",new Command("OK"));

                            }
                            else
                            Dialog.show("ERROR", "Erreur!", new Command("OK"));
                            
                    } catch (NumberFormatException e) {                                                                                                                                               // con.addArgument("type", combobox.getSelectedItem().toString());                                    
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }             
                }
            }
        }); 
        addAll(tfdate,tfprovider,btnValider);
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
