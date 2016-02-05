package cs355.model.drawable;

import java.awt.*;

/**
 * Created by Joshua on 1/18/2016.
 */
public interface DrawableShape {
    void onDraw(Graphics2D g2d);
    void drawSelection(Graphics2D g2d);
}
