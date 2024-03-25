package com.claude.capsule;


import com.claude.capsule.BacteriaGame;
import com.claude.capsule.Cell;
import com.claude.capsule.GridPrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BacteriaGameTest {

    @InjectMocks
    private BacteriaGame sut;

    @Mock
    private GridPrinter gridPrinter;

    @Captor
    private ArgumentCaptor<Set<Cell>> argumentCaptor;

    @Test
    public void testThatNextGenerationExists() {
        ByteArrayInputStream in = new ByteArrayInputStream("""
                1,2
                2,2
                3,2
                1000000001 ,1000000002
                1000000002 ,1000000002
                1000000003 ,1000000002
                end
                """
                .getBytes());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        sut.simulateBacteriaGame(in);
        Mockito.verify(gridPrinter).printOutput(argumentCaptor.capture());
        Set<Cell> cells = argumentCaptor.getValue();
        assertEquals(6, cells.size());
        assertTrue(cells.containsAll(
                Set.of(new Cell(2, 1),
                        new Cell(2, 2),
                        new Cell(2, 3),
                        new Cell(1000000002, 1000000001),
                        new Cell(1000000002, 1000000002),
                        new Cell(1000000002, 1000000003)
                )));
    }

    @Test
    public void testWhenNoNextGeneration() {
        ByteArrayInputStream in = new ByteArrayInputStream("""
                2,2
                """
                .getBytes());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        sut.simulateBacteriaGame(in);
        Mockito.verify(gridPrinter).printOutput(argumentCaptor.capture());
        Set<Cell> cells = argumentCaptor.getValue();
        assertEquals(0, cells.size());
    }

}