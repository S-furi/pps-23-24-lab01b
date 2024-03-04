package e1;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class RandomLogicTest extends AbstractLogicTest {

    @Override
    protected Logics createLogic() {
        return new LogicsImpl(super.boardSize);
    }

    @Test
    void testIllegalKnightMove() {
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
