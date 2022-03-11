package sudoku;


import org.junit.jupiter.api.Test;
import sudoku.exception.DaoException;
import sudoku.exception.FileOperationException;

import static org.junit.jupiter.api.Assertions.*;


public class FileSudokuBoardDaoTest {

    private final SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private final SudokuBoard board = new SudokuBoard();
    private Dao<SudokuBoard> sudokuDao;

    @Test
    public void writeReadTest() throws DaoException {
        sudokuDao = factory.getFileDao("filename");
        sudokuDao.write(board);
        SudokuBoard board2 = sudokuDao.read();
        assertEquals(board, board2);
    }

    @Test
    public void readExceptionTest() throws DaoException{
        assertThrows(FileOperationException.class, ()-> {
            sudokuDao = factory.getFileDao("OabOab");
            sudokuDao.read();
        });
    }

    @Test
    public void writeExceptionTest() throws DaoException {
       assertThrows(FileOperationException.class, ()-> {
           sudokuDao = factory.getFileDao("?");
           sudokuDao.write(board);
       });
    }

}
