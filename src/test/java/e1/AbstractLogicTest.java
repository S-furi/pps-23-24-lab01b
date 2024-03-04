package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public abstract class AbstractLogicTest {
    protected final int boardSize = 5;
    protected Logics logic;

    protected final BiPredicate<Integer, Integer> knightPredicate = (i, j) -> this.logic.hasKnight(i, j);
    protected final BiPredicate<Integer, Integer> pawnPredicate = (i, j) -> this.logic.hasPawn(i, j);

    @BeforeAll
    void setUp() {
        this.logic = createLogic();
    }

    protected abstract Logics createLogic();

    @Test
    void testCreateLogicIsNotNull() {
        final Logics newLogics = new LogicsImpl(this.boardSize);
        assertNotNull(newLogics);
    }

    @Test
    void testLogicCreationWithSizeZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(0));
    }

    @Test
    void logicShouldContainOneKnight() {
        assertTrue(checkBoardHasOnlyOnePieceOfAKind(this.knightPredicate, "Logic's board should contain only one knight."));
    }

    @Test
    void logicShouldContainOnePawn() {
        assertTrue(checkBoardHasOnlyOnePieceOfAKind(this.pawnPredicate, "Logic's board should contain only one pawn."));
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
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logic.hit(this.boardSize + 1, this.boardSize + 1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logic.hit(-1, -1))
        );
    }

    @Test
    void initialKingPositionShouldNotHitPawn() {
        final Pair<Integer, Integer> knightPosition = this.getPiecePosition(this.knightPredicate);
        assertFalse(this.logic.hit(knightPosition.getX(), knightPosition.getY()));
    }


    protected Pair<Integer, Integer> getPiecePosition(final BiPredicate<Integer, Integer> pieceCondition) {
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
