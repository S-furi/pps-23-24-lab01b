package e2.cell;

import e2.Pair;

public class EmptyCell extends AbstractCell {

    public EmptyCell(final Pair<Integer, Integer> position) {
        super(position, CellType.EMPTY);
    }
}
