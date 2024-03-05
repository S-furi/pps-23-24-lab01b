package e1;

import e1.positioning.PositioningPolicy;

public class LogicsImpl implements Logics {
	
	private final int size;
	private final PositioningPolicy positioningPolicy;
	 
	public LogicsImpl(final int size, final PositioningPolicy positioningPolicy) {
		  if (size <= 1) {
			throw new IllegalArgumentException("Cannot instanciate a grid with dimensions <= 1.");
		  }
		  this.size = size;
		  this.positioningPolicy = positioningPolicy;
    }

	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		final var knight = this.positioningPolicy.getKnightPosition();
		int x = row - knight.getX();
		int y = col - knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.positioningPolicy.moveKnight(row, col);
			return this.positioningPolicy.getPawnPosition().equals(this.positioningPolicy.getKnightPosition());
		}
		return false;
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
