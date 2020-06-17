/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.itextpdf.text.Document;
import com.esprit.Entite.Reparateur;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.Service.ServiceReparateur;
import com.esprit.Utils.DataBase;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author OMEN
 */
public class pdf {
      private Connection con;
        private Statement ste;
    public pdf()  {
          
               
           
}
    public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
        con = DataBase.getInstance().getConnection();

                ste=con.createStatement();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                 
                 ResultSet rs=ste.executeQuery("select * from reparateur");
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(5) ;
                //create a cell object
                PdfPCell table_cell;
                                
                              
                                table_cell=new PdfPCell(new Phrase("Nom"));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("Prenom"));
                                my_report_table.addCell(table_cell);
                                
                                table_cell=new PdfPCell(new Phrase("NumTel"));
                                my_report_table.addCell(table_cell);
                                
                                table_cell=new PdfPCell(new Phrase("nbr_velo_repare"));
                                my_report_table.addCell(table_cell);
                                
                                table_cell=new PdfPCell(new Phrase("Experience"));
                                my_report_table.addCell(table_cell);
                                
                                
                              
               
                while (rs.next()) {  
                                            
                               
                                String Nom=rs.getString("Nom");
                                table_cell=new PdfPCell(new Phrase(Nom));
                                my_report_table.addCell(table_cell);
                              
                                String Prenom=rs.getString("Prenom");
                                table_cell=new PdfPCell(new Phrase(Prenom));
                                my_report_table.addCell(table_cell);
                                
                                String NumTel=rs.getString("NumTel");
                                table_cell=new PdfPCell(new Phrase(NumTel));
                                my_report_table.addCell(table_cell);
                                
                                String nbr_velo_repare=rs.getString("nbr_velo_repare");
                                table_cell=new PdfPCell(new Phrase(nbr_velo_repare));
                                my_report_table.addCell(table_cell);
                                
                                String Experience=rs.getString("Experience");
                                table_cell=new PdfPCell(new Phrase(Experience));
                                my_report_table.addCell(table_cell);
                                
                              // int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                //table_cell=new PdfPCell(new Phrase(prix));
                                //my_report_table.addCell(table_cell);
                               /* int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);*/
                               

                }
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */
                 rs.close();
                ste.close();
                con.close();
        
    }
     
}