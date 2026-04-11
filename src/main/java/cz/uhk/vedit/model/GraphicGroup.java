package cz.uhk.vedit.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicGroup extends AbstractGraphicObject {
    protected List<AbstractGraphicObject> items = new ArrayList<>();
    private Point point;

    public void addGraphObject(AbstractGraphicObject o){
        items.add(o);
    }

    public List<AbstractGraphicObject> getItems() {
        return items;
    }

    public List<Point> getAllItemCrosshairs() {
        List<Point> points = new ArrayList<>();
        for (AbstractGraphicObject obj : items) {
            if(obj instanceof GraphicGroup group){
                points.addAll(group.getAllItemCrosshairs());
            }
            points.add(obj.getCrosshairPosition());

        }
        return points;
    }

    @Override
    public void draw(Graphics2D g) {
        items.forEach(it -> it.draw(g));
    }

    @Override
    public boolean contains(Point p) {
        for (var it : items){
            if (it.contains(p)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveBy(int dx, int dy) {
        items.forEach(it -> it.moveBy(dx, dy));
    }

    @Override
    public Point getCrosshairPosition() {
        return null;
    }
}
