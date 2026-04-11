package cz.uhk.vedit.model;

import java.awt.*;

public class Square extends AbstractGraphicObject{

    protected int a;

    public Square(int a) {
        this.a = a;
    }

    public Square(Point point, Color color, int a, boolean fill) {
        super(point, color, fill);
        this.a = a;
    }

    public Square(Point point, Color color, int a) {
        super(point, color);
        this.a = a;
    }

    public Square(int x, int y, Color color, int a, boolean fill) {
        super(x, y, color, fill);
        this.a = a;
    }

    public Square(int x, int y, Color color, int a) {
        super(x, y, color);
        this.a = a;
    }

    public Point getCrosshairPosition() {
        return new Point(this.point.x + this.a/2, this.point.y + this.a/2);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if (fill) {
            g.fillRect(point.x, point.y, a, a);
        }
        else g.drawRect(point.x, point.y, a, a);
    }

    @Override
    public boolean contains(Point p) {
        return a + point.x >= p.x && p.x >= point.x
                && a + point.y >= p.y && p.y >= point.y;
    }
}
