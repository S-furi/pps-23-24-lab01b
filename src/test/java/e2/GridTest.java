package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.cell.Cell;
import e2.cell.EmptyCell;

public class GridTest {
    private final int size = 7;
    private final int numberOfMines = 2;
    private Grid grid;

    @BeforeEach
    void setUp() {
        this.grid = new GridImpl(size, numberOfMines);
    }

    @Test
    void newGridShouldNotBeNull() {
        assertNotNull(new GridImpl(size, numberOfMines));
    }

    @Test
    void testNumberOfMinesShouldMatch() {
        assertEquals(this.numberOfMines, this.grid.getMines().size());
    }

    @Test
    void testHasMineShouldBeTrueOnAMine() {
        final Cell mine = this.grid.getMines().get(0);
        assertTrue(this.grid.hasMine(mine.getPosition()));
    }

    @Test
    void testHasMineShouldBeFalseOnAnEmptyCell() {
        Cell cell = null;

        // Find first EmptyCell
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final var pos = new Pair<>(i, j);
                if (!this.grid.hasMine(pos)) {
                    cell = this.grid.getCellAtPosition(pos).get();
                    break;
                }
            }
        }
        assertNotNull(cell);
    }


    @Test
    void testNumberOfAdjacentMinesOfFullySurroundedCell() {
        final var onlyOneEmptyCellGrid = new GridImpl(this.size, (this.size * this.size) - 1);

        final Cell cell = IntStream.range(0, this.size * this.size)
            .mapToObj(i -> new Pair<Integer, Integer>(i / this.size, i % this.size))
            .filter(pos -> !onlyOneEmptyCellGrid.hasMine(pos))
            .findAny()
            .map(pos -> new EmptyCell(pos)).get();

        // Depends if the EmptyCell is in corners, borders or surrounded by mines in every edge.
        final List<Integer> expectedValues = List.of(3, 5, 8);
        assertTrue(expectedValues.contains(onlyOneEmptyCellGrid.getNumberOfAdjacentMines(cell.getPosition())));
    }

    @Test
    void testEmptyCenterCellShouldHaveEightNeighbors() {
        final Grid noMinesGrid = new GridImpl(3, 0);
        final Cell center = noMinesGrid.getCellAtPosition(new Pair<>(1, 1)).get();

        final List<Cell> expectedNeighbors = List.of(
            new EmptyCell(new Pair<>(0, 0)),
            new EmptyCell(new Pair<>(0, 1)),
            new EmptyCell(new Pair<>(0, 2)),
            new EmptyCell(new Pair<>(1, 0)),
            new EmptyCell(new Pair<>(1, 2)),
            new EmptyCell(new Pair<>(2, 0)),
            new EmptyCell(new Pair<>(2, 1)),
            new EmptyCell(new Pair<>(2, 2))
        );

        final var neighbors = noMinesGrid.getCellNeighborhood(center);
        assertTrue(neighbors.containsAll(expectedNeighbors));
    }

    @Test
    void testEmptyTopLeftCornerCellShouldHaveThreeNeighbors() {
        final Grid noMinesGrid = new GridImpl(3, 0);
        final Cell topLeftCorner = noMinesGrid.getCellAtPosition(new Pair<>(0, 0)).get();

        final List<Cell> expectedNeighbors = List.of(
            new EmptyCell(new Pair<>(1, 0)),
            new EmptyCell(new Pair<>(1, 1)),
            new EmptyCell(new Pair<>(0, 1))
        );

        final var neighbors = noMinesGrid.getCellNeighborhood(topLeftCorner);
        assertTrue(neighbors.containsAll(expectedNeighbors));
    }

    @Test
    void testEmptyBottomEdgeCellShouldHaveFiveNeighbors() {
        final Grid noMinesGrid = new GridImpl(3, 0);
        final Cell bottomEdge = noMinesGrid.getCellAtPosition(new Pair<>(2, 1)).get();

        final List<Cell> expectedNeighbors = List.of(
            new EmptyCell(new Pair<>(2, 0)),
            new EmptyCell(new Pair<>(2, 2)),
            new EmptyCell(new Pair<>(1, 0)),
            new EmptyCell(new Pair<>(1, 1)),
            new EmptyCell(new Pair<>(1, 2))
        );

        final var neighbors = noMinesGrid.getCellNeighborhood(bottomEdge);
        assertTrue(neighbors.containsAll(expectedNeighbors));
    }
}