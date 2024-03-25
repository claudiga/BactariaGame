package com.claude.capsule;

import java.util.Set;

public class GridPrinter {

    public void printOutput(Set<Cell> cells) {
        cells.forEach(cell -> System.out.println(cell.getColumn() + "," + cell.getRow()));
        System.out.println("end");
    }
}
