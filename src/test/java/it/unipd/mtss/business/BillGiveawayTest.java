////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import it.unipd.mtss.model.User;

public class BillGiveawayTest {
    User user;
    BillGiveawayImp giveaway = null;

    @Before
    public void initialize() {
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2011, 9, 29));
        giveaway = new BillGiveawayImp();
    }

    @Test
    public void testisOrderOk() {
        assertEquals(true, giveaway.isOrderOk(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testUserOver18() {
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, 29));
        assertFalse(giveaway.isOrderOk(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testWrongTime() {
        assertFalse(giveaway.isOrderOk(user, LocalTime.of(14, 30)));
    }


    @Test
    public void testUserGiaSelezionato() {
        giveaway.selectedUser.add(user);
        assertFalse(giveaway.isOrderOk(user, LocalTime.of(18, 30)));
    }


    @Test
    public void test10PiuUser() throws IllegalArgumentException {
        for (int i = 1; i < 10; i++) {
            user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, i));
            giveaway.selectedUser.add(user);
        }
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, 30));
        assertFalse(giveaway.giveAwayOrder(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testUserNull() {
        user = null;
        try {
            giveaway.giveAwayOrder(user, LocalTime.of(18, 30));
        } catch (IllegalArgumentException exc) {
            assertEquals("L'user inserito è null", exc.getMessage());
        }
    }

    @Test
    public void testNullTime() {
        try {
            giveaway.giveAwayOrder(user, null);
        } catch (IllegalArgumentException exc) {
            assertEquals("La data dell'ordine è null", exc.getMessage());
        }
    }
}
