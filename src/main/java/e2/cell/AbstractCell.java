package e2.cell;

import e2.Pair;

public abstract class AbstractCell implements Cell {
    protected final Pair<Integer, Integer> position;
    protected final CellType type;
    protected CellStatus status;
    protected boolean hasBeenClicked = false;

    public AbstractCell(final Pair<Integer, Integer> position, final CellType type) {
        this.position = position;
        this.type = type;
        this.status = CellStatus.NOT_CLICKED;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    @Override
    public boolean isAdjacent(final Cell other) {
        return Math.abs(this.position.getX() - other.getPosition().getX()) <= 1
                && Math.abs(this.position.getY() - other.getPosition().getY()) <= 1;
    }

    @Override
    public void click() {
        this.status = CellStatus.CLICKED;
    }

    @Override
    public void disable() {
        if (this.status != CellStatus.CLICKED) {
            throw new IllegalStateException();
        }
        this.status = CellStatus.DISABLED;
    }

    @Override
    public boolean isClicked() {
        return this.status == CellStatus.CLICKED;
    }

    @Override
    public boolean isDisabled() {
        return this.status == CellStatus.DISABLED;
    }
}
