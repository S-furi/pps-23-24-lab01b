package e2.cell;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import e2.Pair;

public class CellTest {

    @Test
    void createdEmptyCellShouldNotBeNull() {
        final Cell cell = new EmptyCell(new Pair<>(0, 0));
        assertNotNull(cell);
    }

    @Test
    void createdMineCellShouldNotBeNull() {
        final Cell cell = new MineCell(new Pair<>(0, 0));
        assertNotNull(cell);
    }

    @Test
    void testCellMineType() {
        final Cell cell = new MineCell(new Pair<>(0, 0));
        assertEquals(CellType.MINE, cell.getType());
    }

    @Test
    void testCellEmptyType() {
        final Cell cell = new EmptyCell(new Pair<>(0, 0));
        assertEquals(CellType.EMPTY, cell.getType());
    }

    @Test
    void cellPositionShouldNotBeNull() {
        final Pair<Integer, Integer> pos = new Pair<>(0, 0);
        final Cell emptyCell = new EmptyCell(pos);
        final Cell mineCell = new MineCell(pos);

        assertAll(
            () -> assertEquals(pos, emptyCell.getPosition()),
            () -> assertEquals(pos, mineCell.getPosition())
        );
    }
}
