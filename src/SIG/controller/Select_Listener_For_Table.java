/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SIG.controller;


import java.util.ArrayList;
import SalesInvoiceGeneratorModel.Invoice_Header;
import SalesInvoiceGeneratorModel.Invoice_Line;
import SalesInvoiceGeneratorModel.Invoice_Line_Table;
import SalesInvoiceGeneratorDesign.SIG_Frame;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


/**
 *
 * @author FuTuRe
 */
public class Select_Listener_For_Table implements ListSelectionListener{
   private SIG_Frame frame;

    public Select_Listener_For_Table(SIG_Frame frame) {
        this.frame = frame;
    }
   

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int indexOfSelectedRow=frame.getjTable_Invoice().getSelectedRow();
        System.out.println("Invoice you selected is : " + indexOfSelectedRow);

        if (indexOfSelectedRow != -1) {
            
        Invoice_Header selectedRow =frame.getInvoicesArr().get(indexOfSelectedRow);
        ArrayList<Invoice_Line> lines=selectedRow.getLines();
        Invoice_Line_Table lineTable=new Invoice_Line_Table(lines);
        frame.setLinesArr(lines);
         frame.getjTable_Items().setModel(lineTable);
          frame.getjTextField_CustomerName().setText(selectedRow.getCustomerName());
           frame.getjLabel_InvoiceNumber().setText(selectedRow.getNumber()+"");
            frame.getjLabel_InvoiceTotal().setText(selectedRow.getTotalInvoice()+"");
             frame.getjTextField_InvoiceDate().setText(SIG_Frame.date.format(selectedRow.getDate()));
        
        }
       
    }

    private void getLines() {
     
    }
    
}
