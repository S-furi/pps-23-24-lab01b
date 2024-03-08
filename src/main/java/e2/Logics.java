package e2;

import java.util.List;
import java.util.Optional;

import e2.cell.Cell;

public interface Logics {

    boolean hasMine(Pair<Integer, Integer> position);

    void click(Pair<Integer, Integer> position);

    void disable(Pair<Integer, Integer> position);

    void toggleFlag(Pair<Integer, Integer> position);

    List<Pair<Integer, Integer>> getMinesPositions();

    Optional<? extends Cell> getCellAtPosition(Pair<Integer, Integer> position);

    int getNumberOfAdjacentMines(Pair<Integer, Integer> position);

    boolean isThereVictory();
}
