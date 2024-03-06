package e1.hit;

public class HitStrategyFactory {
    public static HitStrategy createStandardKnightHitStrategy() {
        return new KnightHitStrategy();
    }
}
