package e1.hit;

import e1.Pair;

public interface HitStrategy {

    HitStatus hit(
        Pair<Integer, Integer> knightPosition,
        Pair<Integer, Integer> pawnPosition,
        Pair<Integer, Integer> newKnightPosition
    );

    boolean canMove(Pair<Integer, Integer> fromPosition, Pair<Integer, Integer> toPosition);
}
