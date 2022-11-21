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
public class TableModelOfLines extends AbstractTableModel{
    
  private ArrayList<Linedata>lines;   
   private String [] thecoulms={"No.","Item Name","Item Price","Count","Item Total"};

    public TableModelOfLines(ArrayList<Linedata> lines) {
        this.lines = lines;
    }

    public ArrayList<Linedata> getLines() {
        return lines;
    }
   
    

    @Override
    public int getRowCount() {
        return lines.size();
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
        Linedata line =lines.get(rowIndex);
        switch(columnIndex){
            case 0:return line.invoicedata.getNumber();
             case 1:return line.getItem();
           case 2:return line.getPrice();
            case 3:return line.getCount();
            case 4:return line.getTotalLines();
            default:return "";
        }
    }
}
