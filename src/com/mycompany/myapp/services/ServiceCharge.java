/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Charge;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Crow
 */
public class ServiceCharge {
    
     public ArrayList<Charge> tasks;
     public ArrayList<Charge> proc;

    
    public static ServiceCharge instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    

    private ServiceCharge() {
         req = new ConnectionRequest();
    }
    
     // Connexion
    public static ServiceCharge getInstance() {
        if (instance == null) {
            instance = new ServiceCharge();
        }
        return instance;
    }
    
    
    // add new Provider
     public boolean addTask(Charge t) {
          String url = Statics.BASE_URL + "newCharge?provider=" + t.getProvider()+"&date=" + t.getDatem();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     // show Providers List
public ArrayList<Charge> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                       Charge t = new Charge();
                       t.setId(((int)Float.parseFloat(obj.get("id").toString())));
                       t.setPrice((Float.parseFloat(obj.get("price").toString())));
                       t.setState(obj.get("state").toString());
                       t.setProvider(obj.get("provider").toString());
                       t.setDatem(obj.get("datem").toString());

                tasks.add(t);
            }
          
        } catch (IOException ex) {
            
        }
        return tasks;
    }
     
     
public ArrayList<Charge> getAllTasks(){
        String url = Statics.BASE_URL+"allCharges";
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }



// edit Provider
     public boolean Modifiercl(Charge t) {
        String url = Statics.BASE_URL + "editCharge?idReview=" + t.getId()+"&date=" + t.getDatem()+"&provider=" + t.getProvider();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
      public boolean supprimerTask(Charge t ) {
        
          String url = Statics.BASE_URL + "deleteCharge?idReview=" + t.getId()  ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
       // show ProChrges List
      
     
      
public ArrayList<Charge> getAllTask(String id/*,String state*/){
        String url = Statics.BASE_URL+"findC/"+id/*+"/"+state*/;
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                proc = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return proc;
    }


}
