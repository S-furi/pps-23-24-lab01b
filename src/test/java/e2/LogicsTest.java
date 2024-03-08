package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
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
    void hittingAMineShouldReturnTrue() {
        final List<Pair<Integer, Integer>> mines = this.logics.getMinesPositions();
        assertTrue(this.logics.hit(mines.get(0)));
    }

    @Test
    void notHittingAMineShouldReturnFalse() {
        final List<Pair<Integer, Integer>> mines = this.logics.getMinesPositions();
        final Random random = new Random();
        Pair<Integer, Integer> pos = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));

        while (mines.contains(pos))  {
            pos = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        }

        assertFalse(this.logics.hit(pos));
    }

    @Test
    void testCellFullySurroundedByMines() {
        final var onlyMinesLogics = new LogicsImpl(this.size, (this.size * this.size) - 1);

        final Cell cell = IntStream.range(0, this.size * this.size)
            .mapToObj(i -> new Pair<Integer, Integer>(i / this.size, i % this.size))
            .filter(pos -> !onlyMinesLogics.hasMine(pos))
            .findAny()
            .map(pos -> new EmptyCell(pos)).get();

        assertEquals(8, onlyMinesLogics.getNumberOfAdjacentMines(cell.getPosition()));
    }
}
