////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
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
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2011, 9, 29));
    }

    @Test
    public void testSum() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Scheda_Madre, "Asrock", 267.35));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Intel", 156.43));
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "Razer", 98.99));

        assertEquals(522.77, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }


    @Test
    public void testElementNullInList(){
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech", 60.50));
        itemsOrdered.add(null);
        itemsOrdered.add(new EItem(EItem.category.Scheda_Madre, "Asrock", 267.35));


        try{
            bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00));
        }catch (BillException exc){
            assertEquals("La lista degli item ordinati contiene un valore null", exc.getMessage());
        }
    }

    @Test
    public void testNullList() throws  BillException{
        itemsOrdered = null;

        try{
            bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00));
        }catch(BillException exc){
            assertEquals("La lista degli item ordinati è uguale a null", exc.getMessage());
        }
    }

    @Test
    public void testUserNull(){
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech", 60.50));
        try{
            bill.getOrderPrice(itemsOrdered, null, LocalTime.of(14,00));
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

        assertEquals(3756.35, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }

    @Test
    public void testGiftCheapestMouse() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Roccat Burst Core", 34));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Razer Viper 8K", 69.42));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Corsair Dark Core RGB Pro Wireless", 110.50));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Steelseries Rival 5", 44.20));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Razer Basilisk V3", 62.42));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Razer Basilisk X Hyperspeed", 50));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Corsair Sabre Pro", 44.90));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Logitech G305 Lightspeed", 33.40));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Cooler Master MM720", 32));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Razer Viper Ultimate", 120.50));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Asus Rog Spatha", 132));

        assertEquals(701.34, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }

    @Test
    public void testDiscountMouseKey() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Roccat Burst Core", 34));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Razer Viper 8K", 69.42));
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Corsair Dark Core RGB Pro Wireless", 110.50));
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "MSI Vigor GK20", 24));
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "Cooler Master CK530 V2", 86.30));
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "Logitech G915 LIGHTSPEED TKL", 269.40));

        assertEquals(569.62, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }

    @Test
    public void testDiscount1000() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Tastiera, "Logitech G915 LIGHTSPEED TKL", 269.40));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Intel Box Core i5 5-12600KF", 262.40));
        itemsOrdered.add(new EItem(EItem.category.Processore, "Amd 7452", 2552.30));
        assertEquals(2775.69, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }

    @Test
    public void test30Items(){
        for(int i = 0; i< 31; i++){
            itemsOrdered.add(new EItem(EItem.category.Tastiera, "Tastiera", 50));
        }
        try{
            bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00));
        }catch(BillException exc){
            assertEquals("Ci sono piu di 30 oggetti ordinati", exc.getMessage());
        }
    }

    @Test
    public void test2euroTax() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Mouse", 4.99));

        assertEquals(6.99, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(14,00)), 1e-4);
    }

    @Test
    public void testFree() throws BillException{
        itemsOrdered.add(new EItem(EItem.category.Mouse, "Trust", 9.81));
        bill.giveaway.r.setSeed(10);
        assertEquals(11.81, bill.getOrderPrice(itemsOrdered, user, LocalTime.of(18,30)), 1e-4);
    }
}