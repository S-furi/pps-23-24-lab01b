package e2;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import e2.cell.Cell;
import e2.cell.MineCell;

public class LogicsImpl implements Logics {
    final int size;
    final int numberOfMines;
    private final List<? extends Cell> mines;
    private final Random random = new Random();

    public LogicsImpl(final int size, final int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = this.generateMines();
        System.out.println("Generated mines at positions:");
        this.getMinesPositions().forEach(System.out::println);
    }
    
    @Override
    public boolean hasMine(final Pair<Integer, Integer> position) {
        return this.getMinesPositions().stream().anyMatch(pos -> pos.equals(position));
    }
    
    private List<? extends Cell> generateMines() {
        final List<Pair<Integer, Integer>> generatedMines = new ArrayList<>();
        for (int i = 0; i < this.numberOfMines; i++) {
            Pair<Integer, Integer> minePosition = generateRandomPosition();
            while (generatedMines.contains(minePosition)) {
                minePosition = generateRandomPosition();
            }
            generatedMines.add(minePosition);
        }
        return generatedMines.stream().map(pos -> new MineCell(pos)).toList();
    }

    private Pair<Integer, Integer> generateRandomPosition() {
        return new Pair<>(this.random.nextInt(this.size), this.random.nextInt(this.size));
    }

    @Override
    public boolean hit(final Pair<Integer, Integer> position) {
        return this.hasMine(position);
    }

    @Override
    public List<Pair<Integer, Integer>> getMinesPositions() {
        return this.mines.stream().map(mine -> mine.getPosition()).toList();
    }
}
    