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
            totale = totale + item.getPrice();
        }
        return totale;
    }
}