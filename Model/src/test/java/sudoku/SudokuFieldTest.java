package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.exception.BadFieldValueException;
import sudoku.exception.FileOperationException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    SudokuField field;
    SudokuField field2;

    @BeforeEach
    void setUp() {
        field = new SudokuField();
        field2 = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(field.getFieldValue(), 0);
    }

    @Test
    public void setFieldValueWithCorrectValuesTest() {
        for (int i = 0; i <= 9; i++) {
            field.setFieldValue(i);
            assertEquals(field.getFieldValue(), i);
        }
    }

    @Test
    public void setFieldValueWithTooSmallValuesTest() {
       assertThrows(BadFieldValueException.class, ()-> field.setFieldValue(-1));
    }
    @Test
    public void setFieldValueWithTooBigValuesTest() {
       assertThrows(BadFieldValueException.class, ()-> field.setFieldValue(10));
    }

    @Test
    public void toStringTest() {
        assertNotNull(field.toString());
    }

    @Test
    public void equalsTest() {
        assertTrue(field.equals(field2) && field2.equals(field));
    }
    @Test
    public void negativeEqualsTest() {
        SudokuBoard board = new SudokuBoard();
        assertFalse(field.equals(null));
        assertFalse(field.equals(board));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(field.hashCode(), field2.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        field = new SudokuField(8);
        field2 = field.clone();

        Assertions.assertTrue(field.equals(field2)
                && field2.equals(field));
    }
    @Test
    public void compareToTest() {
        field2 = new SudokuField();

        field.setFieldValue(4);
        field2.setFieldValue(4);
        assertEquals(field.compareTo(field2), 0);

        field.setFieldValue(8);
        field2.setFieldValue(4);
        assertEquals(field.compareTo(field2), 1);

        field.setFieldValue(4);
        field2.setFieldValue(8);
        assertEquals(field.compareTo(field2), -1);

    }

    @Test
    public void compareToExceptionTest() throws NullPointerException {
        assertThrows(NullPointerException.class, ()-> {
           field.compareTo(null);
        });
    }

}
