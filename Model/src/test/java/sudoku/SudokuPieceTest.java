package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.exception.PieceSizeException;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuPieceTest {
    SudokuPiece piece;
    SudokuPiece piece2;
    SudokuField[] fields;
    SudokuField[] fields2;

    @BeforeEach
    void setUp() {
        fields = new SudokuField[9];
        fields2 = new SudokuField[9];
    }

    @Test
    public void badPieceSizeTest() {
       assertThrows(PieceSizeException.class, ()-> {SudokuBox sudokuBox = new SudokuBox(Arrays.asList(
                new SudokuField(1),
                new SudokuField(9)));});
    }

    @Test
    public void validFieldsVerifyMethodTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i + 1);
        }

        piece = new SudokuPiece(Arrays.asList(fields));

        assertTrue(piece.verify());
    }

    @Test
    public void invalidFieldsVerifyMethodTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(1);
        }

        piece = new SudokuPiece(Arrays.asList(fields));

        assertFalse(piece.verify());
    }

    @Test
    public void toStringTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i + 1);
        }

        piece = new SudokuPiece(Arrays.asList(fields));

        assertNotNull(piece.toString());
    }

    @Test
    public void equalsTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields2[i] = new SudokuField();

            fields[i].setFieldValue(i + 1);
            fields2[i].setFieldValue(i + 1);
        }

        piece = new SudokuPiece(Arrays.asList(fields));
        piece2 = new SudokuPiece(Arrays.asList(fields2));

        assertTrue(piece.equals(piece2) && piece2.equals(piece));
    }
    @Test
    public void negativeEqualsTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i + 1);
        }
        piece = new SudokuPiece(Arrays.asList(fields));
        SudokuBoard board = new SudokuBoard();

        assertFalse(piece.equals(null));
        assertFalse(piece.equals(board));
    }

    @Test
    public void hashCodeTest() {
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields2[i] = new SudokuField();

            fields[i].setFieldValue(i + 1);
            fields2[i].setFieldValue(i + 1);
        }

        piece = new SudokuPiece(Arrays.asList(fields));
        piece2 = new SudokuPiece(Arrays.asList(fields));

        assertEquals(piece.hashCode(), piece2.hashCode());
    }


}