/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
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
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceCharge;
import com.mycompany.myapp.services.ServiceProvider;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class AddProvider extends Form{

        ArrayList<User> user ;
        
    
    public AddProvider(Form previous) {
        user =ServiceProvider.getInstance().getAllTasksU();
        setTitle("Add a new Provider");
        setLayout(BoxLayout.y());
        ComboBox<String> tfId = new ComboBox<>();
        for (int i=0; i < user.size();i++){
      tfId.addItem(user.get(i).getPassword());               
        }
        /*TextField tfId= new TextField("", "Provider Id");*/
        TextField tfName = new TextField("","Provider Name");
        TextField tfMail= new TextField("", "Provider Mail");
        /*TextField tfPassword= new TextField("", "Provider Password");*/
        TextField tfTel= new TextField("", "Provider Tel");

        
        Button btnValider = new Button("Add Provider");
          getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication(); });
         Dialog dlg = new Dialog("Warning");
 
    

    Label title = dlg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setAlignment(Component.CENTER);

    dlg.setLayout(BoxLayout.y());
    
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
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((tfId.getSelectedItem().length()==0)||(tfMail.getText().length()==0)||(tfName.getText().length()==0)||
                       ((tfTel.getText().length()==0)))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
               else if (!((tfMail.getText().contains("@"))&&(tfMail.getText().contains(".")))) {
                    Dialog.show("Alert", "Mail not Valid", new Command("OK"));
                }
               else if(tfTel.getText().length()!=8)
                     Dialog.show("Alert", "Phone Number must Cintains 8 Numbers", new Command("OK"));

                else
                   try {
                       
                  
                {
                    try {
                        Provider t = new Provider(tfId.getSelectedItem(),tfName.getText(),tfMail.getText(),Integer.parseInt(tfTel.getText()));
                        if( ServiceProvider.getInstance().addTask(t))
                            Dialog.show("Success","Provider has been added !",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
                    } catch (NumberFormatException e) {
                       Dialog.show("ERROR", "Tel must be a number", new Command("OK"));

                   }
                }
                
                
            
        });
        
        addAll(tfId,tfName,tfMail,tfTel,btnValider);
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
