package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.*;
import cz.uhk.vedit.model.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VeditFrame extends JFrame {

    private final DrawPanel drawPanel = new DrawPanel();

    public VeditFrame() {
        super("FIM vector editor"); //setTitle()
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(drawPanel, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.NORTH);

        initSampleData();

        pack();
    }

    private void initSampleData() {
        drawPanel.addObject(new Square(200,200, Color.GREEN, 200, true));
        drawPanel.addObject(new Square(300,50, Color.RED, 30));
        drawPanel.addObject(new Square(150,220, Color.BLACK, 10));
        drawPanel.addObject(new Circle(200, 200, Color.BLUE, 20, true));
        drawPanel.addObject(new Triangle(122, 122, Color.BLACK, 100));
        drawPanel.addObject(new Triangle(230, 122, Color.PINK, 120, true));
        drawPanel.addObject(new Circle(122, 122, Color.PINK, 20, true));
        drawPanel.addObject(new Circle(122));
        drawPanel.addObject(new Rectangle(200, 300,  Color.BLUE, 40, 100, true));
        GraphicGroup gr = new GraphicGroup();
        drawPanel.addObject(gr);
        gr.addGraphObject(new Square(500, 300, Color.PINK, 100));
        gr.addGraphObject(new Square(100, 200, Color.PINK, 100));
    }

    /*private JMenuBar createMenuBar(){
        JMenuBar mb = new JMenuBar();
        var objects = new JMenu("Objects");

        return mb;
    }*/

    private JToolBar createToolbar() {
        var tb = new JToolBar(JToolBar.HORIZONTAL);
        //adding basic button
        /*var btSquare = new JButton("Square");
        tb.add(btSquare);
        btSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Square(10,10, Color.PINK, 50, true));
            }
        });*/

        var actSquare = new AbstractAction("Square"){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Square(50));
            }
        };
        actSquare.putValue(AbstractAction.SHORT_DESCRIPTION, "paint square");
        tb.add(actSquare);

        var actRectangle = new AbstractAction("Rectangle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Rectangle(50,100));
            }
        };
        actRectangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint rectangle");
        tb.add(actRectangle);

        var actCircle = new AbstractAction("Circle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Circle(50));
            }
        };
        actRectangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint rectangle");
        tb.add(actCircle);

        var actTriangle = new AbstractAction("Triangle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Triangle(50));
            }
        };
        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint triangle");
        tb.add(actTriangle);

        return tb;
    }
}
