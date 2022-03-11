package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    SudokuBoard board;
    SudokuBoard board2;

    @BeforeEach
    void setUp() {
        board = new SudokuBoard();
        board2 = new SudokuBoard();
    }

    @Test
    public void testConstructorAndGetBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(board.getValue(i,j), 0);
            }
        }
    }

    @Test
    public void testSetValueAccepted() {
        board.solveGame();
        board.setValue(0,0,0);

        assertEquals(board.getValue(0,0),0);
    }

    @Test
    public void testSetValueTooBig() {
        board.solveGame();
        board.setValue(0,0,99);

        assertNotEquals(board.getValue(0,0),99);
    }

    @Test
    public void testSetValueTooSmall() {
        board.solveGame();
        board.setValue(0,0,-3);

        assertNotEquals(board.getValue(0,0),-3);
    }

    @Test
    public void getRowTest() {
        SudokuRow row;
        board.solveGame();

        for (int i = 0; i < 9; i++) {
            row = board.getRow(i);
            assertTrue(row.verify());
        }
    }

    @Test
    public void getColumnTest() {
        SudokuColumn column;
        board.solveGame();

        for (int i = 0; i < 9; i++) {
            column = board.getColumn(i);
            assertTrue(column.verify());
        }
    }

    @Test
    public void getBoxTest() {
        SudokuBox box;
        board.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                box = board.getBox(i, j);
                assertTrue(box.verify());
            }
        }
    }

    @Test
    public void toStringTest() {
        assertNotNull(board.toString());
    }

    @Test
    public void equalsTest() {
        assertTrue(board.equals(board2) && board2.equals(board));
    }
    @Test
    public void negativeEqualsTest() {
        SudokuField field = new SudokuField();
        assertFalse(board.equals(null));
        assertFalse(board.equals(field));
    }


    @Test
    public void hashCodeTest() {
        assertEquals(board.hashCode(), board2.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        board2 = (SudokuBoard) board.clone();

        assertTrue(board.equals(board2)
                && board2.equals(board));
    }

}

