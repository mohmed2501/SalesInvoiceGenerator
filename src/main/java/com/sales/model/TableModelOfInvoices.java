/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mohamed Ali
 */
public class TableModelOfInvoices extends AbstractTableModel {
    private ArrayList<Invoicedata>invoices;
    private String [] thecoulms={"No.","Date","Customer","Total"};

    @Override
    public int getRowCount() {
        return invoices.size();
        }

    public TableModelOfInvoices(ArrayList<Invoicedata> invoices) {
        this.invoices = invoices;
    }
    

    @Override
    public int getColumnCount() {
        return thecoulms.length;
         }
    
    @Override
    public String getColumnName(int coulmn)
    {
        return thecoulms[coulmn];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoicedata invoice=invoices.get(rowIndex);
        switch(columnIndex){
            case 0:return invoice.getNumber();
            case 1:return invoice.getDate();
           case 2:return invoice.getCustomername();
            case 3:return invoice.getTotalofinvoice();
            default :return "";
        }
                
       }
    
}
