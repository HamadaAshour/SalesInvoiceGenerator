/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesInvoiceGeneratorModel;

/**
 *
 * @author FuTuRe
 */
public class Invoice_Line {
    private Invoice_Header header ;
    private double price;
    private int count;
    private String itemName;
   

    public Invoice_Line() {
    }

    public Invoice_Line(Invoice_Header header, String itemName, double price, int count) {
        this.header = header;
       
        this.itemName = itemName;
        
        this.price = price;
        
        this.count = count;
    }

    public Invoice_Header getHeader() {
        return header;
    }

    
    public void setHeader(Invoice_Header header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    
    
    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public double getTotalLine(){
        return count * price;
    }

    @Override
    public String toString() {
       
        return  header.getNumber() + "," + itemName + "," + price + "," + count ;
    }

    
}
