package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuPiece {
    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getPieceFiledList());
        return new SudokuBox(fields);
    }
}
