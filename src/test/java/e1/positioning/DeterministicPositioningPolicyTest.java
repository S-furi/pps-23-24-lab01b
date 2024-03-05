package e1.positioning;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import e1.Pair;

public class DeterministicPositioningPolicyTest extends AbstractPositioningPolicyTest {
    final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
    final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);

    @Override
    protected PositioningPolicy createPositioningPolicy() {
        return new DeterministicPositioningPolicy(this.size, this.knightPosition, this.pawnPosition);
    }

    @Test
    void testDeterministicPositioningPolicyCreation() {
        assertNotNull(new DeterministicPositioningPolicy(this.size, this.knightPosition, this.pawnPosition));
    }

    @Test
    void testPositioningPolicyWithOverlappingPositionsShouldThrowException() {
        final Pair<Integer, Integer> pos = new Pair<>(0, 0);
        assertThrows(IllegalArgumentException.class, () -> new DeterministicPositioningPolicy(this.size, pos, pos));
    }

    @Test
    void testPositioningPolicyWithOutOfBoundariesPositionsShouldThrowException() {
        final Pair<Integer, Integer> pos = new Pair<>(-1, -1);

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new DeterministicPositioningPolicy(this.size, pos, this.pawnPosition)),
            () -> assertThrows(IllegalArgumentException.class, () -> new DeterministicPositioningPolicy(this.size, this.knightPosition, pos))
        );
    }

    @Test
    void testDeterministicKnightPosition() {
        assertEquals(this.knightPosition, this.positioningPolicy.getKnightPosition());
    }

    @Test
    void testDeterministicPawnPosition() {
        assertEquals(this.pawnPosition, this.positioningPolicy.getPawnPosition());
    }

    @Test
    void knightShouldMoveMultipleTimes() {
        final Pair<Integer, Integer> initialPosition = this.positioningPolicy.getKnightPosition();
        final Pair<Integer, Integer> movementStep = new Pair<>(1, 1);
        final int movements = 3;
        for (var i = 1; i <= movements; i++) {
            this.positioningPolicy.moveKnight(movementStep.getX() * i, movementStep.getY() * i);
        }

        final Pair<Integer, Integer> expectedPosition = new Pair<>(movementStep.getX() * movements, movementStep.getY() * movements);
        assertAll(
            () -> assertNotEquals(initialPosition, this.positioningPolicy.getKnightPosition()),
            () -> assertEquals(expectedPosition, this.positioningPolicy.getKnightPosition())
        );
    }

    @Test
    void testMovementOutOfBoundsDoesNotChangeKnightPosition() {
        final Pair<Integer, Integer> negativeDestination = new Pair<>(-1, -1);
        final Pair<Integer, Integer> outOfBoundsDestination = new Pair<>(this.size + 1, this.size + 1);

        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class,
                () -> this.positioningPolicy.moveKnight(negativeDestination.getX(), negativeDestination.getY())),
            () -> assertThrows(IndexOutOfBoundsException.class,
                () -> this.positioningPolicy.moveKnight(outOfBoundsDestination.getX(), outOfBoundsDestination.getY()))
        );
    }
}
