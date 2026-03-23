package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.Circle;
import cz.uhk.vedit.model.Square;
import cz.uhk.vedit.model.Triangle;

import javax.swing.*;
import java.awt.*;

public class VeditFrame extends JFrame {

    private final DrawPanel drawPanel = new DrawPanel();

    public VeditFrame() {
        super("FIM vector editor"); //setTitle()
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(drawPanel, BorderLayout.CENTER);

        initSampleData();

        pack();
    }

    private void initSampleData() {
        drawPanel.addObject(new Square(200,200, Color.GREEN, 200));
        drawPanel.addObject(new Square(300,50, Color.RED, 30));
        drawPanel.addObject(new Square(150,220, Color.BLACK, 10));
        drawPanel.addObject(new Circle(200, 200, Color.BLUE, 20));
        drawPanel.addObject(new Triangle(122, 122, Color.BLACK, 100));
        drawPanel.addObject(new Circle(122, 122, Color.PINK, 20));
    }
}
