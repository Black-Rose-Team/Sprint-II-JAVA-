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
import com.mycompany.myapp.entities.Provider;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import static com.mycompany.myapp.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Crow
 */
public class ServiceProvider {
    
     public ArrayList<Provider> tasks;
         public ArrayList<User> tasksU;

    public static ServiceProvider instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Provider prov = new Provider();


    private ServiceProvider() {
         req = new ConnectionRequest();
    }
    
     // Connexion
    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }
    
    
    // add new Provider
     public boolean addTask(Provider t) {
          String url = Statics.BASE_URL + "neww?id=" + t.getId()+"&name=" + t.getName()+"&mail=" + t.getMail()+"&tel=" + t.getTel()/*+"&password=" +t.getPassword()*/;
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
public ArrayList<Provider> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Provider t = new Provider();
                       t.setId(obj.get("id").toString());
                       t.setMail(obj.get("mail").toString());
                       t.setName(obj.get("name").toString());
                       t.setPassword((int)Float.parseFloat(obj.get("password").toString()));
                       t.setTel(((int)Float.parseFloat(obj.get("tel").toString())));
    
                tasks.add(t);
            }
          
        } catch (IOException ex) {
            
        }
        return tasks;
    }


     
     
public ArrayList<Provider> getAllTasks(){
        String url = Statics.BASE_URL+"alll";
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
     public boolean Modifiercl(Provider t) {
        String url = Statics.BASE_URL + "editReview?idReview=" + t.getId()+"&name=" + t.getName()+"&mail=" + t.getMail()+"&tel=" + t.getTel()/*+"&password=" +t.getPassword()*/;
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
     
 //Delete Provider
      public boolean supprimerTask(Provider t ) {
        String url = Statics.BASE_URL + "deleteReview?idReview=" + t.getId()  ;
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
public ArrayList<User> parseTasksU(String jsonText){
        try {
            tasksU=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               User t = new User();
                       /*t.setId(obj.get("id").toString());*/
                       t.setPassword(obj.get("username").toString());
                    
                tasksU.add(t);
            }
          
        } catch (IOException ex) {
            
        }
        return tasksU;
    }


     
     
public ArrayList<User> getAllTasksU(){
        String url = Statics.BASE_URL+"allU";
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasksU = parseTasksU(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasksU;
    }  

public ArrayList<Provider> getOneTasksU(String id){
        String url = Statics.BASE_URL+"find/"+id;
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



public Provider parseLogin(String jsonText) {
        Provider UserL = new Provider();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if (UserListJson.get("type").equals("Login succeed")) {
                UserL.setId(UserListJson.get("id").toString());
                UserL.setPassword((int)Float.parseFloat(UserListJson.get("password").toString()));
   
            } else {
                return null;
            }
        } catch (IOException ex) {
                ex.getMessage();
        }
        return UserL;
    }
    
    public Provider Login(String username,String password) {
        String url =BASE_URL +"ap/login/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prov = parseLogin(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prov;
    }
}
