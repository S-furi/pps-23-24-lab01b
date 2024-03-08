package e2;

import java.util.List;
import java.util.Optional;

import e2.cell.Cell;

public class LogicsImpl implements Logics {
    final int size;
    final int numberOfMines;
    final Grid grid;

    public LogicsImpl(final int size, final int numberOfMines) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.grid = new GridImpl(size, numberOfMines);
    }
    
    @Override
    public boolean hasMine(final Pair<Integer, Integer> position) {
        return this.getMinesPositions().stream().anyMatch(pos -> pos.equals(position));
    }

    @Override
    public List<Pair<Integer, Integer>> getMinesPositions() {
        return this.grid.getMines().stream().map(mine -> mine.getPosition()).toList();
    }

    @Override
    public int getNumberOfAdjacentMines(final Pair<Integer, Integer> position) {
        return this.grid.getNumberOfAdjacentMines(position);
    }
    
    @Override
    public Optional<? extends Cell> getCellAtPosition(final Pair<Integer, Integer> position) {
        return this.grid.getCellAtPosition(position);
    }

    @Override
    public void click(final Pair<Integer, Integer> position) {
        final Cell cell = this.getCellAtPosition(position).orElseThrow();

        if (hasMine(position) || cell.isDisabled() || cell.isClicked()) {
            return;
        }

        cell.click();

        if (this.getNumberOfAdjacentMines(cell.getPosition()) == 0) {
            final List<Cell> neighbors = this.grid.getCellNeighborhood(cell);
            neighbors.stream().map(Cell::getPosition).forEach(this::click);
        }
    }
}