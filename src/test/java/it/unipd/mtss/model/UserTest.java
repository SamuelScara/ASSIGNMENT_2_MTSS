package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class UserTest {
    User user = null;

    @Before
    public void initialize(){
        user = new User("Samuel", "Scara", 1717171717, LocalDate.of(2001, 9, 29));
    }

    @Test
    public void testNullDataNascita(){
        try{
            user = new User("Samuel", "Scara", 1717171717, null);
        }catch(IllegalArgumentException exc){
            assertEquals("La data di nascita inserita è null", exc.getMessage());
        }
    }

    @Test
    public void testDataNascitaFuoriRange(){
        try{
            user = new User("Samuel", "Scara", 1717171717, LocalDate.of(3001, 9, 29));
        }catch (IllegalArgumentException exc){
            assertEquals("La data di nascita inserita deve essere prima di quella di oggi", exc.getMessage());
        }
    }

    @Test
    public void testGetId(){
        assertEquals(1717171717, user.getID());
    }

    @Test
    public void testGetNome(){
        assertEquals("Samuel", user.getNome());
    }

    @Test
    public void testGetCognome(){
        assertEquals("Scara", user.getCognome());
    }

    @Test
    public void testGetDataNascita(){
        assertEquals(LocalDate.of(2001, 9, 29), user.getDataNascita());
    }
}