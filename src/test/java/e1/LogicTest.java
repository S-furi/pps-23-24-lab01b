package e1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testLogicCreationWithSizeZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(0));
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

    private boolean checkBoardHasOnlyOnePieceOfAKind(final BiPredicate<Integer, Integer> pieceCondition, final String failMessage) {
        boolean found = false;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (found && pieceCondition.test(i, j)) {
                    fail(failMessage);
                }

                if (pieceCondition.test(i, j)) {
                    found = true;
                }
            }
        }
        return found;
    }

    @Test
    void testOutOfBoundsIndexShouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(this.boardSize + 1, this.boardSize + 1));
    }

    @Test
    void initialKingPositionShouldNotHitPawn() {
        final Pair<Integer, Integer> kingPosition = this.getPiecePosition((i, j) -> this.logics.hasKnight(i, j));
        assertFalse(this.logics.hit(kingPosition.getX(), kingPosition.getY()));
    }

    @Test
    void testIllegalKnightMove() {
        final Pair<Integer, Integer> kingPosition = this.getPiecePosition((i, j) -> this.logics.hasKnight(i, j));
        int newYPosition = 0;
        int newXPosition = 0;
        
        if (kingPosition.getX() == this.boardSize - 1 && kingPosition.getY() < this.boardSize - 1) {
            newXPosition = kingPosition.getX() - 1;
            newYPosition = kingPosition.getY() + 1;
        } else if (kingPosition.getX() < this.boardSize - 1 && kingPosition.getY() == this.boardSize - 1) {
            newXPosition = kingPosition.getX() + 1;
            newYPosition = kingPosition.getY() - 1;
        } else if (kingPosition.getX() == this.boardSize - 1 && kingPosition.getX() == this.boardSize - 1) {
            newXPosition = kingPosition.getX() - 1;
            newYPosition = kingPosition.getY() - 1;
        } else {
            newXPosition = kingPosition.getX() + 1;
            newYPosition = kingPosition.getY() + 1;
        }

        assertFalse(this.logics.hit(newXPosition, newYPosition));
    }

    private final Pair<Integer, Integer> getPiecePosition(final BiPredicate<Integer, Integer> pieceCondition) {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (pieceCondition.test(i, j)) {
                    return new Pair<>(i, j);
                }
            }
        }
        // Having tested the presence of the two pieces in the above tests,
        // the return statement below will never be yielded.
        return new Pair<Integer,Integer>(-1, -1);
    }
}
