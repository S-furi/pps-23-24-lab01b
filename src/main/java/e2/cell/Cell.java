package e2.cell;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    CellType getType();

    void click();
    
    void disable();

    void toggleFlag();
    
    boolean isAdjacent(Cell other);
    
    boolean isClicked();

    boolean isDisabled();

    boolean hasFlag();
}
