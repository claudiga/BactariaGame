package com.claude.capsule;

import com.claude.capsule.Cell;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CellTest {

    @Test
    public void testEqualsTrueWhenColumnAndRowSame() {
        Cell cell = new Cell(1, 1);
        Cell cell2 = new Cell(1, 1);
        assertEquals(cell2, cell);
    }

    @Test
    public void testEqualFalseWhenColumnDifferent() {
        Cell cell = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);
        assertNotEquals(cell2, cell);
    }

    @Test
    public void testEqualFalseWhenRowDifferent() {
        Cell cell = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        assertNotEquals(cell2, cell);
    }

    @Test
    public void testHashCodeWhenValuesAreSame() {
        Cell cell1 = new Cell(2, 3);
        Cell cell2 = new Cell(2, 3);

        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    @Test
    public void testHashCodeWhenValuesAreDifferent() {
        Cell cell1 = new Cell(1, 3);
        Cell cell2 = new Cell(2, 3);

        assertNotEquals(cell1.hashCode(), cell2.hashCode());
    }

    @Test
    public void testHashCodesWhenUsingSet() {
        Set<Cell> cellSet = new HashSet<>();
        cellSet.add(new Cell(2, 3));
        cellSet.add(new Cell(3, 4));
        cellSet.add(new Cell(3, 4));

        assertEquals(2, cellSet.stream().map(Cell::hashCode).distinct().count());

    }
}