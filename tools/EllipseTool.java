/*
 * TCSS 305 - Fall 2020
 * 
 * A simple EllipseTool class to contain attributes and define
 * behaviors specific to EllipseTools.
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This program contains attributes and defines behaviors specific to EllipseTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public final class EllipseTool extends RectangleTool {
	
	/** A EllipseTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = false;
	
	/** A EllipseTool's erasability. */
	private static final boolean ERASABLE = true;
	
	/** A EllipseTool's basic name. */
	private static final String BASIC_NAME = "Ellipse";
	
	/** A EllipseTool's Icon file name. */
	private static final String ICON_FILE = "images//ellipse_bw.gif";
	
	/** Constructs a new EllipseTool and calls EllipseTool's superclass constructor 
	 *  with the defined EllipseTool attributes. 
	 */
	public EllipseTool() {
		super(FOLLOWER, ERASABLE, BASIC_NAME, ICON_FILE);
	}

	/**
	 * Provides access to the Shape drawn by this EllipseTool.
	 * 
	 * @return the Shape drawn by this EllipseTool.
	 */
	@Override
	public final Shape getShape() {
		final Ellipse2D.Double ellipse = new Ellipse2D.Double();
		ellipse.setFrame((Rectangle2D.Double) super.getShape());
		return ellipse;
	}

}
