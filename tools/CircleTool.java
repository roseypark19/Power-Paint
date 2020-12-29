/*
 * TCSS 305 - Fall 2020
 * 
 * A simple CircleTool class to contain attributes and define
 * behaviors specific to CircleTools.
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This program contains attributes and defines behaviors specific to CircleTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public final class CircleTool extends SquareTool {
	
	/** A CircleTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = false;
	
	/** A CircleTool's erasability. */
	private static final boolean ERASABLE = true;
	
	/** A CircleTool's basic name. */
	private static final String BASIC_NAME = "Circle";
	
	/** A CircleTool's Icon file name. */
	private static final String ICON_FILE = "images//circle_bw.gif";
	
	/** Constructs a new CircleTool and calls CircleTool's superclass constructor 
	 *  with the defined CircleTool attributes. 
	 */
	public CircleTool() {
		super(FOLLOWER, ERASABLE, BASIC_NAME, ICON_FILE);
	}

	/**
	 * Provides access to the Shape drawn by this CircleTool.
	 * 
	 * @return the Shape drawn by this CircleTool.
	 */
	@Override
	public final Shape getShape() {
		final Ellipse2D.Double circle = new Ellipse2D.Double();
		circle.setFrame((Rectangle2D.Double) super.getShape());
		return circle;
	}
}
