import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

class FightTest {

    @Test
    void testFightBetweenGoblinAndHuman(){
        Humans human = new Humans();
        Goblins goblin = new Goblins();
        Fight fight = new Fight();

        assertEquals(human, fight.attack(human, goblin));
    }

}