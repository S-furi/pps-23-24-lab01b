package e1.hit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Pair;

public class HitStrategyTest {

    private HitStrategy hitStrategy;
    private final Pair<Integer, Integer> initialPosition = new Pair<>(0, 0);
    private final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);

    @BeforeEach
    void setUp() {
        hitStrategy = new KnightHitStrategy();
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
            () -> assertEquals(HitStatus.NOT_ALLOWED, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, maxPosition)),
            () -> assertEquals(HitStatus.NOT_ALLOWED, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, negativePosition))
        );
    }

    @Test
    void testHitMisses() {
        final Pair<Integer, Integer> newPosition = new Pair<>(2, 1);
        assertEquals(HitStatus.MISS, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, newPosition));
    }

    @Test
    void testHitSuccess() {
        assertEquals(HitStatus.SUCCESS, this.hitStrategy.hit(this.initialPosition, this.pawnPosition, this.pawnPosition));
    }
}
