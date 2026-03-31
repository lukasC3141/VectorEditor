package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private List<AbstractGraphicObject> objects = new ArrayList<>();
    private AbstractGraphicObject selected;
    private int dx, dy; //soucet souradnice mysi od ref pointu
    private Point oldMouse;

    public DrawPanel(List<AbstractGraphicObject> objects) {
        this.objects = objects;
    }

    public DrawPanel() {
        initGui();
    }

    public void addObject(AbstractGraphicObject obj){
        objects.add(obj);
        repaint();
    }

    private void initGui() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                selected = findObjectUnderMouse(e.getPoint());
                if (selected != null) {
                    oldMouse = e.getPoint();
                    //dx = e.getX() - selected.getPoint().x;
                    //dy = e.getY() - selected.getPoint().y;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (selected != null) {
                    int dx = e.getX() - oldMouse.x;
                    int dy = e.getY() - oldMouse.y;
                    selected.moveBy(dx, dy);
                    oldMouse = e.getPoint();
                    //selected.setPoint(e.getX()-dx, e.getY()-dy);

                    repaint();
                }
            }
        });
    }

    private AbstractGraphicObject findObjectUnderMouse(Point point) {
        /*for (var o : objects) {
            if (o.contains(point)) {
                return o;
            }
        }
        return null;*/
        return objects.stream()
                .filter(o -> o.contains(point))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        objects.forEach(obj -> obj.draw((Graphics2D) g));
    }

}
