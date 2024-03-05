package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import e1.positioning.DeterministicPositioningPolicy;
import e1.positioning.PositioningPolicy;

public class DeterministicLogicTest extends AbstractLogicTest {

    private final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
    private final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);

    @Override
    protected Logics createLogic(final PositioningPolicy positioningPolicy) {
        return new LogicsImpl(
            super.boardSize,
            positioningPolicy
        );
    }

    @Override
    protected PositioningPolicy createPositioningPolicy() {
        return new DeterministicPositioningPolicy(this.boardSize, this.knightPosition, this.pawnPosition);
    }

    @Test
    void cannotCreateLogicWithOverlappingPieces() {
        final Pair<Integer, Integer> pos = new Pair<>(0, 0);
         
        assertThrows(IllegalArgumentException.class, () -> 
            new LogicsImpl(this.boardSize, new DeterministicPositioningPolicy(this.boardSize, pos, pos))
        );
    }

    @Test
    void testSuccessfulHit() {
        assertTrue(super.logic.hit(pawnPosition.getX(), pawnPosition.getY()));
    }

    @Test
    void testKnightMovement() {
        final Pair<Integer, Integer> destinationPosition = new Pair<>(2, 1);
        assertFalse(super.logic.hit(destinationPosition.getX(), destinationPosition.getY()));
        assertEquals(destinationPosition, this.positioningPolicy.getKnightPosition());
    }
}
