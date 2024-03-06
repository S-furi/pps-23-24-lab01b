package e1;

import e1.hit.HitStatus;
import e1.hit.HitStrategy;
import e1.positioning.PositioningPolicy;

public class LogicsImpl implements Logics {
	
	private final int size;
	private final PositioningPolicy positioningPolicy;
	private final HitStrategy hitStrategy;
	 
	public LogicsImpl(final int size, final PositioningPolicy positioningPolicy, final HitStrategy hitStrategy) {
		  if (size <= 1) {
			throw new IllegalArgumentException("Size must be >= 1.");
		  }
		  this.size = size;
		  this.positioningPolicy = positioningPolicy;
		  this.hitStrategy = hitStrategy;
    }

	@Override
	public boolean hit(int row, int col) {
		final Pair<Integer, Integer> knightPosition = this.positioningPolicy.getKnightPosition();
		final Pair<Integer, Integer> pawnPosition = this.positioningPolicy.getPawnPosition();
		final Pair<Integer, Integer> newPosition = new Pair<>(row, col);

		if ((row < 0 || col < 0) || (row >= this.size || col >= this.size)) {
			throw new IndexOutOfBoundsException();
		}

		final HitStatus hitStatus = this.hitStrategy.hit(knightPosition, pawnPosition, newPosition);
		if (hitStatus.equals(HitStatus.NOT_ALLOWED)) {
			return false;
		}
		this.positioningPolicy.moveKnight(row, col);
		return hitStatus.equals(HitStatus.SUCCESS);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.positioningPolicy.getKnightPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.positioningPolicy.getPawnPosition().equals(new Pair<>(row,col));
	}
}
