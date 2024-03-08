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

public class LogicsTest {
    private final int size = 7;
    private final int numberOfMines = 2;
    private Logics logics;

    @BeforeEach
    void setUp() {
        this.logics = new LogicsImpl(this.size, this.numberOfMines);
    }

    @Test
    void testLogicCreationShouldNotBeNull() {
        assertNotNull(new LogicsImpl(this.size, this.numberOfMines));
    }

    @Test
    void logicsShouldHaveRightAmountOfMines() {
        final List<Pair<Integer, Integer>> minesFound = this.logics.getMinesPositions();
        assertEquals(numberOfMines, minesFound.size());
    }

    @Test
    void testCellFullySurroundedByMines() {
        final var onlyMinesLogics = new LogicsImpl(this.size, (this.size * this.size) - 1);

        final Cell cell = IntStream.range(0, this.size * this.size)
            .mapToObj(i -> new Pair<Integer, Integer>(i / this.size, i % this.size))
            .filter(pos -> !onlyMinesLogics.hasMine(pos))
            .findAny()
            .map(pos -> new EmptyCell(pos)).get();

        // Depends if the EmptyCell is in corners, borders or surrounded by mines in every edge.
        final List<Integer> expectedValues = List.of(3, 5, 8);
        assertTrue(expectedValues.contains(onlyMinesLogics.getNumberOfAdjacentMines(cell.getPosition())));
    }

    @Test
    void cellClickShouldChangeCellStatus() {
        final Pair<Integer, Integer> cellPosition = this.getEmptyCells().get(0).getPosition();
        
        this.logics.click(cellPosition);
        assertTrue(this.logics.getCellAtPosition(cellPosition).get().isClicked());
    }

    @Test
    void clickingOnAnEmptyCellShuldCauseNeighborsAutoClick() {

    }

    private List<? extends Cell> getEmptyCells() {
        return IntStream.range(0, this.size * this.size)
            .mapToObj(i -> new Pair<Integer, Integer>(i / this.size, i % this.size))
            .filter(pos -> !this.logics.hasMine(pos))
            .map(pos -> new EmptyCell(pos))
            .toList();
    }
}
