package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sudoku.exception.FileOperationException;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;

    public FileSudokuBoardDao(String name) {
        this.filename = name + ".txt";
    }

    @Override
    public SudokuBoard read() throws FileOperationException {
        SudokuBoard board;
        try (FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            board = (SudokuBoard) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new FileOperationException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws FileOperationException {

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(obj);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }

    @Override
    public void close() throws FileOperationException {

    }
}
