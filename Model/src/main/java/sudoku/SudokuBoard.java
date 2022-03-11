package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {

    private final List<List<SudokuField>> board;

    public SudokuBoard() {
        // Tworzenie pierwszego wymiaru sudoku
        board = Arrays.asList(new List[9]);

        // Tworzenie drugiego wymiaru sudoku
        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }

        // Wstawianie SudokuField w pola listy
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField());
            }
        }
    }


    public int getValue(int row, int column) {
        return board.get(row).get(column).getFieldValue();
    }

    public void setValue(int row, int column, int value) {
        if (value >= 0 && value <= 9) {
            board.get(row).get(column).setFieldValue(value);
        }
    }

    private boolean checkRow(int row, int col) {
       int number = getValue(row, col);

       //Checking if placement in row is possible
       for (int i = 0; i < 9; i++) {
           if (i != col && getValue(row, i) == number) {
               return false;
           }
       }

       return true;
    }

    private boolean checkColumn(int row, int col) {
       int number = getValue(row, col);

       //Checking if placement in column is possible
       for (int i = 0; i < 9; i++) {
           if (i != row && getValue(i, col) == number) {
               return false;
           }
       }

       return true;
    }

    private boolean checkSquareBox(int row, int col) {
       int number = getValue(row, col);
       int littleBoxRow = row - row % 3;
       int littleBoxColumn = col - col % 3;

       //Checking if placement in 3x3 box is possible
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               if (i + littleBoxRow != row && j + littleBoxColumn != col
                       && getValue(i + littleBoxRow, j + littleBoxColumn) == number) {
                   return false;
               }
           }
       }

       return true;
    }

    public boolean isInsertionPossible(int row, int col) {
       return checkRow(row, col) && checkColumn(row, col) && checkSquareBox(row, col);
    }

    public void solveGame() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(this);
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            fields.set(i, board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            fields.set(i, board.get(i).get(column));
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int row, int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;

        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.set(index++, board.get(boxRow + i).get(boxColumn + j));
            }
        }

        return new SudokuBox(fields);
    }

    public SudokuBoard convertStringToSudokuBoard(String string) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setValue(i, j, Character.getNumericValue(string.charAt(i * 9 + j)));
            }
        }
        return this;
    }

    public String convertSudokuBoardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(String.valueOf(getValue(i, j)));
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("SudokuBoard", board).toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            return new EqualsBuilder().append(board, ((SudokuBoard) obj).board).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.setValue(i, j, getValue(i, j));
            }
        }
        return sudokuBoard;
    }

}



