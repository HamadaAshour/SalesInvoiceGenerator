/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SIG.controller;

import SalesInvoiceGeneratorModel.Invoice_Header;
import SalesInvoiceGeneratorModel.Invoice_Line;
import SalesInvoiceGeneratorModel.Invoice_Line_Table;
import SalesInvoiceGeneratorDesign.Dialog_For_Header;
import SalesInvoiceGeneratorDesign.Dialog_For_Line;
import SalesInvoiceGeneratorDesign.SIG_Frame;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;




/**
 *
 * @author FuTuRe
 */
public class Action_Listener_Buttons implements ActionListener{
    private SIG_Frame frame;
    private Dialog_For_Header dialogForHeader ;
    private Dialog_For_Line dialogForLine;

    public Action_Listener_Buttons(SIG_Frame frame) {
        this.frame = frame;
    } 
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Create Invoice":
                CreateNewInvoice();
                break;
          
            case "Delete Invoice":
                DeleteInvoice();
                break;
            
            case "Add Item":
                Save();           
                break;
            
            case "Delete Item":
                Cancel();
                break;
            
            
            case "OkCreatNewInvoice":
                OkCreatNewInvoice();
                break;
            
            case "CancelNewInvoice":
                CancelNewInvoice();
                break;
            
            case "OkCreatNewLine":
                OkCreatNewLine();
                break;
            
            case "CancelNewLine":
                CancelNewLine();
                  break;
           
        }
       
    }

    private void CreateNewInvoice() {
        dialogForHeader=new Dialog_For_Header(frame);
       
        dialogForHeader.setVisible(true);
    }

    
    private void DeleteInvoice() {       
        int indexOfSelectedRow=frame.getjTable_Invoice().getSelectedRow();
              if (indexOfSelectedRow != -1) {
                frame.getInvoicesArr().remove(indexOfSelectedRow);
                 frame.getHeaderTable().fireTableDataChanged();
                  frame.getjTable_Items().setModel(new Invoice_Line_Table(null)) ;
                   frame.setLinesArr(null);
                    frame.getjTextField_CustomerName().setText("");
                     frame.getjLabel_InvoiceNumber().setText("");
                      frame.getjLabel_InvoiceTotal().setText("");
                       frame.getjTextField_InvoiceDate().setText("");
            
                       printInvoices();
            
        }
    }

    private void Save() {
       
        dialogForLine=new Dialog_For_Line(frame);
        
        dialogForLine.setVisible(true);
    }

    private void Cancel() {
        int indexOfSelectedRow=frame.getjTable_Items().getSelectedRow();
       
        int indexOfSelectedRowHeader=frame.getjTable_Invoice().getSelectedRow();
        
        if (indexOfSelectedRow != -1) {
           
            frame.getLinesArr().remove(indexOfSelectedRow);
           
            Invoice_Line_Table lineTable = (Invoice_Line_Table) frame.getjTable_Items().getModel();
            
            lineTable.fireTableDataChanged();
            
            
            frame.getHeaderTable().fireTableDataChanged();
            
            Invoice_Header h =frame.getInvoicesArr().get(indexOfSelectedRow);
            
            
            frame.getjLabel_InvoiceTotal().setText(h.getTotalInvoice()+"");  
        }
            frame.getjTable_Invoice().setRowSelectionInterval(indexOfSelectedRowHeader, indexOfSelectedRowHeader);
            printInvoices();
        
    }

        private void OkCreatNewInvoice() {
        dialogForHeader.setVisible(false);
        String S1 = dialogForHeader.getJTextFieldCustomerName().getText() ;
        String S2=dialogForHeader.getJTextFieldInvoiceDate().getText();
        Date date=new Date();
        try {
            date =SIG_Frame.date.parse(S2);
        } catch (ParseException e ) {
            JOptionPane.showMessageDialog(frame, "Cann't parse date", "Wrong Date Format", JOptionPane.ERROR_MESSAGE);
        } // parse to be revised
        int num=0;
        for(Invoice_Header header : frame.getInvoicesArr() ){
            if (header.getNumber()>num) {
                num=header.getNumber();
            }
        }
             num++;
        Invoice_Header newheader=new Invoice_Header(num, date, S1);
        frame.getInvoicesArr().add(newheader);
        frame.getHeaderTable().fireTableDataChanged();
       dialogForHeader.dispose();
       dialogForHeader=null;
       printInvoices();
    }

         private void CancelNewInvoice() {
       dialogForHeader.setVisible(false);
       dialogForHeader.dispose();
       dialogForHeader=null;
       
    }

    private void OkCreatNewLine() {
        dialogForLine.setVisible(false);
        String s1=dialogForLine.getJTextFielditemName().getText();
        String s2=dialogForLine.getJTextFielditemCount().getText();
        String s3 =dialogForLine.getItemPriceField().getText();
        int count=1;
        double price=1.0;
        try {
            count = Integer.parseInt(s2);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Enter valid Count ", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            price = Double.parseDouble(s3);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Enter valid price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        int indexOfSelectedRow=frame.getjTable_Invoice().getSelectedRow();
        if (indexOfSelectedRow != -1) {
            Invoice_Header h =frame.getInvoicesArr().get(indexOfSelectedRow);
            Invoice_Line newline =new Invoice_Line(h, s1, price, count);
          
            frame.getLinesArr().add(newline);
            // frame.getLineTable().fireTableDataChanged();
            Invoice_Line_Table lineTable = (Invoice_Line_Table) frame.getjTable_Items().getModel();
            lineTable.fireTableDataChanged();
           
            frame.getHeaderTable().fireTableDataChanged();
            frame.getjLabel_InvoiceTotal().setText(h.getTotalInvoice()+"");        
        }
            frame.getjTable_Invoice().setRowSelectionInterval(indexOfSelectedRow, indexOfSelectedRow);

        dialogForLine.dispose();
        dialogForLine=null;
        printInvoices();
    }


    private void CancelNewLine() {
        dialogForLine.setVisible(false);
        dialogForLine.dispose();
        dialogForLine=null;
       
}
    private void printInvoices() {
        System.out.println("*****************");
        for (Invoice_Header header : frame.getInvoicesArr()) {
            System.out.println(header);
        }
              System.out.println("*****************");
    }
}