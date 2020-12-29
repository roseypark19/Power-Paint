/*
 * TCSS 305 - Fall 2020
 * 
 * A ShapeAttributeContainer class to store all necessary data for Shapes
 * that have been drawn on a DrawPanel.
 */

package tools;

import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;

/**
 * This program stores and provides access to all necessary data for
 * Shapes that have been drawn on a DrawPanel.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public final class ShapeAttributeContainer implements Serializable {
	
	/** A generated serial version ID. */
	private static final long serialVersionUID = 6401269904836882258L;
	
	/** This ShapeAttributeContainer's Shape. */
	private final Shape myShape;
	
	/** The Color of this ShapeAttributeContainer's Shape. */
	private final Color myColor;
	
	/** The fill Color of this ShapeAttributeContainer's Shape. */
	private final Color myFillColor;
	
	/** Indicates if this ShapeAttributeContainer's Shape is filled. */
	private final boolean myFilled;
	
	/** Indicates if a DrawPanel's 'erase' mode was active while storing this
	 * ShapeAttributeContainer's Shape.
	 */
	private final boolean myEraserActivated;
	
	/** The stroke width this ShapeAttributeContainer's Shape. */ 
	private final int myStrokeWidth;
	
	/** The ID number of this ShapeAttributeContainer's Shape. */ 
	private final int myID;
	
	/**
	 * Constructs a new ShapeAttributeContainer with provided information
	 * about a particular Shape drawn on a DrawPanel.
	 * 
	 * @param theShape is the drawn Shape.
	 * @param theFilled indicates if theShape is filled.
	 * @param theEraserActivated indicates if the 'erase' mode was active on
	 * theShape's corresponding DrawPanel at the time it was drawn.
	 * @param theColor is the Color of theShape.
	 * @param theFill is the fill Color of theShape.
	 * @param theStrokeWidth is the stroke width of theShape.
	 * @param theID is the ID number of theShape.
	 */
	public ShapeAttributeContainer(final Shape theShape, final boolean theFilled, 
			                       final boolean theEraserActivated, 
			                       final Color theColor, final Color theFill, 
			                       final int theStrokeWidth, final int theID) {
		myShape = theShape;
		myColor = theColor;
		myFillColor = theFill;
		myFilled = theFilled;
		myEraserActivated = theEraserActivated;
		myStrokeWidth = theStrokeWidth;
		myID = theID;
	}
	
	/**
	 * Provides a copied version of this ShapeAttributeContainer with a 
	 * new Shape ID number.
	 * 
	 * @param theID is the new Shape ID number.
	 * @return the copied ShapeAttributeContainer with newly assigned Shape ID number.
	 */
	public final ShapeAttributeContainer copyWithGivenID(final int theID) {
		return new ShapeAttributeContainer(getShape(), myFilled, myEraserActivated,
				                           myColor, myFillColor, myStrokeWidth, theID);
	}
	
	/**
	 * Provides access to this ShapeAttributeContainer's Shape.
	 * 
	 * @return this ShapeAttributeContainer's Shape.
	 */
	public final Shape getShape() {
		return myShape;
	}
	
	/**
	 * Provides access to the Color of this ShapeAttributeContainer's Shape.
	 * 
	 * @return the Color of this ShapeAttributeContainer's Shape.
	 */
	public final Color getColor() {
		return myColor;
	}
	
	/**
	 * Provides access to the fill Color of this ShapeAttributeContainer's Shape.
	 * 
	 * @return the fill Color of this ShapeAttributeContainer's Shape.
	 */
	public final Color getFillColor() {
		return myFillColor;
	}
	
	/**
	 * Indicates if this ShapeAttributeContainer's Shape is filled.
	 * 
	 * @return true if this ShapeAttributeContainer's Shape is filled, false otherwise.
	 */
	public final boolean isFilled() {
		return myFilled;
	}
	
	/**
	 * Indicates if the 'erase' mode was active on this ShapeAttributeContainer's Shape's 
	 * corresponding DrawPanel at the time it was drawn.
	 * 
	 * @return true if the 'erase' mode was active, false otherwise.
	 */
	public final boolean isEraserActivated() {
		return myEraserActivated;
	}
	
	/**
	 * Provides access to the stroke width of this ShapeAttributeContainer's Shape.
	 * 
	 * @return the stroke width of this ShapeAttributeContainer's Shape.
	 */
	public final int getStrokeWidth() {
		return myStrokeWidth;
	}
	
	/**
	 * Provides access to the ID number of this ShapeAttributeContainer's Shape.
	 * 
	 * @return the ID number of this ShapeAttributeContainer's Shape.
	 */
	public final int getID() {
		return myID;
	}
	
}
