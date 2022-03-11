package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sudoku.exception.DbException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

public static String dbName;
private String boardName;
private String tableName;

    public JdbcSudokuBoardDao(String filename, String dbName, String tableName) {
        this.boardName = filename;
        this.dbName = dbName;
        this.tableName = tableName;
    }

    private Connection prepConnection(String jdbcUrl) throws DbException {
       Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl, "root", "Yamaha2012");
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return connection;
    }

    @Override
    public SudokuBoard read() throws DbException {
        SudokuBoard board = new SudokuBoard();
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC";
        Connection connection = prepConnection(jdbcUrl);
        String receivedData;
        ResultSet resultSet;
        String select = "select boardName, fields from " + tableName + " where boardName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1, boardName);
            resultSet = preparedStatement.executeQuery();
           if (resultSet.next()) {
               receivedData = resultSet.getString(2);
           } else {
               return null;
           }
            board.convertStringToSudokuBoard(receivedData);
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws DbException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC";
        Connection connection = prepConnection(jdbcUrl);

        String insertData = "INSERT INTO " + tableName + "(boardName, fields) values (?,?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setString(1, boardName);
            preparedStatement.setString(2,obj.convertSudokuBoardToString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }


    @Override
    public void close() throws Exception {
    }
}
