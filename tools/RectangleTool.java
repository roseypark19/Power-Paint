/*
 * TCSS 305 - Fall 2020
 * 
 * A simple RectangleTool class to contain attributes and define
 * behaviors specific to RectangleTools.
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This program contains attributes and defines behaviors specific to RectangleTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public class RectangleTool extends AbstractPaintTool {

	/** A RectangleTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = false;
	
	/** A RectangleTool's erasability. */
	private static final boolean ERASEBLE = true;
	
	/** A RectangleTool's basic name. */
	private static final String BASIC_NAME = "Rectangle";
	
	/** A RectangleTool's Icon file name. */
	private static final String ICON_FILE = "images//rectangle_bw.gif";
	
	/** Constructs a RectangleTool with the defined RectangleTool attributes. */
	public RectangleTool() {
		this(FOLLOWER, ERASEBLE, BASIC_NAME, ICON_FILE);
	}
	
	/**
	 * Constructs a new RectangleTool and calls RectangleTool's superclass constructor 
	 * with pre-defined PaintTool attributes.
	 * 
	 * @param theFollower is the Path-dependency while drawing.
	 * @param theErasable is the erasability.
	 * @param theBasicName is the basic name.
	 * @param theIconFile is the Icon file name.
	 */
	protected RectangleTool(final boolean theFollower, final boolean theErasable,
			                final String theBasicName, final String theIconFile) {
		super(theFollower, theErasable, theBasicName, theIconFile);
	}

	/**
	 * Provides access to the Shape drawn by this RectangleTool.
	 * 
	 * @return the Shape drawn by this RectangleTool.
	 */
	@Override
	public Shape getShape() {
		final Rectangle2D.Double rect = new Rectangle2D.Double();
		double startX = getStartPoint().getX();
		double startY = getStartPoint().getY();
		final double endX = getEndPoint().getX();
		final double endY = getEndPoint().getY();
		final double dX = Math.abs(getEndPoint().getX() - getStartPoint().getX());
		final double dY = Math.abs(getEndPoint().getY() - getStartPoint().getY());
		if (endX < startX && endY < startY) {
			startX = endX;
			startY = endY;
		} else if (endY < startY) {
			startY = endY;
		} else if (endX < startX) {
			startX = endX;
		}
		rect.setRect(startX, startY, dX, dY);
		return rect;
	}

}
