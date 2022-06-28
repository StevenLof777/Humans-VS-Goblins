import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {

    @Test
    void testGeneratePrintMethod(){
        Game game = new Game();

        Land land = new Land();

        game.generateWorld();

        assertEquals(land.print(), game.generateWorld());
    }


}