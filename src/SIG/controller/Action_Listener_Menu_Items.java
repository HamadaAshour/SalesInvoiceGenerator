/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SIG.controller;

import SalesInvoiceGeneratorModel.Invoice_Header;
import SalesInvoiceGeneratorModel.Invoice_Header_Table;
import SalesInvoiceGeneratorModel.Invoice_Line;
import SalesInvoiceGeneratorDesign.SIG_Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import javax.swing.JOptionPane;

/**
 *
 * @author FuTuRe
 */
public class Action_Listener_Menu_Items implements ActionListener{
    private SIG_Frame frame;
    

    public Action_Listener_Menu_Items(SIG_Frame frame) {
        this.frame = frame;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Load File":
                LoadFile();
                break;
           
            
            case "Save File":
                SaveFile();
                break;
        }
    }
    private void LoadFile() {
        JOptionPane.showMessageDialog(frame, "Please, select header file!", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser=new JFileChooser();
        try{
        int choice= fileChooser.showOpenDialog(frame);
        if (choice==JFileChooser.APPROVE_OPTION) {
            Path PathOfHeaderFile=Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
           List<String> linesOfHeaderFile;
            linesOfHeaderFile = Files.readAllLines(PathOfHeaderFile);
            ArrayList<Invoice_Header> Headers = new ArrayList<>();
            for(String line :linesOfHeaderFile){
                String[]HeaderElements=line.split(",");
                int code =Integer.parseInt(HeaderElements[0]);
                Date invoiceDate = SIG_Frame.date.parse(HeaderElements[1]);
                Invoice_Header invHeader = new Invoice_Header(code, invoiceDate, HeaderElements[2]);
                    Headers.add(invHeader);      
            }
            frame.setInvoicesArr(Headers);
            System.out.println("Header file read");
            JOptionPane.showMessageDialog(frame, "Please, select lines file!", "Attension", JOptionPane.WARNING_MESSAGE);
            choice= fileChooser.showOpenDialog(frame);
        if (choice==JFileChooser.APPROVE_OPTION) {
            Path PathOfLineFile=Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
           List<String> linesOfLineFile;
            linesOfLineFile = Files.readAllLines(PathOfLineFile);
            ArrayList<Invoice_Line> invline = new ArrayList<>();
            for(String lineOfLineFile :linesOfLineFile){
                String[]lineElements=lineOfLineFile.split(",");
                int code =Integer.parseInt(lineElements[0]);
                double price=Double.parseDouble(lineElements[2]);
                int count=Integer.parseInt(lineElements[3]);
                Invoice_Header inv=frame.getInvoiceHeaderObject(code) ;
                Invoice_Line line=new Invoice_Line(inv, lineElements[1], price, count);
                inv.getLines().add(line);
                
            }
        }
            Invoice_Header_Table headerTable1=new Invoice_Header_Table(Headers);
            frame.setHeaderTable(headerTable1);
            frame.getjTable_Invoice().setModel(headerTable1);
            System.out.println("lines file read");
                   
        }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }catch(ParseException e){
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        
    }
       printInvoices();
    }
    private void SaveFile() {
        ArrayList<Invoice_Header> headers=frame.getInvoicesArr();
        JOptionPane.showMessageDialog(frame, "(select/Create) file to save Invoices Header", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser=new JFileChooser();
        try{
        int choice= fileChooser.showSaveDialog(frame);
        if (choice==JFileChooser.APPROVE_OPTION) {
            File hFile=fileChooser.getSelectedFile();
            FileWriter hFileWriter=new FileWriter(hFile);
            String hStr="";
            String lStr="";
            int i=0;
            int hSize=headers.size();
            for (Invoice_Header header:headers) {
                i++;
                hStr+=header.toString();
                if (i!=hSize) {
                    hStr+="\n";
                }
                int j=0;
                int lSize=header.getLines().size();
                for(Invoice_Line line :header.getLines()){
                    j++;
                    lStr+=line.toString();
                    if (j==lSize && i==hSize) {
                        break;
                    }
                    lStr+="\n";
                    
                }
            }
            JOptionPane.showMessageDialog(frame, "(select/Create) file to save Lines Invoice", "Attension", JOptionPane.WARNING_MESSAGE);
            choice= fileChooser.showSaveDialog(frame);
            File lFile=fileChooser.getSelectedFile();
            FileWriter lFileWriter=new FileWriter(lFile);
            hFileWriter.write(hStr);
            lFileWriter.write(lStr);
            hFileWriter.close();
            lFileWriter.close();
        }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void printInvoices() {
        System.out.println("*****************");
        for (Invoice_Header header : frame.getInvoicesArr()) {
            System.out.println(header);
        }
        System.out.println("*****************");
    }
}
