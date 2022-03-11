package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.exception.DaoException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SudokuBoardDaoFactoryTest {
    private final SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void GetFileDaoTest() throws DaoException {assertNotNull(factory.getFileDao("BaoBao"));}

}
