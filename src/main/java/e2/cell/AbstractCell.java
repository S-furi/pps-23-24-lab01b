package e2.cell;

import e2.Pair;

public class AbstractCell implements Cell {
    protected final Pair<Integer, Integer> position;
    protected final CellType type;

    public AbstractCell(final Pair<Integer, Integer> position, final CellType type) {
        this.position = position;
        this.type = type;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public Object getType() {
        return this.type;
    }

    @Override
    public boolean isAdjacent(final Cell other) {
        return Math.abs(this.position.getX() - other.getPosition().getX()) <= 1
                && Math.abs(this.position.getY() - other.getPosition().getY()) <= 1;
    }
}
