package inf101.v20.rogue101.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoldTest {

    Gold gold = new Gold();

    /**
     * Test that Carrot symbol is G.
     */
    @Test
    void testGetSymbol() {
        assertEquals('G',gold.getSymbol());
    }
    @Test
    void testGoldSize(){
        assertEquals(10, gold.getSize());
    }
    @Test

    void testGoldDefence(){
        assertEquals(4, gold.getDefence());
    }

    @Test

    void testShortNameGold(){
        assertEquals("gold", gold.getShortName());
    }
    @Test

    void testLongNameGold(){
        assertEquals("Au, gold", gold.getLongName());
    }
    @Test

    void testNameGold(){
        assertTrue(gold.getLongName().contains(gold.getShortName()));
    }

}

