////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem;



public class BillImplemented implements Bill {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double totale = 0.0;
        int countProc = 0;
        int countMouse = 0;
        double menoCaroMouse = Double.MAX_VALUE;
        double menoCaro = Double.MAX_VALUE; //processore

        if (itemsOrdered == null) {
            throw new BillException("La lista degli item ordinati è uguale a null");
        }
        if (itemsOrdered.contains(null)) {
            throw new BillException("La lista degli item ordinati contiene un valore null");
        }
        if (user == null) {
            throw new BillException("L'utente inserito è uguale a null");
        }
        for (EItem item : itemsOrdered) {
            if(item.getItemType() == EItem.category.Processore){
                countProc = countProc + 1;
                if(item.getPrice() < menoCaro){
                    menoCaro = item.getPrice();
                }
            }else if(item.getItemType() == EItem.category.Mouse){
                countMouse = countMouse + 1;
                if(item.getPrice() < menoCaroMouse){
                    menoCaroMouse = item.getPrice();
                }
            }
            totale = totale + item.getPrice();
        }
        if(countProc > 5){
            totale = totale - (menoCaro / 2);
        }
        if(countMouse > 10){
            totale = totale - menoCaroMouse;
        }
        return totale;
    }
}