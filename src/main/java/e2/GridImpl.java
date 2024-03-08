package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import e2.cell.Cell;
import e2.cell.MineCell;
import e2.cell.EmptyCell;

public class GridImpl implements Grid {
    private final int size;
    private final int numberOfMines;
    private final Random random = new Random();

    private final List<? extends Cell> mines;
    private final List<? extends Cell> emptyCells;

    public GridImpl(final int size, final int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = this.generateMines();
        this.emptyCells = this.generateEmptyCells();

        System.out.println("Generated mines at positions:");
        this.mines.stream().map(mine -> mine.getPosition()).forEach(System.out::println);
    }

    @Override
    public int getNumberOfAdjacentMines(final Pair<Integer, Integer> position) {
        final var cell = this.getCellAtPosition(position);
        if (cell.isEmpty()) {
            throw new IllegalArgumentException("Position " + position + " does not correspond to any cell.");
        }
        return Math.toIntExact(this.mines.stream().filter(mine -> cell.get().isAdjacent(mine)).count());
    }

    @Override
    public Optional<? extends Cell> getCellAtPosition(final Pair<Integer, Integer> position) {
        return Stream.concat(this.emptyCells.stream(), this.mines.stream())
            .filter(cell -> cell.getPosition().equals(position)).findFirst();
    }

    @Override
    public boolean hasMine(final Pair<Integer, Integer> position) {
        return this.mines.stream().anyMatch(mine -> mine.getPosition().equals(position));
    }


    @Override
    public List<? extends Cell> getMines() {
        return this.mines;
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
        return IntStream.range(0, this.size * this.size)
            .mapToObj(i -> new Pair<Integer, Integer>(i / this.size, i % this.size))
            .filter(pos -> !this.mines.stream().anyMatch(mine -> mine.getPosition().equals(pos)))
            .map(pos -> new EmptyCell(pos))
            .toList();
    }

    private Pair<Integer, Integer> generateRandomPosition() {
        return new Pair<>(this.random.nextInt(this.size), this.random.nextInt(this.size));
    }

    @Override
    public List<Cell> getCellNeighborhood(final Cell cell) {
        final List<Cell> neighbors = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                final Pair<Integer, Integer> position = new Pair<>(
                        cell.getPosition().getX() + x,
                        cell.getPosition().getY() + y
                );

                this.emptyCells.stream()
                    .filter(c -> c.getPosition().equals(position))
                    .findFirst().ifPresent(c -> neighbors.add(c));
            }
        }
        return neighbors;
    }
}