package sudoku;

import sudoku.exception.DaoException;
import sudoku.exception.DbException;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String filename) throws DaoException {
        return new FileSudokuBoardDao(filename);
    }

    public Dao<SudokuBoard> getDatabaseDao(String filename, String dbName,
                                           String tabelName) throws DbException {
        return new JdbcSudokuBoardDao(filename, dbName, tabelName);
    }
}
