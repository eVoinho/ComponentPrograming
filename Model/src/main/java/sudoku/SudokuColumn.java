package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuPiece {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getPieceFiledList());
        return new SudokuColumn(fields);
    }
}
