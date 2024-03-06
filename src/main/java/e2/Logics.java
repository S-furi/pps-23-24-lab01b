package e2;

import java.util.List;

public interface Logics {

    boolean hasMine(Pair<Integer, Integer> position);

    boolean hit(Pair<Integer, Integer> pair);

    List<Pair<Integer, Integer>> getMinesPositions();

    int getNumberOfAdjacentMines(Pair<Integer, Integer> position);
}
