package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class SudokuColumnTest {

    private SudokuColumn column;
    private SudokuColumn columnSecond;

    private SudokuColumn prep() {
        return new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }
    @Test
    public void CloneTest() throws CloneNotSupportedException {
        column = prep();
        columnSecond = (SudokuColumn) column.clone();

        Assertions.assertTrue(column.equals(columnSecond)
                && columnSecond.equals(column));
    }

}
