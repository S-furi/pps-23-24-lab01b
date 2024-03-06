package e2.cell;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void adjacentCellsShouldBeAdjacent() {
        final Cell cell = new EmptyCell(new Pair<>(1, 1));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                if (pos == cell.getPosition()) {
                    continue;
                }

                final Cell otherCell = new EmptyCell(pos);
                assertTrue(cell.isAdjacent(otherCell));
            }
        }
    }

    @Test
    void nonAdjacentCellShouldNotBeAdjacent() {
        final Cell cell = new EmptyCell(new Pair<>(0, 0));
        final Cell other = new EmptyCell(new Pair<>(100, 100));

        assertFalse(cell.isAdjacent(other));
        assertFalse(other.isAdjacent(cell));
    }
}
