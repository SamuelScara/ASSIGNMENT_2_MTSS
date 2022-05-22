////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import it.unipd.mtss.business.Bill;

import java.util.List;



public class EItem {
    public enum category {Processore, Scheda_Madre, Tastiera, Mouse};

    private category itemType;
    private String name;
    private double price;

    public EItem(category itemType, String name, double price){
        this.itemType = itemType;
        this.name = name;
        if(price >= 0.0D){
            this.price = price;
        }else{
            throw new IllegalArgumentException("Il prezzo deve per forza essere >=0");
        }
    }
    public category getItemType(){
        return itemType;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}