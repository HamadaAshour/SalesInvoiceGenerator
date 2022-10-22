/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGeneratorModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
   

/**
 *
 * @author FuTuRe
 */
public class Invoice_Line_Table extends AbstractTableModel{
    private ArrayList <Invoice_Line> linesArr; 

    public Invoice_Line_Table(ArrayList<Invoice_Line> linesArr) {
        this.linesArr = linesArr;
    }
    
    @Override
    public int getRowCount() {
        if (linesArr==null) {
            return 0;     
        }
        return linesArr.size() ;
    }

    @Override
    public int getColumnCount() {
         return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice_Line Row=linesArr.get(rowIndex);
        switch(columnIndex){
            case 0:
                return Row.getHeader().getNumber();
           
            case 1:
                return Row.getItemName();
            
            case 2:
                return Row.getPrice();
            
            case 3:
                return Row.getCount();
            
            case 4:
                return Row.getTotalLine();
            
            default:
                   return null;
                   
        }  
    }
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0:
            return "No.";
 
            case 1 :
            return "Item Nme";
        
            case 2 :
            return "Item Price";
           
            case 3:
                return "Count";
            case 4: 
                return "Item Total";
            
            default:
            return "";
        }
        
    }
    
}
