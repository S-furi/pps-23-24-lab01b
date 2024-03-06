package e1.positioning;

import e1.Pair;

public class DeterministicPositioningPolicy implements PositioningPolicy {

    private final int size;
    private final Pair<Integer, Integer> pawnPosition;
    private Pair<Integer, Integer> knightPosition;

    public DeterministicPositioningPolicy(int size, final Pair<Integer, Integer> knightPosition, final Pair<Integer, Integer> pawnPosition) {
        if (size <= 1)  {
            throw new IllegalArgumentException("Cannot instanciate a grid with dimensions <= 1.");
        }
        this.size = size;
        checkPositions(knightPosition, pawnPosition);
        this.knightPosition = knightPosition;
        this.pawnPosition = pawnPosition;
    }

    private void checkPositions(final Pair<Integer, Integer> knightPosition, final Pair<Integer, Integer> pawnPosition) {
        if (knightPosition.equals(pawnPosition)) {
            throw new IllegalArgumentException("Knight should be placed in a different position than Pawn.");
        } else if (!isPositionInsideBoundaries(knightPosition) || !isPositionInsideBoundaries(pawnPosition)) {
            throw new IllegalArgumentException("Positions must be placed inside the " + this.size + "x" + this.size + "grid!");
        }
    }

    private boolean isPositionInsideBoundaries(final Pair<Integer, Integer> position) {
        return (position.getX() >= 0 && position.getX() < this.size)
                && (position.getY() >= 0 && position.getY() < this.size);
    }

    @Override
    public Pair<Integer, Integer> getKnightPosition() {
        return this.knightPosition;
    }

    @Override
    public Pair<Integer, Integer> getPawnPosition() {
        return this.pawnPosition;
    }

    @Override
    public void moveKnight(final int row, final int col) {
        final Pair<Integer, Integer> position = new Pair<>(row, col);
        if (!isPositionInsideBoundaries(position)) {
            throw new IndexOutOfBoundsException();
        }
        this.knightPosition = position;
    }
}
