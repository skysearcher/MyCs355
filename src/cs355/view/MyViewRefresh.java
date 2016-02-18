package cs355.view;

import cs355.GUIFunctions;
import cs355.model.MyModel;
import cs355.model.drawable.DrawableShape;
import cs355.model.drawable.ShapeConverter;
import cs355.model.drawing.Shape;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Joshua on 1/18/2016.
 */
public class MyViewRefresh implements ViewRefresher{
    private int start;
    private MyModel myMod;
    private List<Shape> shapes;
    private List<DrawableShape> drawShapes;
    private int amDrawing;
    private DrawableShape shapeDrawing;
    private ShapeConverter shaper;
    private int selectedShape;
    private AffineTransform zoom;

    public AffineTransform getZoom() {
        return zoom;
    }

    public void setZoom(AffineTransform zoom) {
        this.zoom = zoom;
    }




    public MyViewRefresh(MyModel givenModel){
        myMod = givenModel;
        drawShapes = new ArrayList<>();
        shaper = new ShapeConverter();
        amDrawing = 0;
        start = 0;
        selectedShape = -1;
        zoom = new AffineTransform();
    }
    public void setSelected(int given){
        selectedShape = given;
    }
    public void unSelect(){
        selectedShape = -1;
    }
    public void setDrawing(Shape givenShape){
        shapeDrawing = shaper.convert(givenShape);
        amDrawing = 1;
    }
    public void endDrawing(){
        amDrawing = 0;
    }
    @Override
    public void refreshView(Graphics2D g2d) {
        start++;
        for(int i = 0; i < drawShapes.size(); i++){
            drawShapes.get(i).onDraw(g2d, zoom);
        }
        if(selectedShape > -1){
            drawShapes.get(selectedShape).drawSelection(g2d);
        }
        //GUIFunctions.printf("Refresh " + start,0);
        //Draw list of Drawables
        if(amDrawing == 1){   //are you drawing?
            shapeDrawing.onDraw(g2d, zoom);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        drawShapes.clear();
        shapes = myMod.getShapes();
        for(Shape s: shapes){
            drawShapes.add(shaper.convert(s));
        }
        GUIFunctions.refresh();
    }
}
