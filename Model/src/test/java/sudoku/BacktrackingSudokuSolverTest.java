package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {

    public BacktrackingSudokuSolverTest() {
    }
    private boolean checkRows(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++ ) {
                for (int k = j + 1; k < 9; k++) {
                    if (board.getValue(i,j) == board.getValue(i,k)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkColumns(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++ ) {
                for (int k = j + 1; k < 9; k++) {
                    if (board.getValue(j,i) == board.getValue(k,i)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkSquareBox(SudokuBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++ ) {
                for (int k = 0; k < 9; k++) {
                   for (int l = k + 1; l < 9; l++) {
                       if (board.getValue(i*3+k/3,j*3+k%3) == board.getValue(i*3+l/3,j*3+l%3)) {
                           return false;
                       }
                   }
                }
            }
        }

        return true;
    }


    @Test
    public void fillBoardTest() {
        SudokuBoard sudokuBoard=new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

        solver.solve(sudokuBoard);

        assertTrue(checkRows(sudokuBoard));
        assertTrue(checkColumns(sudokuBoard));
        assertTrue(checkSquareBox(sudokuBoard));
    }

    @Test
    public void fillBoardTwoDifferentBoards() {
        SudokuBoard sudokuBoard=new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

        int[][] pomArr = new int[9][9];
        solver.solve(sudokuBoard);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                pomArr[i][j] = sudokuBoard.getValue(i,j);
            }
        }

        solver.solve(sudokuBoard);
        boolean areTheyTheSame = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getValue(i,j) != pomArr[i][j]) {
                    areTheyTheSame = false;
                }
            }
        }

        assertFalse(areTheyTheSame);
    }
}
