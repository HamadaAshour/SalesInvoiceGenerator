/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGeneratorModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FuTuRe
 */
public class Invoice_Header {
    private int number;
    private Date date;
    private String customerName;
    private ArrayList<Invoice_Line>lines ;
    private DateFormat d=new SimpleDateFormat("dd-MM-yyyy");

    public Invoice_Header() {
    }

    public Invoice_Header(int number, Date date, String customerName) {
        this.number = number;
        this.date = date;
        this.customerName = customerName;
    }
//Getters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }
     // Setters
    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public ArrayList<Invoice_Line> getLines() {
        if (lines==null) {
            lines=new ArrayList<>();
        }
        return lines;
    }
    
    public void setLines(ArrayList<Invoice_Line> lines) {
        this.lines = lines;
    }
    
    public double getTotalInvoice(){
        double total=0.0;
        for (int i = 0; i < getLines().size(); i++) {
            total += lines.get(i).getTotalLine();
        }
        
        return total;
    
    }

    @Override
    public String toString() {
        return  number + ","+ d.format(date)+ "," + customerName   ;
    }
    
    
}
