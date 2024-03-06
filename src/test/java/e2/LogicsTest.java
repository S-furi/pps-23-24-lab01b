package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        final List<Pair<Integer, Integer>> minesFound = this.extractMinesPosition();
        assertEquals(numberOfMines, minesFound.size());
    }

    @Test
    void hittingAMineShouldReturnTrue() {
        final List<Pair<Integer, Integer>> mines = this.extractMinesPosition();
        assertTrue(this.logics.hit(mines.get(0)));
    }

    @Test
    void notHittingAMineShouldReturnFalse() {
        final List<Pair<Integer, Integer>> mines = this.extractMinesPosition();
        final Random random = new Random();
        Pair<Integer, Integer> pos = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));

        while (mines.contains(pos))  {
            pos = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        }

        assertFalse(this.logics.hit(pos));
    }

    private List<Pair<Integer, Integer>> extractMinesPosition() {
        final List<Pair<Integer, Integer>> minesFound = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final Pair<Integer, Integer> pos = new Pair<Integer, Integer>(i, j);
                if (logics.hasMine(pos)) {
                    minesFound.add(pos);
                }
            }
        }
        return List.copyOf(minesFound);
    }

}
