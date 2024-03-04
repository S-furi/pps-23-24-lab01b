package e1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class DeterministicLogicTest extends LogicTest {

    final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
    final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);

    @Test
    void cannotCreateLogicWithOverlappingPieces() {
        final Pair<Integer, Integer> pos = new Pair<>(0, 0);
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(boardSize, pos, pos));
    }

    @Test
    void testSuccessfulHit() {
        assertTrue(super.logic.hit(pawnPosition.getX(), pawnPosition.getY()));
    }

    @Override
    protected Logics createLogic() {
        return new LogicsImpl(super.boardSize, knightPosition, pawnPosition);
    }
}
