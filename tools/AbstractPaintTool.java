/*
 * TCSS 305 - Fall 2020
 * 
 * An AbstractPaintTool class to define general PaintTool behaviors and attributes.
 */

package tools;

import java.awt.Point;
import java.util.Objects;

/**
 * This program defines the general attributes and behaviors shared by all PaintTools.
 * 
 * @author Parker Rosengreen
 * @version 13 December 2020
 */
public abstract class AbstractPaintTool implements PaintTool {

	/** A PaintTool's initial point. */
	private static final Point INITIAL_POINT = new Point(-10, -10);
	
	/** Indicates whether or not this PaintTool's drawing patterns are Path-dependent. */
	private final boolean myPathDependency;
	
	/** Indicates if this PaintTool is erasable. */
	private final boolean myErasable;
	
	/** This PaintTool's basic name. */
	private final String myBasicName;
	
	/** The file name for this PaintTool's Icon. */
	private final String myIconFile;
	
	/** This PaintTool's start Point. */
	private Point myStartPoint;
	
	/** This PaintTool's end Point. */
	private Point myEndPoint;
	
	
	/**
	 * Constructs an AbstractPaintTool with the provided path dependency and
	 * erasable value.
	 *  
	 * @param thePathDependency is this PaintTool's Path dependency.
	 * @param theErasable is this PaintTool's erasable value. 
	 * @throws NullPointerException if theBasicName is null.
	 * @throws NullPointerException if theIconFile is null.
	 * @throws IllegalArgumentException if theBasicName is empty.
	 * @throws IllegalArgumentException if theIconFile is empty.
	 */
	protected AbstractPaintTool(final boolean thePathDependency, 
			                    final boolean theErasable, final String theBasicName,
			                                               final String theIconFile) {
		Objects.requireNonNull(theBasicName, "Basic names must be non-null!");
		Objects.requireNonNull(theIconFile, "Icon file names must be non-null!");
		if (theBasicName.isEmpty() || theIconFile.isEmpty()) {
			throw new IllegalArgumentException("Basic and Icon file names "
					                           + "cannot be empty!");
		}
		setToInitialPoints();
		myPathDependency = thePathDependency;
		myErasable = theErasable;
		myBasicName = theBasicName;
		myIconFile = theIconFile;
	}
	
	/**
	 * Sets this PaintTool's start Point.
	 * 
	 * @param thePoint is the Point to be assigned.
	 * @throws NullPointerException if thePoint is null.
	 */
	@Override
	public final void setStartPoint(final Point thePoint) {
		Objects.requireNonNull(thePoint, "Points must be non-null!");
		myStartPoint = thePoint;
		myEndPoint = thePoint;
	}
	
	/**
	 * Provides access to this PaintTool's start Point.
	 * 
	 * @return this PaintTool's start Point. 
	 */
	protected final Point getStartPoint() {
		return myStartPoint;
	}
	
	/**
	 * Sets this PaintTool's end Point.
	 * 
	 * @param thePoint is the Point to be assigned.
	 * @throws NullPointerException if thePoint is null.
	 */
	@Override
	public final void setEndPoint(final Point thePoint) {
		Objects.requireNonNull(thePoint, "Points must be non-null!");
		myEndPoint = thePoint;
	}
	
	/**
	 * Provides access to this PaintTool's end Point.
	 * 
	 * @return this PaintTool's end Point. 
	 */
	protected final Point getEndPoint() {
		return myEndPoint;
	}
	
	/**
	 * Provides access to this PaintTool's basic name.
	 * 
	 * @return this PaintTool's basic name. 
	 */
	public final String getBasicName() {
		return myBasicName;
	}
	
	/**
	 * Provides access to the file name of this PaintTool's Icon.
	 * 
	 * @return the file name of this PaintTool's Icon.
	 */
	public final String getIconFile() {
		return myIconFile;
	}
	
	/** Resets this PaintTool to its initial positioning. */
	public final void setToInitialPoints() {
		myStartPoint = INITIAL_POINT;
		myEndPoint = INITIAL_POINT;
	}
	
	/**
	 * Provides whether or not this PaintTool's drawing patterns are Path-dependent.
	 * 
	 * @return true if this PaintTool is Path-dependent, false otherwise.
	 */
	public final boolean isPathFollower() {
		return myPathDependency;
	}
	
	/**
	 * Provides whether or not this PaintTool is erasable. 
	 * 
	 * @return true if this PaintTool is erasable, false otherwise.
	 */
	public final boolean isErasable() {
		return myErasable;
	}
}
