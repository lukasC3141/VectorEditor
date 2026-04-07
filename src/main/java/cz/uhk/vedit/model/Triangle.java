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

    private Polygon getTriangle() {
        int h = (int)(this.a * Math.sin(Math.PI / 3));
        Polygon p = new Polygon();
        p.addPoint(point.x - a/2, point.y + h/3);
        p.addPoint(point.x, point.y - 2*h/3);
        p.addPoint(point.x + a/2, point.y + h/3);
        return p;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        Polygon triangle = getTriangle();
        if (fill){
            g.fill(triangle);
        }
        else g.draw(triangle);
    }

    /**
     * helper method for deducing the side of a halfplane
     */
    private double sign(Point p1, Point p2, Point p3) {
        //cross product, the subtraction is for creating the vectors for the cross product
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    @Override
    public boolean contains(Point p) {
        int h = (int)(this.a * Math.sin(Math.PI / 3)); // height

        Point v1 = new Point(point.x - a/2, point.y + h/3);   // left
        Point v2 = new Point(point.x, point.y - 2*h/3);       // up
        Point v3 = new Point(point.x + a/2, point.y + h/3);   // down

        // position of point to edge of triangle
        double d1 = sign(p, v1, v2);
        double d2 = sign(p, v2, v3);
        double d3 = sign(p, v3, v1);

        // all the values are more or equal to 0
        return (d1 >= 0) && (d2 >= 0) && (d3 >= 0);
    }
}
