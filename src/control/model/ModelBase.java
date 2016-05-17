package control.model;

import java.awt.*;

/**
 * Created by Oxyde on 12/05/2016.
 */
public class ModelBase implements ModelObj {
    @Override
    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public Point getPoint() {
        return point;
    }
    private Point point;
}
