package e2.cell;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    CellType getType();

    boolean isAdjacent(Cell other);

    void click();

    boolean isClicked();

    void disable();

    boolean isDisabled();
}
