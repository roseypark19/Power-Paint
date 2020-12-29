/*
 * TCSS 305 - Fall 2020
 * 
 * A simple SquareTool class to contain attributes and define
 * behaviors specific to SquareTools.
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This program contains attributes and defines behaviors specific to SquareTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public class SquareTool extends RectangleTool {
	
	/** A SquareTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = false;
	
	/** A SquareTool's erasability. */
	private static final boolean ERASEBLE = true;
	
	/** A SquareTool's basic name. */
	private static final String BASIC_NAME = "Square";
	
	/** A SquareTool's Icon file name. */
	private static final String ICON_FILE = "images//square_bw.gif";
	
	/** Constructs a SquareTool with the defined SquareTool attributes. */
	public SquareTool() {
		this(FOLLOWER, ERASEBLE, BASIC_NAME, ICON_FILE);
	}
	
	/**
	 * Constructs a new SquareTool and calls SquareTool's superclass constructor 
	 * with pre-defined PaintTool attributes.
	 * 
	 * @param theFollower is the Path-dependency while drawing.
	 * @param theErasable is the erasability.
	 * @param theBasicName is the basic name.
	 * @param theIconFile is the Icon file name.
	 */
	protected SquareTool(final boolean theFollower, final boolean theErasable,
			             final String theBasicName, final String theIconFile) {
		super(theFollower, theErasable, theBasicName, theIconFile);
	}
	
	/**
	 * Provides access to the Shape drawn by this SquareTool.
	 * 
	 * @return the Shape drawn by this SquareTool.
	 */
	@Override
	public Shape getShape() {
		final Rectangle2D.Double rect = (Rectangle2D.Double) super.getShape();
		double startX = getStartPoint().getX();
		double startY = getStartPoint().getY();
		final double endX = getEndPoint().getX();
		final double endY = getEndPoint().getY();
		final double sideLength = Math.max(rect.getWidth(), rect.getHeight());
		if (endX < startX && endY < startY) {
			startX -= sideLength;
			startY -= sideLength; 
		} else if (endY < startY) {
			startY -= sideLength; 
		} else if (endX < startX) {
			startX -= sideLength;
		}
		rect.setRect(startX, startY, sideLength, sideLength);
		return rect;
	}

}
