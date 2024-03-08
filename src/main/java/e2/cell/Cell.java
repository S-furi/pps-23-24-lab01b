package e2.cell;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    CellType getType();

    void click();
    
    void disable();
    
    boolean isAdjacent(Cell other);
    
    boolean isClicked();

    boolean isDisabled();
}
