package e1.hit;

import e1.Pair;

public class KnightHitStrategy implements HitStrategy {

    public KnightHitStrategy() { }

    @Override
    public HitStatus hit(
            final Pair<Integer, Integer> knightPosition,
            final Pair<Integer, Integer> pawnPosition,
            final Pair<Integer, Integer> newKnightPosition) {

        if (this.canMove(knightPosition, newKnightPosition)) {
            return pawnPosition.equals(newKnightPosition) ? HitStatus.SUCCESS : HitStatus.MISS;
        }
        return HitStatus.NOT_ALLOWED;
    }

    @Override
    public boolean canMove(Pair<Integer, Integer> fromPosition, Pair<Integer, Integer> toPosition) {
        final int x = toPosition.getX() - fromPosition.getX();
        final int y = toPosition.getY() - fromPosition.getY();
        if (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) {
            return true;
        }
        return false;
    }
}
