/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;

import com.sales.model.Invoicedata;
import com.sales.model.Linedata;
import com.sales.model.TableModelOfInvoices;
import com.sales.model.TableModelOfLines;
import com.sales.view.InvoiceConversation;
import com.sales.view.LineConversation;
import com.sales.view.first_Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mohamed Ali
 */
public class Controller implements ActionListener,ListSelectionListener {
    
    private first_Frame shape;
    private InvoiceConversation invoiceconversation;
    private LineConversation lineconversation;
    
    public Controller(first_Frame shape)
    {
        this.shape=shape;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actioncommand=e.getActionCommand();
      System.out.println("action "+actioncommand);
      
      switch(actioncommand){
          case "Load File":
              loadFile();
              break;
                case "Save File":
                    saveFile();
              break;
                case "x":
                    createNewInvoice();
              break;
                case "y":
                    deleteInvoice();
              break;
                case "w":
                    createNewitem();
              break;
                case "z":
                    deleteItem();
              break;
                case "makeInvoiceCancel":
                    makeInvoiceCancel();
              break;
                case "makeInvoiceOK":
                    makeInvoiceOK();
              break;
                case "makeLineOK":
                    makeLineOK();
              break;
                case "makeLineCancel":
                        makeLineCancel();
              break;
      }
            
    }

