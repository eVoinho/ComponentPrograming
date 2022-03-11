package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class SudokuBoxTest {

    private SudokuBox box;
    private SudokuBox boxSecond;

    private SudokuBox prep() {
        return new SudokuBox(Arrays.asList(
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
        box = prep();
        boxSecond = (SudokuBox) box.clone();

        Assertions.assertTrue(box.equals(boxSecond)
                && boxSecond.equals(box));
    }

}

