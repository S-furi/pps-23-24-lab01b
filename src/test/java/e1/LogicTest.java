package e1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class LogicTest {
    final int boardSize = 5;
    private final Logics logics = new LogicsImpl(boardSize);

    @Test
    void testCreateLogic() {
        final Logics newLogics = new LogicsImpl(this.boardSize);
        assertNotNull(newLogics);
    }

    @Test
    void logicShouldContainOneKnight() {
        boolean knightFound = false;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (knightFound == true && this.logics.hasKnight(i, j)) {
                    fail("Logic's board should contain only one knight");
                }

                if (this.logics.hasKnight(i, j)) {
                    knightFound = true;
                }
            }
        }

        assertTrue(knightFound);
    }
}