    private void saveFile() {
        ArrayList<Invoicedata>invoices=shape.getInvoices();
        String headers = "";
        String lines = "";
        for (Invoicedata invoicedata : invoices) {
            String invStyle = invoicedata.getAsStyle();
            headers += invStyle;
            headers += "\n";

            for (Linedata linedata : invoicedata.getLines()) {
                String lineStyle = linedata.getAsStyle();
                lines += lineStyle;
                lines += "\n";
            }
            
        }
        
        System.out.println("inspect the data");
         try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(shape);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter headerfilewrite = new FileWriter(headerFile);
                headerfilewrite.write(headers);
                headerfilewrite.flush();
                headerfilewrite.close();
                result = fc.showSaveDialog(shape);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter linefilewrite = new FileWriter(lineFile);
                    linefilewrite.write(lines);
                    linefilewrite.flush();
                    linefilewrite.close();
                }
            }
        } catch (Exception ex) {

        }
    
        
 
         }
      @Override
    public void valueChanged(ListSelectionEvent e) {
        int indexselected=shape.getInvoiceTable().getSelectedRow();
        if(indexselected!=-1){
        
        System.out.println("selected row "+ indexselected);
        Invoicedata current = shape.getInvoices().get(indexselected);
        shape.getInvoicenumberLabel().setText(""+current.getNumber());
        shape.getInvoiceDateLabel().setText(current.getDate());
        shape.getCustomerNameLabel().setText(current.getCustomername());
        shape .getInvoiceTotalLabel().setText(""+current.getTotalofinvoice());
        TableModelOfLines tablemodeloflines=new  TableModelOfLines(current.getLines());
        shape.getLineTable().setModel(tablemodeloflines);
        tablemodeloflines.fireTableDataChanged();
        }
        
    }

    private void loadFile() {
         JFileChooser fc=new JFileChooser();
          try {
            int result = fc.showOpenDialog(shape);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerofLines = Files.readAllLines(headerPath);
                System.out.println(" Read the Invoices");
                
                ArrayList<Invoicedata> invoicesofarray=new ArrayList<>(); 
                for(String headerLine:headerofLines){
                  String[] headerarrange= headerLine.split(",");
                  int invoiceNumber=Integer.parseInt(headerarrange[0]);
                  String date=(headerarrange[1]);
                  String name=(headerarrange[2]);
                  Invoicedata invoicedata=new Invoicedata(invoiceNumber,date,name);
                  invoicesofarray.add(invoicedata);
                  
                            
                          }
                  System.out.println("inspect all data");
                   result = fc.showOpenDialog(shape);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineofLines = Files.readAllLines(linePath);
                    System.out.println(" Read the Lines");
                    for (String lineLine : lineofLines) {
                        String lineParts[] = lineLine.split(",");
                        int invoicenumber = Integer.parseInt(lineParts[0]);
                        String itemname = lineParts[1];
                        double price = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        Invoicedata invo=null;
                        for (Invoicedata invoice : invoicesofarray) {
                            if (invoice.getNumber() == invoicenumber) {
                                invo = invoice;
                                break;
                            }
                        }
                        
                        Linedata line= new Linedata(itemname,price,count,invo);
                        invo.getLines().add(line);
                    }
                    System.out.println("inspect all data");
                }
                shape.setInvoices(invoicesofarray);
                TableModelOfInvoices tablemodelofinvoices=new TableModelOfInvoices(invoicesofarray);
                shape.setTablemodelofinvoices(tablemodelofinvoices);
                shape.getInvoiceTable().setModel(tablemodelofinvoices);
                shape.getTablemodelofinvoices().fireTableDataChanged();
                
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
       }

    private void createNewInvoice() {
        
         invoiceconversation=new InvoiceConversation(shape);
        invoiceconversation.setVisible(true);
       }

    private void deleteInvoice() {
     int selectedrow= shape.getInvoiceTable().getSelectedRow();
        if(selectedrow!=-1)
        {
            shape.getInvoices().remove(selectedrow);
            shape.getTablemodelofinvoices().fireTableDataChanged();
        }
        
    }

    private void createNewitem() {
         lineconversation=new  LineConversation(shape);
         lineconversation.setVisible(true);
        
         }

    private void deleteItem() {
        
        int selectedinvo=shape.getInvoiceTable().getSelectedRow();
       int selectedrow= shape.getLineTable().getSelectedRow();
        if(selectedinvo!=-1 && selectedrow!=-1)
        {
            Invoicedata invoicedata=shape.getInvoices().get(selectedinvo);
            invoicedata.getLines().remove(selectedrow);
            TableModelOfLines tablemodeloflines=new TableModelOfLines(invoicedata.getLines());
            shape.getLineTable().setModel(tablemodeloflines);
            tablemodeloflines.fireTableDataChanged();
            shape.getTablemodelofinvoices().fireTableDataChanged();
        }
        
    }

    private void makeInvoiceCancel() {
        invoiceconversation.setVisible(false);
        invoiceconversation.dispose();
        invoiceconversation=null;
        }

    private void makeInvoiceOK() {
      String date=  invoiceconversation.getInvoiceDateField().getText();
      String customer=invoiceconversation.getCustomerNameField().getText();
      int number =shape.getNextInvoiceNumber();
      Invoicedata invoicedata =new Invoicedata(number,date,customer);
      shape.getInvoices().add(invoicedata);
      shape.getTablemodelofinvoices().fireTableDataChanged();
      invoiceconversation.setVisible(false);
       invoiceconversation.dispose();
        invoiceconversation=null;
      }

    private void makeLineOK() {
         String item=lineconversation.getItemNameField().getText();
         String countstr=lineconversation.getItemCountField().getText();
         String pricestr=lineconversation.getItemPriceField().getText();
         int count =Integer.parseInt(countstr);
         double price=Double.parseDouble(pricestr);
         int selectedinvoice=shape.getInvoiceTable().getSelectedRow();
         if(selectedinvoice!=-1){
             
             Invoicedata invoicedata=shape.getInvoices().get(selectedinvoice);
             Linedata linedata =new Linedata(item ,price,count,invoicedata);
             invoicedata.getLines().add(linedata);
             TableModelOfLines tablemodeloflines=(TableModelOfLines) shape.getLineTable().getModel();
             tablemodeloflines.fireTableDataChanged();
             shape.getTablemodelofinvoices().fireTableDataChanged();
                      }
        
         lineconversation.setVisible(false);
          lineconversation.dispose();
           lineconversation=null;
    }

    private void makeLineCancel() {
         lineconversation.setVisible(false);
          lineconversation.dispose();
           lineconversation=null;
        
    }

  
    
}
