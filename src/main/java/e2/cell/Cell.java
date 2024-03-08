package e2.cell;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    CellType getType();

    CellStatus getStatus();

    boolean isAdjacent(Cell other);

    void click();

    void disable();
}
