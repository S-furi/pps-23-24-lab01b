package e1;

import java.util.Optional;
import java.util.Random;

public class RandomPositioningPolicy implements PositioningPolicy {
    private final int size;
    private final Random random;
    private final Pair<Integer, Integer> pawn;
    private final Pair<Integer, Integer> knight;

    public RandomPositioningPolicy(final int size, final Optional<Long> seed) {
        this.size = size;
        this.random = seed.isPresent() ? new Random(seed.get()) : new Random();
        this.pawn = randomEmptyPosition();
        this.knight = randomEmptyPosition();
    }

    @Override
    public Pair<Integer, Integer> getKnightPosition() {
        return this.knight;
    }

    @Override
    public Pair<Integer, Integer> getPawnPosition() {
        return this.pawn;
    }

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	final Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn != null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
}
