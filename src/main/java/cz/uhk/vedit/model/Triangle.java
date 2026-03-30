package cz.uhk.vedit.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject{
    /**
     * length of the edge of a triangle
     */
    protected int a;

    public Triangle(int a) {
        this.a = a;
    }

    public Triangle(Point point, Color color, int a, boolean fill) {
        super(point, color, fill);
        this.a = a;
    }

    public Triangle(Point point, Color color, int a) {
        super(point, color);
        this.a = a;
    }

    public Triangle(int x, int y, Color color, int a, boolean fill) {
        super(x, y, color, fill);
        this.a = a;
    }

    public Triangle(int x, int y, Color color, int a) {
        super(x, y, color);
        this.a = a;
    }

    @Override
    public void draw(Graphics2D g) {
        //TODO add the fill method for triangle
        g.setColor(color);
        int h = (int)(this.a * Math.sin(Math.PI / 3));

        //point x, y is a center of gravity of Triangle
        g.drawLine(point.x - a/2, point.y  + h/3, point.x, point.y - 2*h/3);
        g.drawLine(point.x, point.y - 2*h/3, point.x + a/2, point.y + h/3);
        g.drawLine(point.x + a/2, point.y + h/3,point.x - a/2, point.y +h/3);

        /*g.drawLine(point.x, point.y, point.x + a, point.y);
        int y2 = (int)*/
    }

    @Override
    public boolean contains(Point p) {
        //aproximation
        //TODO calculate the point inside triangle
        Circle inside = new Circle(point.x, point.y, Color.WHITE, 2*this.a/3);
        return inside.contains(p);
        /*double vc
        double dy = point.y - p.y;
        double dx = dy / Math.tan(Math.PI/3);*/
    }
}
