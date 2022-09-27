package nz.ac.vuw.ecs.swen225.gp22.util.swing;

import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import javax.swing.text.AttributeSet;

public class NumberField extends JTextField {

    public NumberField(int value, int columns) {
        super(Integer.toString(value), columns);
    }

    public int getValue() {
        return Integer.parseInt(getText());
    }

    public void setValue(int value) {
        setText(Integer.toString(value));
    }

    @Override
    protected Document createDefaultModel() {
        return new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                if (str == null) {
                    return;
                }
                char[] source = str.toCharArray();
                char[] result = new char[source.length];
                int j = 0;
                // walk over each character and only add it if it is a digit
                for (int i = 0; i < result.length; i++) {
                    if (Character.isDigit(source[i])) {
                        result[j++] = source[i];
                    }
                }
                super.insertString(offs, new String(result, 0, j), a);
            }
        };
    }
}
