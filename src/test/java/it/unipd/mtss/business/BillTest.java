////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillTest {
    BillImplemented bill = null;
    User user = null;
    List<EItem> itemsOrdered = null;

    @Before
    public void initialize(){
        bill = new BillImplemented();
        itemsOrdered = new ArrayList<EItem>();
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, 29));
    }

    @Test
    public void testSum() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech", 60.50));
        itemsOrdered.add(new EItem(EItem.category.Scheda_Madre, "Asrock", 267.35));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Intel", 156.43));
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "Razer", 98.99));

        assertEquals(583.27, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }


    @Test
    public void testElementNullInList(){
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech", 60.50));
        itemsOrdered.add(null);
        itemsOrdered.add(new EItem(EItem.category.Scheda_Madre, "Asrock", 267.35));


        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch (BillException exc){
            assertEquals("La lista degli item ordinati contiene un valore null", exc.getMessage());
        }
    }

    @Test
    public void testNullList() throws  BillException{
        itemsOrdered = null;

        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch(BillException exc){
            assertEquals("La lista degli item ordinati è uguale a null", exc.getMessage());
        }
    }

    @Test
    public void testUserNull(){
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech", 60.50));
        try{
            bill.getOrderPrice(itemsOrdered, user);
        }catch (BillException exc){
            assertEquals("L'utente inserito è uguale a null", exc.getMessage());
        }

    }

    @Test
    public void testDiscount5Proc() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Processore, "Intel Core i5-9500", 192.40));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Amd 7452", 2552.30));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Amd Ryzen Box 3500", 166.20));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Amd Ryzen 5 5500", 134.10));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Intel Box Core i5 5-12600KF", 262.40));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Amd Ryzen 9 5950X", 516));

        assertEquals(3756.35, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }
}