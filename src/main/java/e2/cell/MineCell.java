package e2.cell;

import e2.Pair;

public class MineCell extends AbstractCell {

    public MineCell(final Pair<Integer, Integer> position) {
        super(position, CellType.MINE);
    }
}
