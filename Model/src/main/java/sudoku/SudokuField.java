package sudoku;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;
import sudoku.exception.BadFieldValueException;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    private ResourceBundle listBundle = ResourceBundle.getBundle("sudoku.resources.Languages");

    public SudokuField() {
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int newValue) {
        if (newValue >= 0 && newValue <= 9) {
            this.value = newValue;
        } else {
            throw new BadFieldValueException(listBundle.getObject("wrongFieldValue").toString());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("SudokuField", value).toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            return new EqualsBuilder().append(value,
                    ((SudokuField) obj).getFieldValue()).isEquals();

        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(this.getFieldValue());
        return sudokuField;
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
