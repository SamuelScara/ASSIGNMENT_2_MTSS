////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import java.time.LocalTime;
import it.unipd.mtss.model.User;

public interface BillGiveaway {
    boolean giveAwayOrder(User user, LocalTime orderTime);
}