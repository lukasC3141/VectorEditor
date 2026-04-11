package cz.uhk.vedit.model;

import java.awt.*;

/**
 * predek vsech grafickych utvaru v editoru
 */

public abstract class AbstractGraphicObject {
    /**
     * reference point, from where the object will be drawn
     */
    protected Point point = new Point(50, 50);
    /**
     * color of object
     */
    protected Color color = Color.BLACK;
    /**
     * default boolean for fill or line drawn object
     */
    protected boolean fill = true;

    public AbstractGraphicObject() {}

    public AbstractGraphicObject(Point point, Color color, boolean fill) {
        this.point = point;
        this.color = color;
        this.fill = fill;
    }

    public AbstractGraphicObject(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    public AbstractGraphicObject(int x, int y, Color color, boolean fill) {
        setPoint(x, y);
        this.color = color;
        this.fill = fill;
    }

    public AbstractGraphicObject(int x, int y, Color color) {
        setPoint(x, y);
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoint(int x, int y){
        this.point = new Point(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public abstract void draw(Graphics2D g); //nebo jen Graphics g

    public abstract boolean contains(Point p);

    public boolean contains(int x, int y){
        return contains(new Point(x,y));
    }

    public void moveBy(int dx, int dy){
        point.translate(dx, dy);

    }

    //crosshair for selected objects
    public abstract Point getCrosshairPosition();
}
