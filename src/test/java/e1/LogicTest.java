package e1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class LogicTest {
    @Test
    void testCreateLogic() {
        final Logics logics = new LogicsImpl(5);
        assertNotNull(logics);
    }

    @Test
    void logicShouldContainOneKnight() {
        final int boardSize = 5;
        final Logics logic = new LogicsImpl(boardSize);
        
        boolean knightFound = false;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (knightFound == true && logic.hasKnight(i, j)) {
                    fail("Logic's board should contain only one knight");
                }

                if (logic.hasKnight(i, j)) {
                    knightFound = true;
                }
            }
        }

        assertTrue(knightFound);
    }
}
