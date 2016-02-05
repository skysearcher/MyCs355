package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Iterator;

public interface CS355Controller extends MouseListener, MouseMotionListener {

	// Color.

	/**
	 * Called when the user hits the color button.
	 * @param c = the new <i>drawing</i> color.
	 */
	void colorButtonHit(Color c);

	// Shapes.

	/**
	 * Called when the user hits the line button.
	 */
	void lineButtonHit();

	/**
	 * Called when the user hits the square button.
	 */
	void squareButtonHit();

	/**
	 * Called when the user hits the rectangle button.
	 */
	void rectangleButtonHit();

	/**
	 * Called when the user hits the circle button.
	 */
	void circleButtonHit();

	/**
	 * Called when the user hits the ellipse button.
	 */
	void ellipseButtonHit();

	/**
	 * Called when the user hits the triangle button.
	 */
	void triangleButtonHit();

	/**
	 * Called when the user hits the select button.
	 */
	void selectButtonHit();

	// Zooming.

	/**
	 * Called when the user hits the zoom in button.
	 */
	void zoomInButtonHit();

	/**
	 * Called when the user hits the zoom out button.
	 */
	void zoomOutButtonHit();

	/**
	 * Called when the horizontal scrollbar position changes.
	 * @param value = the new position.
	 */
	void hScrollbarChanged(int value);

	/**
	 * Called when the vertical scrollbar position changes.
	 * @param value = the new position.
	 */
	void vScrollbarChanged(int value);

	// 3D Model.

	/**
	 * Called to load a scene from a file.
	 * @param file = the file containing the scene to load.
	 */
	void openScene(File file);

	/**
	 * Called to toggle the 3D OpenGL display.
	 */
	void toggle3DModelDisplay();

	/**
	 * Called when the user presses keys. This is used
	 * for navigating in the 3D world.
	 * @param iterator = the iterator over the keys.
	 */
	void keyPressed(Iterator<Integer> iterator);

	// Image.

	/**
	 * Called to load a background image.
	 * @param file = the image file to load.
	 */
	void openImage(File file);

	/**
	 * Called to save the background image.
	 * @param file = the file to save the image to.
	 */
	void saveImage(File file);

	/**
	 * Called to toggle the background image display.
	 */
	void toggleBackgroundDisplay();

	// File menu.

	/**
	 * Called to save a drawing.
	 * @param file = the file to save the drawing to.
	 */
	void saveDrawing(File file);

	/**
	 * Called to open a drawing.
	 * @param file = the file to open the drawing from.
	 */
	void openDrawing(File file);

	// Edit menu.

	/**
	 * Called to delete the currently selected shape.
	 */
	void doDeleteShape();

	// Image menu.

	/**
	 * Called to perform edge detection
	 * on the background image.
	 */
	void doEdgeDetection();

	/**
	 * Called to perform sharpen
	 * on the background image.
	 */
	void doSharpen();

	/**
	 * Called to perform median blur
	 * on the background image.
	 */
	void doMedianBlur();

	/**
	 * Called to perform uniform blur
	 * on the background image.
	 */
	void doUniformBlur();

	/**
	 * Called to change the background image to grayscale.
	 */
	void doGrayscale();

	/**
	 * Called to change the contrast on the background image.
	 * @param contrastAmountNum = how much contrast to add.
	 */
	void doChangeContrast(int contrastAmountNum);

	/**
	 * Called to change the brightness on the background image.
	 * @param brightnessAmountNum = how much brightness to add.
	 */
	void doChangeBrightness(int brightnessAmountNum);

	// Object menu.

	/**
	 * Called to move the currently selected
	 * shape one slot forward.
	 */
	void doMoveForward();

	/**
	 * Called to move the currently selected
	 * shape one slot backward.
	 */
	void doMoveBackward();

	/**
	 * Called to move the currently
	 * selected shape to the front.
	 */
	void doSendToFront();

	/**
	 * Called to move the currently
	 * selected shape to the back.
	 */
	void doSendtoBack();
}
