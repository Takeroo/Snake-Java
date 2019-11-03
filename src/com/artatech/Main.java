package com.artatech;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final Integer SIZE = 600;

    public static void main(String[] args) {
        JFrame frame = new Board();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE, SIZE);
        frame.setVisible(true);
    }
}
