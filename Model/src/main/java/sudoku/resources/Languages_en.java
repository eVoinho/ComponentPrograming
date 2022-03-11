package sudoku.resources;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class Languages_en extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"wrongFieldValue", "Must be <1,9>"},
                {"wrongLength", "Length must be equal 9"}
        };
    }
}