/*
 * TCSS 305 - Fall 2020
 * 
 * A simple LineTool class to contain attributes and define
 * behaviors specific to LineTools.
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * This program contains attributes and defines behaviors specific to LineTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public class LineTool extends AbstractPaintTool {
	
	/** A LineTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = false;
	
	/** A LineTool's erasability. */
	private static final boolean ERASABLE = true;
	
	/** A LineTool's basic name. */
	private static final String BASIC_NAME = "Line";
	
	/** A LineTool's Icon file name. */
	private static final String ICON_FILE = "images//line_bw.gif";
	
	/** Constructs a LineTool with the defined LineTool attributes. */
	public LineTool() {
		this(FOLLOWER, ERASABLE, BASIC_NAME, ICON_FILE);
	}
	
	/**
	 * Constructs a new LineTool and calls LineTool's superclass constructor 
	 * with pre-defined PaintTool attributes.
	 * 
	 * @param theFollower is the Path-dependency while drawing.
	 * @param theErasable is the erasability.
	 * @param theBasicName is the basic name.
	 * @param theIconFile is the Icon file name.
	 */
	protected LineTool(final boolean theFollower, final boolean theErasable,
			           final String theBasicName, final String theIconFile) {
		super(theFollower, theErasable, theBasicName, theIconFile);
	}

	/**
	 * Provides access to the Shape drawn by this LineTool.
	 * 
	 * @return the Shape drawn by this LineTool.
	 */
	@Override
	public final Shape getShape() {
		return new Line2D.Double(getStartPoint(), getEndPoint());
	}
}
