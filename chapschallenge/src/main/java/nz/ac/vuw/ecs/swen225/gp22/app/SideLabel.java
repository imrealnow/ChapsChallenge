package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class SideLabel extends JLabel {
    private int PADDING = 50;

    SideLabel(String name) {
        super();
        Border border = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
        setBorder(border);
        setFont(new Font("Times New Roman", Font.BOLD, 14));
        setText(name);
    }
}
