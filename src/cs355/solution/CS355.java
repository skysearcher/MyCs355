package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.MyController;
import cs355.model.MyModel;
import cs355.view.MyViewRefresher;

import java.awt.*;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

    /**
     * This is where it starts.
     *
     * @param args = the command line arguments
     */
    public static void main(String[] args) {

        // Fill in the parameters below with your controller and view.
        MyModel myMod = new MyModel();
        MyViewRefresher myView = new MyViewRefresher(myMod);
        MyController myCont = new MyController(myMod, myView);
        myMod.addObserver(myView);
        GUIFunctions.createCS355Frame(myCont, myView);
        GUIFunctions.changeSelectedColor(Color.white);
        GUIFunctions.refresh();
        GUIFunctions.setHScrollBarMax(2048);
        GUIFunctions.setHScrollBarMin(0);
        GUIFunctions.setHScrollBarKnob(512);
        GUIFunctions.setHScrollBarPosit(0);
        GUIFunctions.setVScrollBarMax(2048);
        GUIFunctions.setVScrollBarMin(0);
        GUIFunctions.setVScrollBarKnob(512);
        GUIFunctions.setVScrollBarPosit(0);
    }
}
