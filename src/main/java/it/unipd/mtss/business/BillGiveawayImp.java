////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import it.unipd.mtss.model.User;

public class BillGiveawayImp implements BillGiveaway{
    List<User> selectedUser;
    Random r;



    public BillGiveawayImp(){
        selectedUser = new ArrayList<User>();
        r = new Random();
    }

    public boolean isOrderOk(User user, LocalTime orderTime){
        if(user.getDataNascita() != null && Period.between(user.getDataNascita(), LocalDate.now()).getYears() < 18){
            if(selectedUser.size() < 10 && !selectedUser.contains(user)){
                if(orderTime.getHour() == 18 || orderTime.getHour() == 19){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean giveAwayOrder(User user, LocalTime orderTime){
        if(user == null){
            throw new IllegalArgumentException("L'user inserito è null");
        }
        if(orderTime == null){
            throw new IllegalArgumentException("La data dell'ordine è null");
        }
        if(isOrderOk(user, orderTime)){
            if(r.nextInt(100) < 30){
                selectedUser.add(user);
                return true;
            }
        }
        return false;
    }
}