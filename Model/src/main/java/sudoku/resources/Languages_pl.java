package sudoku.resources;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class Languages_pl extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"wrongFieldValue", "Wartosc musi byc <1,9>"},
                {"wrongLength", "Dlugość musi byc rowna 9"}
        };
    }
}
