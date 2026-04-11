package cz.uhk.vedit.model;

import java.awt.*;

public class Rectangle extends AbstractGraphicObject{

    protected int a;
    protected int b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Rectangle(Point point, Color color, int a, int b, boolean fill) {
        super(point, color, fill);
        this.a = a;
        this.b = b;
    }

    public Rectangle(Point point, Color color, int a, int b) {
        super(point, color);
        this.a = a;
        this.b = b;
    }

    public Rectangle(int x, int y, Color color, int a, int b) {
        super(x, y, color);
        this.a = a;
        this.b = b;
    }

    public Rectangle(int x, int y, Color color, int a, int b, boolean fill) {
        super(x, y, color, fill);
        this.a = a;
        this.b = b;
    }

    public Point getCrosshairPosition() {
        return new Point(this.point.x + this.a/2, this.point.y + this.b/2);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if (fill){
            g.fillRect(point.x, point.y, a, b);
        }
        else g.drawRect(point.x, point.y, a, b);
    }

    @Override
    public boolean contains(Point p) {
        return a + point.x >= p.x && p.x >= point.x
                && b + point.y >= p.y && p.y >= point.y;
    }
}
