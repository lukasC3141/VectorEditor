package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;
import cz.uhk.vedit.model.GraphicGroup;

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
    private List<AbstractGraphicObject> selectedObjects = new ArrayList<>();
    private int dx, dy; //soucet souradnice mysi od ref pointu
    private Point oldMouse;
    private final int width = 800;
    private final int height = 600;

    public DrawPanel(List<AbstractGraphicObject> objects) {
        this.objects = objects;
    }

    public DrawPanel() {
        initGui();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public List<AbstractGraphicObject> getObjects() {
        return objects;
    }

    public void setObjects(List<AbstractGraphicObject> objects) {
        this.objects = objects;
    }

    public List<AbstractGraphicObject> getSelectedObjects() {
        return selectedObjects;
    }

    public void setSelectedObjects(List<AbstractGraphicObject> selectedObjects) {
        this.selectedObjects = selectedObjects;
    }

    public void addObject(AbstractGraphicObject obj){
        objects.add(obj);
        repaint();
    }

    private void initGui() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                selected = findObjectUnderMouse(e.getPoint());
                if (selected != null) {
                    if (!selectedObjects.contains(selected)) {
                        selectedObjects.add(selected);
                    } else selectedObjects.remove(selected);

                    oldMouse = e.getPoint();
                    repaint();
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
        return objects.stream()
                .filter(o -> o.contains(point))
                .findFirst()
                .orElse(null);
    }

    private void drawCross(Graphics2D g, Point p) {
        int size = 10;
        if (p != null) {
            g.drawLine(p.x - size, p.y, p.x + size, p.y);
            g.drawLine(p.x, p.y - size, p.x, p.y + size);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        objects.forEach(obj -> obj.draw((Graphics2D) g));

        g.setXORMode(Color.WHITE);
        for (AbstractGraphicObject sel : selectedObjects) {
            if (sel instanceof GraphicGroup group) {
                // Get the GraphicGroup to access the specific method
                List<Point> groupPoints = group.getAllItemCrosshairs();

                for (Point p : groupPoints) {
                    drawCross((Graphics2D) g, p);
                }
            } else {
                //single object
                drawCross((Graphics2D) g, sel.getCrosshairPosition());
            }
        }
    }
}

