package e1.positioning;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Pair;

public abstract class AbstractPositioningPolicyTest {
    protected PositioningPolicy positioningPolicy;
    protected final int size = 5;

    @BeforeEach
    void setUp() {
        this.positioningPolicy = createPositioningPolicy();
    }

    protected abstract PositioningPolicy createPositioningPolicy();

    @Test
    void testPositioningiPolicyShouldHaveNotNullPiecesPositions() {
        assertAll(
            () -> assertNotNull(this.positioningPolicy.getKnightPosition()),
            () -> assertNotNull(this.positioningPolicy.getPawnPosition())
        );
    }

    @Test
    void testMoveKnight() {
        final var start = this.positioningPolicy.getKnightPosition();
        final Pair<Integer, Integer> destination = new Pair<>(1, 1);
        this.positioningPolicy.moveKnight(destination.getX(), destination.getY());
        assertNotEquals(start, this.positioningPolicy.getKnightPosition());
    }
}
