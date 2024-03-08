package e2;

import java.util.List;
import java.util.Optional;

import e2.cell.Cell;

public interface Grid {
    boolean hasMine(Pair<Integer, Integer> position);

    List<? extends Cell> getMines();

    int getNumberOfAdjacentMines(Pair<Integer, Integer> position);

    Optional<? extends Cell> getCellAtPosition(Pair<Integer, Integer> position);

    List<Cell> getCellNeighborhood(Cell cell);
}
