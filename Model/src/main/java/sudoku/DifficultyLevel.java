package sudoku;

public class DifficultyLevel {
    public static void makeEasy(final SudokuBoard board) {
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);

            if (board.getValue(x, y) != 0) {
                board.setValue(x, y, 0);
            } else {
                i--;
            }
        }
    }

    public static void makeMedium(final SudokuBoard board) {
        for (int i = 0; i < 40; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);

            if (board.getValue(x, y) != 0) {
                board.setValue(x, y, 0);
            } else {
                i--;
            }
        }
    }

    public static void makeHard(final SudokuBoard board) {
        for (int i = 0; i < 60; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);

            if (board.getValue(x, y) != 0) {
                board.setValue(x, y, 0);
            } else {
                i--;
            }
        }
    }
}
