package e1.positioning;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
