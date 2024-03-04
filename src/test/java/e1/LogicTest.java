package e1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.BiPredicate;

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
        assertTrue(checkBoardHasOnlyOnePieceOfAKind(
            (i, j) -> this.logics.hasKnight(i, j),
            "Logic's board should contain only one knight.")
        );
    }

    @Test
    void logicShouldContainOnePawn() {
        assertTrue(checkBoardHasOnlyOnePieceOfAKind(
            (i, j) -> this.logics.hasPawn(i, j),
            "Logic's board should contain only one pawn.")
        );
    }

    private boolean checkBoardHasOnlyOnePieceOfAKind(final BiPredicate<Integer, Integer> condition, final String failMessage) {
        boolean found = false;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (found && condition.test(i, j)) {
                    fail(failMessage);
                }

                if (condition.test(i, j)) {
                    found = true;
                }
            }
        }
        return found;
    }
}
