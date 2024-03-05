package e1.positioning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;

import e1.Pair;

public class RandomPositioningPolicyTest extends AbstractPositioningPolicyTest {

    private final long seed = 123;
    private final Random random = new Random(seed);
    private final Pair<Integer, Integer> pawn = randomEmptyPosition();
    private final Pair<Integer, Integer> knight = randomEmptyPosition();

    @Override
    protected PositioningPolicy createPositioningPolicy() {
        return new RandomPositioningPolicy(size, Optional.of(seed));
    }

    @Test
    void testRandomPositioningPolicyCreationIsNotNull() {
        assertNotNull(new RandomPositioningPolicy(size, Optional.empty()));
    }

    @Test
    void testKnightPositionWithRandomSeed() {
        assertEquals(this.knight, this.positioningPolicy.getKnightPosition());
    }


    @Test
    void testPawnPositionWithRandomSeed() {
        assertEquals(this.pawn, this.positioningPolicy.getPawnPosition());
    }

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	final Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn != null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
}
