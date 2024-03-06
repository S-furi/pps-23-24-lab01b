package e2;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class LogicsImpl implements Logics {
    final int size;
    final int numberOfMines;
    private final List<Pair<Integer, Integer>> mines;
    private final Random random = new Random();

    public LogicsImpl(final int size, final int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = this.generateMines();
    }
    
    @Override
    public boolean hasMine(final Pair<Integer, Integer> position) {
        return this.mines.contains(position);
    }
    
    private List<Pair<Integer, Integer>> generateMines() {
        final List<Pair<Integer, Integer>> generatedMines = new ArrayList<>();
        for (int i = 0; i < this.numberOfMines; i++) {
            Pair<Integer, Integer> minePosition = generateRandomPosition();
            while (generatedMines.contains(minePosition)) {
                minePosition = generateRandomPosition();
            }
            generatedMines.add(minePosition);
        }
        return List.copyOf(generatedMines);
    }

    private Pair<Integer, Integer> generateRandomPosition() {
        return new Pair<>(this.random.nextInt(this.size), this.random.nextInt(this.size));
    }

    @Override
    public boolean hit(final Pair<Integer, Integer> pair) {
        return this.mines.contains(pair);
    }
}
