////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem;
import java.time.LocalTime;



public class BillImplemented implements Bill {
    BillGiveawayImp giveaway;

    public BillImplemented(){
        giveaway = new BillGiveawayImp();
    }
    public double getOrderPrice(List<EItem> itemsOrdered, User user, LocalTime orderTime) throws BillException {
        double totale = 0.0D;
        int countProc = 0, countMouse = 0, countTastiere = 0;
        double menoCaroMouse = Double.MAX_VALUE;
        double menoCaroProc = Double.MAX_VALUE; //processore
        double menoCaroTastiere = Double.MAX_VALUE;
        double totaleMouseTastiere = 0.0D;

        if (itemsOrdered == null) {
            throw new BillException("La lista degli item ordinati è uguale a null");
        }
        if (itemsOrdered.contains(null)) {
            throw new BillException("La lista degli item ordinati contiene un valore null");
        }
        if (user == null) {
            throw new BillException("L'utente inserito è uguale a null");
        }
        if(itemsOrdered.size() > 30){
            throw new BillException("Ci sono piu di 30 oggetti ordinati");
        }
        for (EItem item : itemsOrdered) {
            if(item.getItemType() == EItem.category.Processore){
                countProc = countProc + 1;
                if(item.getPrice() < menoCaroProc){
                    menoCaroProc = item.getPrice();
                }
            }
            if(item.getItemType() == EItem.category.Mouse){
                totaleMouseTastiere = totaleMouseTastiere + item.getPrice();
                countMouse = countMouse + 1;
                if(item.getPrice() < menoCaroMouse){
                    menoCaroMouse = item.getPrice();
                }
            }
            if(item.getItemType() == EItem.category.Tastiera){
                totaleMouseTastiere = totaleMouseTastiere + item.getPrice();
                countTastiere = countTastiere + 1;
                if(item.getPrice() < menoCaroTastiere){
                    menoCaroTastiere = item.getPrice();
                }
            }
            totale = totale + item.getPrice();
        }
        if(countProc > 5){
            totale = totale - (menoCaroProc / 2);
            return totale;
        }
        if(countMouse > 10){
            totale = totale - menoCaroMouse;
            return totale;
        }

        if(countTastiere == countMouse){
            return totale - Math.min(menoCaroTastiere, menoCaroMouse);
        }

        if(totale > 1000){
            totale = totale * 0.9;
            return totale;
        }
        if(totale < 10){
            totale = totale + 2;
            return totale;
        }
        if(giveaway.giveAwayOrder(user, orderTime)){
            return 0;
        }
        return totale;
    }
}
