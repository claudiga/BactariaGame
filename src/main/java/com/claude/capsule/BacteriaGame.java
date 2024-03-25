package com.claude.capsule;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BacteriaGame {

    private static final String END_OF_INPUT = "end";
    private static final String COORDINATE_DELIMITER = ",";
    private final GridPrinter gridPrinter;

    public BacteriaGame(GridPrinter gridPrinter) {
        this.gridPrinter = gridPrinter;
    }

    public void simulateBacteriaGame(InputStream inputStream) {
        Set<Cell> liveCells;
        try (Scanner scanner = new Scanner(inputStream)) {
            liveCells = readInput(scanner);
        }

        Grid grid = new Grid(liveCells);
        Set<Cell> nextGeneration = grid.simulateNextGeneration();

        gridPrinter.printOutput(nextGeneration);
    }

    private Set<Cell> readInput(Scanner scanner) {
        Set<Cell> liveCells = new HashSet<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals(END_OF_INPUT)) {
                break;
            }
            String[] coordinates = line.split(COORDINATE_DELIMITER);
            int column = Integer.parseInt(coordinates[0].trim());
            int row = Integer.parseInt(coordinates[1].trim());
            liveCells.add(new Cell(column, row));
        }
        return liveCells;
    }
}

