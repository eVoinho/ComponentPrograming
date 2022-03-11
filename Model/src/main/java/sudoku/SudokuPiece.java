package sudoku;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;
import sudoku.exception.PieceSizeException;

public class SudokuPiece implements Serializable, Cloneable {
    private final List<SudokuField> fields;
    private ResourceBundle listBundle = ResourceBundle.getBundle("sudoku.resources.Languages");

    public SudokuPiece(List<SudokuField> fields) {
       if (fields.size() != 9) {
           throw new PieceSizeException(listBundle.getObject("wrongLength").toString());
       }
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    public List<SudokuField> getPieceFiledList() {
        return Collections.unmodifiableList(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("SudokuBoard", fields).toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            return new EqualsBuilder().append(fields, ((SudokuPiece) obj).fields).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }


}
