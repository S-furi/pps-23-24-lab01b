package e1.hit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Pair;

public class KnightHitStrategyTest {

    private HitStrategy hitStrategy;
    private final Pair<Integer, Integer> initialPosition = new Pair<>(0, 0);
    private final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);

    @BeforeEach
    void setUp() {
        hitStrategy = HitStrategyFactory.createStandardKnightHitStrategy();
    }

    @Test
    void testHitStrategyCreationIsNotNull() {
        assertNotNull(new KnightHitStrategy());
    }

    @Test
    void testHitNotAllowed() {
        final Pair<Integer, Integer> maxPosition = new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE);
        final Pair<Integer, Integer> negativePosition = new Pair<>(-1, -1);
        assertAll(
            () -> assertFalse(this.hitStrategy.canMove(this.initialPosition, maxPosition)),
            () -> assertEquals(HitStatus.NOT_ALLOWED, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, maxPosition)),
            () -> assertFalse(this.hitStrategy.canMove(this.initialPosition, negativePosition)),
            () -> assertEquals(HitStatus.NOT_ALLOWED, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, negativePosition))
        );
    }

    @Test
    void testHitMisses() {
        final Pair<Integer, Integer> newPosition = new Pair<>(2, 1);

        assertTrue(this.hitStrategy.canMove(this.initialPosition, newPosition));
        assertEquals(HitStatus.MISS, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, newPosition));
    }

    @Test
    void testHitSuccess() {
        assertTrue(this.hitStrategy.canMove(this.initialPosition, this.pawnPosition));
        assertEquals(HitStatus.SUCCESS, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, this.pawnPosition));
    }
}
