package net.ucoz.talg.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class BigButton extends JButton {
    public BigButton(String text) {
        super(text);
        Color greenColor = new Color(102, 255, 102);
        this.setBorder(new LineBorder(greenColor, 10));
        this.setBackground(greenColor);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
    }
}