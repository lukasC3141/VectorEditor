package cz.uhk.vedit.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject{
    protected int r;

    public Circle(int r) {
        this.r = r;
    }

    public Circle(Point point, Color color, int r) {
        super(point, color);
        this.r = r;
    }

    public Circle(int x, int y, Color color, int r) {
        super(x, y, color);
        this.r = r;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawOval(point.x - r, point.y - r, 2*r, 2*r);
    }

    @Override
    public boolean contains(Point p) {
        //TODO implementovat zjistení bodu uvnitr kruhu
        return false;
    }
}
