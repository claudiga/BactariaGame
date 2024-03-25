package com.claude.capsule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grid {
    private final Set<Cell> liveCells;

    public Grid(Set<Cell> liveCells) {
        this.liveCells = liveCells;
    }

    public Set<Cell> simulateNextGeneration() {
        Set<Cell> nextGen = new HashSet<>();
        Map<Cell, Integer> neighborCount = new HashMap<>();

        for (Cell cell : liveCells) {
            countLiveNeighbours(cell, neighborCount);
        }

        for (Map.Entry<Cell, Integer> entry : neighborCount.entrySet()) {
            Cell cell = entry.getKey();
            int count = entry.getValue();
            // If the cell has neighbour count of 3 then it becomes alive
            // otherwise if the count is 2, and it's already a live cell then promote to next gen.
            if (count == 3 || (count == 2 && liveCells.contains(cell))) {
                nextGen.add(cell);
            }
        }

        return nextGen;
    }

    /**
     * Count the live neighbours by going to the column and row before and after the current cell.
     *
     * @param cell          The cell to count the neighbours of.
     * @param neighborCount The map that holds the neighbour count.
     */
    private void countLiveNeighbours(Cell cell, Map<Cell, Integer> neighborCount) {
        for (int i = cell.getColumn() - 1; i <= cell.getColumn() + 1; i++) {
            for (int j = cell.getRow() - 1; j <= cell.getRow() + 1; j++) {
                Cell neighbor = new Cell(i, j);
                if (!neighbor.equals(cell)) {
                    neighborCount.put(neighbor, neighborCount.getOrDefault(neighbor, 0) + 1);
                }
            }
        }
    }
}
