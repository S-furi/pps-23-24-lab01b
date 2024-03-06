package e2;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Random;

import e2.cell.Cell;
import e2.cell.EmptyCell;
import e2.cell.MineCell;

public class LogicsImpl implements Logics {
    final int size;
    final int numberOfMines;
    private final List<? extends Cell> mines;
    private final List<? extends Cell> emptyCells;
    private final Random random = new Random();

    public LogicsImpl(final int size, final int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = this.generateMines();
        this.emptyCells = this.generateEmptyCells();
        
        System.out.println("Generated mines at positions:");
        this.getMinesPositions().forEach(System.out::println);
    }
    
    @Override
    public boolean hasMine(final Pair<Integer, Integer> position) {
        return this.getMinesPositions().stream().anyMatch(pos -> pos.equals(position));
    }

    @Override
    public boolean hit(final Pair<Integer, Integer> position) {
        return this.hasMine(position);
    }

    @Override
    public List<Pair<Integer, Integer>> getMinesPositions() {
        return this.mines.stream().map(mine -> mine.getPosition()).toList();
    }

    @Override
    public int getNumberOfAdjacentMines(final Pair<Integer, Integer> position) {
        final var cell = this.getCellAtPosition(position);
        if (cell.isEmpty()) {
            throw new IllegalArgumentException("Position " + position + " does not correspond to any cell.");
        }
        return Math.toIntExact(this.mines.stream().filter(mine -> cell.get().isAdjacent(mine)).count());
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

    private List<? extends Cell> generateEmptyCells() {
        final List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                final var pos = new Pair<>(i, j);
                if (this.hasMine(pos)) {
                    continue;
                }
                cells.add(new EmptyCell(pos));
            }
        }
        return List.copyOf(cells);
    }

    private Pair<Integer, Integer> generateRandomPosition() {
        return new Pair<>(this.random.nextInt(this.size), this.random.nextInt(this.size));
    }

    private Optional<? extends Cell> getCellAtPosition(final Pair<Integer, Integer> position) {
        return this.emptyCells.stream().filter(cell -> cell.getPosition().equals(position)).findFirst();
    }
}
    