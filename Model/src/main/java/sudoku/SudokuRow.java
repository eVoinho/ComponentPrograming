package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuPiece {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getPieceFiledList());
        return new SudokuRow(fields);
    }
}
