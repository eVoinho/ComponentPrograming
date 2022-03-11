package sudoku;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sudoku.exception.DaoException;
import sudoku.exception.DbException;
import sudoku.exception.FileOperationException;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JdbcSudokuBoardDaoTest {



    @Test
    public void writeReadDbTest() throws DaoException {
        SudokuBoard board1 = new SudokuBoard();
        board1.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String name = dtf.format(now).toString();
        factory.getDatabaseDao(name, "sudokuproject", "testtable").write(board1);
        SudokuBoard board2 = factory.getDatabaseDao(name, "sudokuproject", "testtable").read();
        assertEquals(board1, board2);
    }


    @Test
    public void prepConnectionExceptionTest () throws DbException {
        SudokuBoard board1 = new SudokuBoard();
        board1.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String name = dtf.format(now).toString();
        assertThrows(DbException.class, ()-> {
           factory.getDatabaseDao(name, "lol", "testtable").write(board1);
        });
    }

    @Test
    public void readResultIsNullTest () throws DaoException {
        SudokuBoard board1 = new SudokuBoard();
        board1.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Assertions.assertNull(factory.getDatabaseDao("makapaka", "sudokuproject", "testtable").read());
    }




}

