package sudoku;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {
    @Override
    public void solve(SudokuBoard board) {
        if (board.getValue(0, 0) != 0) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board.setValue(i, j, 0);
                }
            }
        }

        Random rand = new Random();

        int[] numbers = new int[81];

        for (int i = 0; i < 81; i++) {
            int row = i / 9;
            int col = i % 9;
            boolean flag = false;
            int newValue;

            if (numbers[i] == 0) {
                numbers[i] = rand.nextInt(9) + 1;
                board.setValue(row, col, numbers[i]);

                do {
                    if (board.isInsertionPossible(row, col)) {
                        flag = true;
                        break;
                    }

                    newValue = board.getValue(row, col) % 9 + 1;
                    board.setValue(row, col, newValue);
                } while (board.getValue(row, col) != numbers[i]);
            } else {
                newValue = board.getValue(row, col) % 9 + 1;
                board.setValue(row, col, newValue);

                while (board.getValue(row, col) != numbers[i]) {
                    if (board.isInsertionPossible(row, col)) {
                        flag = true;
                        break;
                    }

                    newValue = board.getValue(row, col) % 9 + 1;
                    board.setValue(row, col, newValue);
                }
            }

            if (!flag) {
                numbers[i] = 0;
                board.setValue(row, col, 0);
                i = i - 2;
            }
        }
    }
}
