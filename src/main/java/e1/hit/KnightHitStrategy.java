package e1.hit;

import e1.Pair;

public class KnightHitStrategy implements HitStrategy {

    public KnightHitStrategy() { }

    @Override
    public HitStatus hit(
            final Pair<Integer, Integer> knightPosition,
            final Pair<Integer, Integer> pawnPosition,
            final Pair<Integer, Integer> newKnightPosition) {
        final int x = newKnightPosition.getX() - knightPosition.getX();
        final int y = newKnightPosition.getY() - knightPosition.getY();
        if (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) {
            return pawnPosition.equals(newKnightPosition) ? HitStatus.SUCCESS : HitStatus.MISS;
        }
        return HitStatus.NOT_ALLOWED;
    }
}
