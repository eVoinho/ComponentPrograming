package sudoku;

import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LanguagesTest {
    @Test
    public void languagesEnTest () {
        ResourceBundle bundle = ResourceBundle.getBundle("sudoku.resources.Languages_en");
        assertEquals("Must be <1,9>", bundle.getString("wrongFieldValue"));
        assertEquals("Length must be equal 9", bundle.getString("wrongLength"));
    }
    @Test
    public void languagesPlTest () {
        ResourceBundle bundle = ResourceBundle.getBundle("sudoku.resources.Languages_pl");
        assertEquals("Wartosc musi byc <1,9>", bundle.getString("wrongFieldValue"));
        assertEquals("Dlugość musi byc rowna 9", bundle.getString("wrongLength"));
    }
}
