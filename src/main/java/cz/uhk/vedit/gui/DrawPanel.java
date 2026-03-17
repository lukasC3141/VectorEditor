package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private List<AbstractGraphicObject> objects = new ArrayList<>();

    public DrawPanel(List<AbstractGraphicObject> objects) {
        this.objects = objects;
    }

    public DrawPanel() {
        initGui();
    }

    public void addObject(AbstractGraphicObject obj){
        objects.add(obj);
    }

    private void initGui() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void paint(Graphics g) {


        for (var obj : objects) {
            obj.draw((Graphics2D) g);
        }
    }

}
