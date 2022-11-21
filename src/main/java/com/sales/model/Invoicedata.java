/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

import java.util.ArrayList;

/**
 *
 * @author Mohamed Ali
 */

public class Invoicedata {
    private int number;
    private String date;
    private String customername;
    private ArrayList<Linedata>lines;
    private double totalofinvoice;
    
    
    public Invoicedata() {
    }

    public Invoicedata(int number, String date, String customername) {
        this.number = number;
        this.date = date;
        this.customername = customername;
    }
    
    public double getTotalofinvoice()
    {
        
        double total= 0.0;
        for(Linedata line:getLines() )
        {
            total+= line.getTotalLines();
        }
        return total;
    }
    
    
      public ArrayList<Linedata> getLines() {
        if(lines==null){
            lines=new ArrayList<>();
        }
        return lines;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
    

    @Override
    public String toString() {
        return "Invoicedata{" + "number=" + number + ", date=" + date + ", customername=" + customername + '}';
    }

   public String getAsStyle() {
        return number + "," + date + "," + customername;
    }
    
    
}
