package e2.cell;

import e2.Pair;

public abstract class AbstractCell implements Cell {
    private final Pair<Integer, Integer> position;
    private final CellType type;
    private boolean hasFlag;
    private CellStatus status;

    public AbstractCell(final Pair<Integer, Integer> position, final CellType type) {
        this.position = position;
        this.type = type;
        this.status = CellStatus.NOT_CLICKED;
        this.hasFlag = false;
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

    @Override
    public boolean hasFlag() {
        return this.hasFlag;
    }

    @Override
    public void toggleFlag() {
        this.hasFlag = !this.hasFlag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractCell other = (AbstractCell) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (type != other.type)
            return false;
        if (status != other.status)
            return false;
        return true;
    }
}