package e2.cell;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    Object getType();

    boolean isAdjacent(Cell other);
}
