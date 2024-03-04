package e1;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import e1.positioning.PositioningPolicy;
import e1.positioning.RandomPositioningPolicy;

@TestInstance(Lifecycle.PER_CLASS)
public class RandomLogicTest extends AbstractLogicTest {

    @Override
    protected Logics createLogic() {
        return new LogicsImpl(super.boardSize, new RandomPositioningPolicy(this.boardSize, Optional.empty()));
    }

    @Override
    protected PositioningPolicy createPositioningPolicy() {
        return new RandomPositioningPolicy(this.boardSize, Optional.empty());
    }

    @Test
    void testIllegalKnightMove() {
        // TODO: make a better version of this test!
        final Pair<Integer, Integer> knightPosition = super.getPiecePosition(super.knightPredicate);
        int newYPosition = 0;
        int newXPosition = 0;
        
        if (knightPosition.getX() == this.boardSize - 1 && knightPosition.getY() < this.boardSize - 1) {
            newXPosition = knightPosition.getX() - 1;
            newYPosition = knightPosition.getY() + 1;
        } else if (knightPosition.getX() < this.boardSize - 1 && knightPosition.getY() == this.boardSize - 1) {
            newXPosition = knightPosition.getX() + 1;
            newYPosition = knightPosition.getY() - 1;
        } else if (knightPosition.getX() == this.boardSize - 1 && knightPosition.getX() == this.boardSize - 1) {
            newXPosition = knightPosition.getX() - 1;
            newYPosition = knightPosition.getY() - 1;
        } else {
            newXPosition = knightPosition.getX() + 1;
            newYPosition = knightPosition.getY() + 1;
        }

        assertFalse(this.logic.hit(newXPosition, newYPosition));
    }
}
