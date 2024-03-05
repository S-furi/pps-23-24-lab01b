package e1.positioning;

import e1.Pair;

public interface PositioningPolicy {

    Pair<Integer, Integer> getKnightPosition();

    Pair<Integer, Integer> getPawnPosition();

    void moveKnight(int row, int col);
}
