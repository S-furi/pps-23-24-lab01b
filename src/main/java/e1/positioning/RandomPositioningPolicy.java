package e1.positioning;

import java.util.Optional;
import java.util.Random;

import e1.Pair;

public class RandomPositioningPolicy implements PositioningPolicy {
    private final int size;
    private final Random random;
    private final DeterministicPositioningPolicy positioningPolicy;


    public RandomPositioningPolicy(final int size, final Optional<Long> seed) {
        this.size = size;
        this.random = seed.isPresent() ? new Random(seed.get()) : new Random();
        final Pair<Integer, Integer> pawn = randomEmptyPosition(Optional.empty());
        final Pair<Integer, Integer> knight = randomEmptyPosition(Optional.of(pawn));
        this.positioningPolicy = new DeterministicPositioningPolicy(this.size, knight, pawn);
    }

    @Override
    public Pair<Integer, Integer> getKnightPosition() {
        return this.positioningPolicy.getKnightPosition();
    }

    @Override
    public Pair<Integer, Integer> getPawnPosition() {
        return this.positioningPolicy.getPawnPosition();
    }

	private final Pair<Integer,Integer> randomEmptyPosition(final Optional<Pair<Integer, Integer>> pawnPosition) {
    	final Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(this.size), this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return pawnPosition.isPresent() && pawnPosition.get().equals(pos) ? randomEmptyPosition(pawnPosition) : pos;
    }
}
