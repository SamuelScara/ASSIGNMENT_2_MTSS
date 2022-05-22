////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EItemTest {
    EItem item;

    @Before
    public void initialize(){
        item = new EItem(EItem.category.Processore, "Asrock", 267.35);
    }

    @Test
    public void testGetItemType(){
        assertEquals(EItem.category.Processore, item.getItemType());
    }

    @Test
    public void testGetNome(){
        assertEquals("Asrock", item.getName());
    }

    @Test
    public void testGetPrice(){
        assertEquals(267.35, item.getPrice(), 1e-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegative(){
        new EItem(EItem.category.Processore, "Asrock", -267.35);
    }
}