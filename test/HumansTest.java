import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

class HumansTest {
    @Test
    void testHumansToStringMethod(){
        Humans human = new Humans();
        assertEquals("HP: 10 | STR: 4", human.toString());
    }
}