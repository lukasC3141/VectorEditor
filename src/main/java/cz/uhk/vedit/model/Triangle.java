package cz.uhk.vedit.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject{
    protected int a;

    public Triangle(int a) {
        this.a = a;
    }

    public Triangle(Point point, Color color, int a) {
        super(point, color);
        this.a = a;
    }

    public Triangle(int x, int y, Color color, int a) {
        super(x, y, color);
        this.a = a;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawLine(point.x, point.y, point.x + a, point.y);

    }

    @Override
    public boolean contains(Point p) {
        //TODO implementovat zjistení bodu uvnitr trojuhelniku
        return false;
    }
}
