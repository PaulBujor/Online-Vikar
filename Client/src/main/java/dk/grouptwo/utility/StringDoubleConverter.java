package dk.grouptwo.utility;

import javafx.util.StringConverter;

public class StringDoubleConverter extends StringConverter<Number> {
    private double emptyValue;

    public StringDoubleConverter(double emptyValue) {
        this.emptyValue = emptyValue;
    }

    @Override
    public String toString(Number n) {
        if (n == null || n.doubleValue() == emptyValue) {
            return "";
        }
        try {
            return n.toString().substring(0, 5);
        } catch (StringIndexOutOfBoundsException e) {
            return n.toString();
        }
    }

    @Override
    public Number fromString(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return emptyValue;
        }
    }
}
