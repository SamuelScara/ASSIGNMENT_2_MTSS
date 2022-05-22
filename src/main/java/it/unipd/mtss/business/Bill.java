////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;


import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import java.time.LocalTime;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user, LocalTime orderTime) throws BillException;
}