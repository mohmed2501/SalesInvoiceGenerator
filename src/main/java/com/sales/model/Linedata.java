/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

/**
 *
 * @author Mohamed Ali
 */
public class Linedata {
   
    private String item;
    private double price;
    private int count;
    Invoicedata invoicedata;

    public Linedata() {
    }
    

    public Linedata( String item, double price, int count) {
      
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public Linedata( String item, double price, int count, Invoicedata invoice) {
        
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoicedata = invoice;
    }
    public double getTotalLines()
    {
        return price*count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

  

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Linedata{" + "num=" + invoicedata.getNumber() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    public Invoicedata getInvoicedata() {
        return invoicedata;
    }

 
  public String getAsStyle() {
        return invoicedata.getNumber() + "," + item + "," + price +","+count;
    }
   
    
    
    
   }
