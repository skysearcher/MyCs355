package cs355.model.drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Joshua on 1/18/2016.
 */
public interface DrawableShape {
    void onDraw(Graphics2D g2d, AffineTransform zoom);
    void drawSelection(Graphics2D g2d);
}
