/*
 * TCSS 305 - Fall 2020
 * 
 * A PaintTool interface to define behaviors required of each PaintTool subclass.
 */

package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * This program defines the methods that are to be implemented by PaintTool subclasses.
 * 
 * @author Parker Rosengreen
 * @version 13 December 2020
 */
public interface PaintTool {
	
	/**
	 * Provides access to this PaintTool's Shape.
	 * 
	 * @return this PaintTool's Shape.
	 */
	Shape getShape();
	
	/**
	 * Sets this PaintTool's start Point.
	 * 
	 * @param thePoint is the Point to be assigned.
	 */
	void setStartPoint(final Point thePoint);
	
	/**
	 * Sets this PaintTool's end Point.
	 * 
	 * @param thePoint is the Point to be assigned.
	 */
	void setEndPoint(final Point thePoint);
	
	/** Resets this PaintTool to its initial positioning. */
	void setToInitialPoints();
	
	/**
	 * Provides whether or not this PaintTool's drawing patterns are Path-dependent.
	 * 
	 * @return true if this PaintTool is Path-dependent, false otherwise.
	 */
	boolean isPathFollower();
	
	/**
	 * Provides whether or not this PaintTool is erasable. 
	 * 
	 * @return true if this PaintTool is erasable, false otherwise.
	 */
	boolean isErasable();
	
	/**
	 * Provides the basic name of this PaintTool.
	 * 
	 * @return the PaintTool's basic name.
	 */
	String getBasicName();
	
	/**
	 * Provides the file name for this PaintTool's Icon.
	 * 
	 * @return the file name for this PaintTool's Icon.
	 */
	String getIconFile();
}
