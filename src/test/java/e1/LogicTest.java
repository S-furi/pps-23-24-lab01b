package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;

public class LogicTest {
    final int boardSize = 5;
    private final Logics randomPositionLogic = new LogicsImpl(boardSize);

    final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
    final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);
    final Logics initialPositionLogic = new LogicsImpl(boardSize, knightPosition, pawnPosition);

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
            (i, j) -> this.randomPositionLogic.hasKnight(i, j),
            "Logic's board should contain only one knight.")
        );
    }

    @Test
    void logicShouldContainOnePawn() {
        assertTrue(checkBoardHasOnlyOnePieceOfAKind(
            (i, j) -> this.randomPositionLogic.hasPawn(i, j),
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
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.randomPositionLogic.hit(this.boardSize + 1, this.boardSize + 1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.randomPositionLogic.hit(-1, -1))
        );
    }

    @Test
    void initialKingPositionShouldNotHitPawn() {
        final Pair<Integer, Integer> knightPosition = this.getPiecePosition((i, j) -> this.randomPositionLogic.hasKnight(i, j));
        assertFalse(this.randomPositionLogic.hit(knightPosition.getX(), knightPosition.getY()));
    }

    @Test
    void testIllegalKnightMove() {
        final Pair<Integer, Integer> knightPosition = this.getPiecePosition((i, j) -> this.randomPositionLogic.hasKnight(i, j));
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

        assertFalse(this.randomPositionLogic.hit(newXPosition, newYPosition));
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

    @Test
    void testHitSuccess() {
        assertTrue(initialPositionLogic.hit(pawnPosition.getX(), pawnPosition.getY()));
    }
}
