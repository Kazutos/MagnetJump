package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class
BarTest {

    @Test
    public void testCreationOfHorizontalBar() {
        Bar bar = new Bar(0, 0, 5, BarPosition.HORIZONTAL);
        Unit[] units = bar.getUnits();

        assertEquals(5, units.length);
        assertEquals(units[0], new Unit(0, 0));
        assertEquals(units[1], new Unit(1, 0));
        assertEquals(units[2], new Unit(2, 0));
        assertEquals(units[3], new Unit(3, 0));
        assertEquals(units[4], new Unit(4, 0));
    }


    @Test
    public void testCreationOfVerticalBar() {
        Bar bar = new Bar(4, 5, 4, BarPosition.VERTICAL);
        Unit[] units = bar.getUnits();

        assertEquals(4, units.length);
        assertEquals(units[0], new Unit(4, 5));
        assertEquals(units[1], new Unit(4, 6));
        assertEquals(units[2], new Unit(4, 7));
        assertEquals(units[3], new Unit(4, 8));
    }

}