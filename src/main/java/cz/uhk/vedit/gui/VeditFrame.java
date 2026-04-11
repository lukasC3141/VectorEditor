package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.*;
import cz.uhk.vedit.model.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class VeditFrame extends JFrame {


    private final DrawPanel drawPanel = new DrawPanel();
    private final int WIDTH = drawPanel.getWidth();
    private final int HEIGHT = drawPanel.getHeight();

    public VeditFrame() {
        super("FIM vector editor");//setTitle()
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(drawPanel, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.NORTH);

        initSampleData();

        pack();
    }

    private void initSampleData() {
        /*GraphicGroup gr = new GraphicGroup();
        drawPanel.addObject(gr);
        gr.addGraphObject(new Square(500, 300, Color.PINK, 100));
        gr.addGraphObject(new Square(100, 200, Color.PINK, 100));*/
    }

    private JToolBar createToolbar() {
        var tb = new JToolBar(JToolBar.HORIZONTAL);

        //square
        var actSquare = new AbstractAction("Square"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = (int)(Math.random()*100);
                int randX = (int)(Math.random()*WIDTH - a);
                int x = Math.max(0, randX);
                int randY = (int)(Math.random()*HEIGHT - a);
                int y = Math.max(0, randY);
                Color col = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));

                drawPanel.addObject(new Square(x, y, col, a));
            }
        };
        actSquare.putValue(AbstractAction.SHORT_DESCRIPTION, "paint square");
        tb.add(actSquare);

        //rectangle
        var actRectangle = new AbstractAction("Rectangle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = (int)(Math.random()*100);
                int b = (int)(Math.random()*200);
                int randX = (int)(Math.random()*WIDTH - a);
                int x = Math.max(0, randX);
                int randY = (int)(Math.random()*HEIGHT - b);
                int y = Math.max(0, randY);
                Color col = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));

                drawPanel.addObject(new Rectangle(x, y, col, a, b));
            }
        };
        actRectangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint rectangle");
        tb.add(actRectangle);

        //circle
        var actCircle = new AbstractAction("Circle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = (int)(Math.random()*100);
                int randX = (int)(Math.random()*WIDTH - r);
                int x = Math.max(r, randX);
                int randY = (int)(Math.random()*HEIGHT - r);
                int y = Math.max(r, randY);
                Color col = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));

                drawPanel.addObject(new Circle(x, y, col, r));
            }
        };
        actRectangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint rectangle");
        tb.add(actCircle);

        //triangle
        var actTriangle = new AbstractAction("Triangle"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = (int)(Math.random()*100);
                int h = (int)(a * Math.sin(Math.PI / 3));
                int randX = (int)(Math.random()*WIDTH - (a/2));
                int x = Math.max(a/2, randX);
                int randY = (int)(Math.random()*HEIGHT - (h/3));
                int y = Math.max((2*h/3), randY);
                Color col = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));

                drawPanel.addObject(new Triangle(x, y , col, a));
            }
        };
        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "paint triangle");
        tb.add(actTriangle);

        var actAddToGroup = new AbstractAction("Add to Group"){
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<AbstractGraphicObject> selected = drawPanel.getSelectedObjects();

                if (selected.isEmpty()) return;

                GraphicGroup group = new GraphicGroup();

                // Add all selected items to the group
                for (AbstractGraphicObject obj : selected) {
                    group.addGraphObject(obj);
                    // remove from the drawing list
                    drawPanel.getObjects().remove(obj);
                }

                // Clear the selection list
                selected.clear();

                drawPanel.addObject(group);
                drawPanel.repaint();
            }
        };
        actAddToGroup.putValue(AbstractAction.SHORT_DESCRIPTION, "Add group selected objects");
        tb.add(actAddToGroup);

        var actDegroup = new AbstractAction("Degroup"){
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<AbstractGraphicObject> selected = drawPanel.getSelectedObjects();
                java.util.List<AbstractGraphicObject> toRemoveFromPanel = new ArrayList<>();
                java.util.List<AbstractGraphicObject> toAddToPanel = new ArrayList<>();

                if (selected.isEmpty()) return;

                for (AbstractGraphicObject obj : selected){
                    if (obj instanceof GraphicGroup group){
                        //add single items from group to DrawPanel
                        toAddToPanel.addAll(group.getItems());
                        //group to delete
                        toRemoveFromPanel.add(group);
                    }
                }
                drawPanel.getObjects().addAll(toAddToPanel);
                drawPanel.getObjects().removeAll(toRemoveFromPanel);

                selected.removeAll(toRemoveFromPanel);
                selected.addAll(toAddToPanel);

                drawPanel.repaint();
            }
        };
        actDegroup.putValue(AbstractAction.SHORT_DESCRIPTION, "Remove objects from group and create single objects");
        tb.add(actDegroup);

        var actCLearAll = new AbstractAction("Clear all"){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.getSelectedObjects().clear();
                drawPanel.getObjects().clear();
                drawPanel.repaint();

            }
        };
        actCLearAll.putValue(AbstractAction.SHORT_DESCRIPTION, "clear all");

        //element which takes space and sends the clearAll button to the right side
        tb.add(Box.createHorizontalGlue());
        tb.add(actCLearAll);

        //canot be dragged out of the window
        tb.setFloatable(false);

        return tb;
    }
}
