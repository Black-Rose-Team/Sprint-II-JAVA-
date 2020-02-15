/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.test;

import com.esprit.Entite.Examen;
import com.esprit.Service.ServiceExamen;
import com.esprit.Entite.Note;
import com.esprit.Service.ServiceNote;
import com.esprit.Entite.Calandrier_e;
import com.esprit.Service.ServiceCalandrier_e;
import com.esprit.Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 @author ferie
 */
public class Test {
    
    public static void main(String[] args) { 
        ServiceExamen se=new ServiceExamen();
         ServiceNote no=new ServiceNote();
         ServiceCalandrier_e ca=new ServiceCalandrier_e();
            Date d3=new Date(2001,17,20);
            Date d2=new Date(2022,05,18);
            Date d1=new Date(2020,06,12);
     Examen e1=new Examen(1,5,d3,55);
      Examen e2=new Examen(45,57,d3,54);
      Note n1=new Note(1,5,7788,70);
      Note n2=new Note(2,225,31,525);     
      Note n3=new Note(3,25,30,25);
      Calandrier_e c1=new Calandrier_e(1,5,7788,4,6,"math");
      Calandrier_e c2=new Calandrier_e(1,15,3,65,44,"fr"); 
   
   
      
        try {
         // se.update(e1);
          // sn.update(n1);
         //  no.ajouter1(n1);
           no.ajouter(n3);
          //  ca.ajouter(c1);
            //ca.delete(c2);
no.update(n2);
          //ca.update(c2);
 //Calandrier_e ce=ca.rechercheCalandrier_eParid_classe(44);
  //System.out.println(ce);
List<Calandrier_e> lst=ca.Triparnom();
System.out.println(lst);

       //se.ajouter1(e1);
           
            //se.ajouter(e1);
          
         //   List<Examen>lt=se.readAll();
           
            //System.out.println(lt);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
