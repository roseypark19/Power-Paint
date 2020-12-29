/*
 * TCSS 305 - Fall 2020
 * 
 * A DrawPanel class to display user drawings on the Power Paint GUI.
 */

package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.PaintTool;
import tools.ShapeAttributeContainer;

/**
 * This program implements the functions and attributes necessary for
 * displaying drawings on the Power Paint GUI.
 * 
 * @author Parker Rosengreen
 * @version 9 December 2020
 */
public final class DrawPanel extends JPanel {
	
	/** A DrawPanel's preferred width. */
	private static final int WIDTH = 500;
	
	/** A DrawPanel's preferred height. */
	private static final int HEIGHT = 300;
	
	/** A stroke width to indicate a non-visible drawing. */
	private static final int MIN_STROKE = 0;
	
	/** A DrawPanel's maximum supported stroke-width. */
	private static final int MAX_STROKE = 20;
	
	/** A DrawPanel's initial stroke-width. */
	private static final int START_STROKE = 10;
	
	/** A generated serial version ID. */
	private static final long serialVersionUID = 302736941060693880L;
	
	/** The UW purple Color. */
	private static final Color UW_PURPLE = new Color(51, 0, 111);
	
	/** The UW gold Color. */
	private static final Color UW_GOLD = new Color(232, 211, 162);
	
	/** A Random Object used in Shape ID generation. */
	private static final Random RANDOM = new Random();
	
	/** A collection of Shapes drawn on this DrawPanel. */
	private final List<ShapeAttributeContainer> myPreviousShapes;
	
	/** A collection of undone Shapes drawn on this DrawPanel. */
	private final Stack<ShapeAttributeContainer> myRedoShapes;
	
	/** A collection of the unique ID numbers of Shapes drawn on this DrawPanel. */
	private final List<Integer> myUsedIDs;
	
	/** This DrawPanel's primary Color. */
	private Color myPrimaryColor;
	
	/** This DrawPanel's secondary Color. */
	private Color mySecondaryColor;
	
	/** This DrawPanel's fill Color. */
	private Color myFillColor;
	
	/** This DrawPanel's selected Color type. */
	private Color mySelectedColor;
	
	/** This DrawPanel's current PaintTool. */
	private PaintTool myCurrentTool;
	
	/** This DrawPanel's stroke width. */
	private int myStrokeWidth;
	
	/** The next unique Shape ID number to be assigned by this DrawPanel. */
	private int myShapeID;
	
	/** Indicates if a non-erasable tool is activated on this DrawPanel. */
	private boolean myEraserActivated;
	
	/** Indicates if Shapes drawn on this DrawPanel are to be filled. */
	private boolean myFillEnabled;

	/**
	 * Constructs a new DrawPanel supporting stroke widths in the
	 * range range [0, 20]. The initial stroke width is set to 10. Shape filling is 
	 * initially disabled. The initial primary, secondary, fill, and background Colors 
	 * assigned are UW purple, UW gold, black, and white, respectively. The initial 
	 * PaintTool assigned to this DrawPanel is determined via the PaintTool parameter 
	 * theTool.
	 * 
	 * @param theTool is the starting PaintTool.
	 * @throws NullPointerException if theTool is null.
	 */
	public DrawPanel(final PaintTool theTool) {
		super();
		Objects.requireNonNull(theTool, "PaintTools must be non-null!");
		myCurrentTool = theTool;
		myEraserActivated = !myCurrentTool.isErasable();
		myFillEnabled = false;
		myPrimaryColor = UW_PURPLE;
		mySecondaryColor = UW_GOLD;
		myFillColor = Color.BLACK;
		myStrokeWidth = START_STROKE;
		myPreviousShapes = new ArrayList<ShapeAttributeContainer>();
		myRedoShapes = new Stack<ShapeAttributeContainer>();
		myUsedIDs = new ArrayList<Integer>();
		myShapeID = createUniqueID();
		mySelectedColor = myPrimaryColor;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		addListeners();
	}
	
	/**
	 * Adds a MouseListener and MouseMotionListener to respond
	 * to interactions between the user and this DrawPanel on the PowerPaint GUI.
	 */
	private void addListeners() {
		addMouseListener(new MyMouseInputAdapter());
		addMouseMotionListener(new MyMouseInputAdapter());
	}
	
	/**
	 * Provides access to the name of this DrawPanel's currently selected PaintTool.
	 * 
	 * @return the name of this DrawPanel's currently selected PaintTool.
	 */
	public final String getCurrentToolName() {
		return myCurrentTool.getBasicName();
	}
	
	/**
	 * Sets this DrawPanel's current PaintTool.
	 * 
	 * @param theTool is the PaintTool to be assigned.
	 */
	public final void setCurrentTool(final PaintTool theTool) {
		Objects.requireNonNull(theTool, "PaintTools must be non-null!");
		myCurrentTool = theTool;
	}
	
	/**
	 * Sets this DrawPanel's stroke width.
	 * 
	 * @param theWidth is the stroke width to be assigned.
	 * @throws IllegalArgumentException if theWidth < MIN_STROKE or 
	 * theWidth > MAX_STROKE.
	 */
	public final void setStrokeWidth(final int theWidth) {
		if (theWidth < MIN_STROKE || theWidth > MAX_STROKE) {
			throw new IllegalArgumentException(
					                 "Stroke widths must be non-negative and <= 20!");
		}
		myStrokeWidth = theWidth;
	}
	
	/**
	 * Clears all drawn Shapes from this DrawPanel. NOTE: A call to clearShapes() 
	 * deletes all progress and cannot be undone! 
	 */
	public final void clearShapes() {
		myPreviousShapes.clear();
		myRedoShapes.clear();
		myUsedIDs.clear();
		myCurrentTool.setToInitialPoints();
		repaint();
	}
	
	/**
	 * Sets this DrawPanel's primary Color. 
	 * 
	 * @param theColor is the primary Color to be assigned.
	 * @throws NullPointerException if theColor is null.
	 */
	public final void setPrimaryColor(final Color theColor) {
		Objects.requireNonNull(theColor, "Primary Colors must be non-null!");
		myPrimaryColor = theColor;
	}
	
	/**
	 * Sets this DrawPanel's secondary Color.
	 * 
	 * @param theColor is the secondary Color to be assigned.
	 * @throws NullPointerException if theColor is null.
	 */
	public final void setSecondaryColor(final Color theColor) {
		Objects.requireNonNull(theColor, "Secondary Colors must be non-null!");
		mySecondaryColor = theColor;
	}
	
	/**
	 * Sets this DrawPanel's fill Color. 
	 * 
	 * @param theColor is the fill Color to be assigned.
	 * @throws NullPointerException if theColor is null.
	 */
	public final void setFillColor(final Color theColor) {
		Objects.requireNonNull(theColor, "Fill Colors must be non-null!");
		myFillColor = theColor;
	}
	
	/**
	 * Sets this DrawPanel's background Color.
	 * 
	 * @param theColor is the background Color to be assigned.
	 * @throws NullPointerException if theColor is null.
	 */
	@Override
	public final void setBackground(final Color theColor) {
		Objects.requireNonNull(theColor, "Background Colors must be non-null!");
		super.setBackground(theColor);
	}
	
	/**
	 * Provides access to this DrawPanel's primary Color.
	 * 
	 * @return this DrawPanel's primary Color.
	 */
	public final Color getPrimaryColor() {
		return myPrimaryColor;
	}
	
	/**
	 * Provides access to this DrawPanel's secondary Color.
	 * 
	 * @return this DrawPanel's secondary Color.
	 */
	public final Color getSecondaryColor() {
		return mySecondaryColor;
	}
	
	/**
	 * Provides access to this DrawPanel's fill Color.
	 * 
	 * @return this DrawPanel's fill Color.
	 */
	public final Color getFillColor() {
		return myFillColor;
	}

	/**
	 * Provides a defensive copy of this DrawPanel's currently drawn Shapes for saving.
	 * 
	 * @return the defensive copy of this DrawPanel's drawn Shapes.
	 */
	public final List<ShapeAttributeContainer> getDrawnShapes() {
		final List<ShapeAttributeContainer> copy = 
				                            new ArrayList<ShapeAttributeContainer>();
		for (final ShapeAttributeContainer sac : myPreviousShapes) {
			copy.add(sac.copyWithGivenID(sac.getID()));
		}
		return copy;
	}
	
	/**
	 * Enables/disables the filling of Shapes drawn on this DrawPanel according
	 * to the corresponding truth value of the boolean parameter theEnabled.
	 * 
	 * @param theEnabled is the boolean true/false (enabled/disabled) indicator.
	 */
	public void manageFill(final boolean theEnabled) {
		myFillEnabled = theEnabled;
	}
	
	/**
	 * Enables/disables the erasing of Shapes drawn on this DrawPanel according
	 * to the corresponding truth value of the boolean parameter theEnabled.
	 * 
	 * @param theEnabled is the boolean true/false (enabled/disabled) indicator.
	 */
	public final void setErase(final boolean theEnabled) {
		myEraserActivated = theEnabled;
	}
	
	/**
	 * Create a random, unique integer ID for a Shape to be drawn on this DrawPanel.
	 * 
	 * @return the Shape's unique integer ID.
	 */
	private int createUniqueID() {
		int id = RANDOM.nextInt();
		while (myUsedIDs.contains(id)) {
			id = RANDOM.nextInt();
		}
		myUsedIDs.add(id);
		return id;
	}
	
	/**
	 * Removes the most recently drawn Shape from this DrawPanel.
	 */
	public final void undo() {
		if (areShapesDrawn()) {
			myCurrentTool.setToInitialPoints();
			final int id = myPreviousShapes.get(myPreviousShapes.size() - 1).getID();
			do {
				myRedoShapes.push(myPreviousShapes.remove(myPreviousShapes.size() - 1));
			} while (areShapesDrawn() && 
					 myPreviousShapes.get(myPreviousShapes.size() - 1).getID() == id);
			repaint();
		}
	}
	
	/**
	 * Adds the most recently undone Shape back to the collection
	 * Shapes to be drawn on this DrawPanel.
	 */
	public final void redo() {
		if (isRedoAvailable()) {
			final int id = myRedoShapes.peek().getID();
			do {
				myPreviousShapes.add(myRedoShapes.pop());
			} while (isRedoAvailable() && myRedoShapes.peek().getID() == id);
			repaint();
		}
	}
	
	/**
	 * Adds all Shapes drawn on a previously saved DrawPanel to the
	 * collection of Shapes to be drawn on this DrawPanel.
	 * 
	 * @param thePanel is the previously saved DrawPanel.
	 * @throws NullPointerException if theShapes is null.
	 */
	public final void addSavedShapes(final List<ShapeAttributeContainer> theShapes) {
		Objects.requireNonNull(theShapes, "Shape Lists must be non-null!");
		processShapes(theShapes);
		repaint();
	}
	
	/**
	 * Ensures that all Shapes drawn on a previously saved DrawPanel are
	 * added to this DrawPanel's collection of Shapes to be drawn with unique
	 * ID numbers. 
	 * 
	 * @param thePanel is the previously saved DrawPanel.
	 */
	private void processShapes(final List<ShapeAttributeContainer> theShapes) {
		while (!theShapes.isEmpty()) {
			final int temp = theShapes.get(0).getID();
			final boolean containsID = myUsedIDs.contains(temp);
			int newID = 0;
			if (containsID) {
				newID = createUniqueID();
			} else {
				myUsedIDs.add(temp);
			}
			do {
				if (containsID) {
					myPreviousShapes.add(theShapes.remove(0).copyWithGivenID(newID));
				} else {
					myPreviousShapes.add(theShapes.remove(0));
				}	
			} while (!theShapes.isEmpty() && theShapes.get(0).getID() == temp);
		}
	}

	
	/**
	 * Indicates whether or not Shapes are drawn on this DrawPanel.
	 * 
	 * @return true if Shapes are drawn, false otherwise.
	 */
	public final boolean areShapesDrawn() {
		return !myPreviousShapes.isEmpty();
	}
	
	/**
	 * Indicates whether or not a Shape can be redrawn on this DrawPanel.
	 * 
	 * @return true if a Shape can be redrawn, false otherwise.
	 */
	public final boolean isRedoAvailable() {
		return !myRedoShapes.isEmpty();
	}
	
	/**
	 * Draws all Shapes on this DrawPanel. NOTE: Shapes drawn with a thickness
	 * of 0 will produce no output! However, rectangular Shapes may still be
	 * filled if Shape filling is enabled on the GUI.
	 *
	 * @param theGraphics is the Graphics context.
	 */
	@Override
	public final void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g2d = (Graphics2D) theGraphics;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
							 RenderingHints.VALUE_ANTIALIAS_ON);
		for (final ShapeAttributeContainer sac : myPreviousShapes) {
			if (sac.isEraserActivated()) {
				g2d.setPaint(getBackground());
			} else {
				g2d.setPaint(sac.getColor());
			}
			g2d.setStroke(new BasicStroke(sac.getStrokeWidth()));
			g2d.draw(sac.getShape());
			if (sac.isFilled()) {
				g2d.setPaint(sac.getFillColor());
				g2d.fill(sac.getShape());
			}
		}
		if (myEraserActivated) {
			g2d.setPaint(getBackground());
		} else {
			g2d.setPaint(mySelectedColor);
		}
		g2d.setStroke(new BasicStroke(myStrokeWidth));
		if (myStrokeWidth > MIN_STROKE) {
			g2d.draw(myCurrentTool.getShape());
		}
		if (myFillEnabled) {
			g2d.setPaint(myFillColor);
			g2d.fill(myCurrentTool.getShape());
		}
	}

/**
 * A combined MouseListener and MouseMotionListener class to handle all
 * interactions between the mouse and this DrawPanel.
 * 
 * @author Parker Rosengreen
 * @version 10 December 2020
 */
private class MyMouseInputAdapter extends MouseInputAdapter {

	/**
	 * Method called in response to a singular mouse click - unused
	 * by this program.
	 * 
	 * @param theEvent is the MouseEvent.
	 */
	@Override
	public final void mouseClicked(final MouseEvent theEvent) {}

	/**
	 * Method called in response to the mouse being pressed. Shape
	 * drawing begins at the Point provided by theEvent. A left click
	 * selects this DrawPanel's primary Color, and a right click
	 * selects this DrawPanel's secondary Color.
	 * 
	 * @param theEvent is the MouseEvent.
	 */
	@Override
	public final void mousePressed(final MouseEvent theEvent) {
		myCurrentTool.setStartPoint(theEvent.getPoint());
		if (theEvent.getButton() == MouseEvent.BUTTON1) {
			mySelectedColor = myPrimaryColor;
		} else {
			mySelectedColor = mySecondaryColor;
		}
		repaint();
	}

	/**
	 * Method called in response to the mouse being released. Adds a new
	 * Shape to the collection of Shapes to be drawn on this DrawPanel under
	 * the condition that the current thickness is greater than 0 or Shape
	 * filling is enabled on this DrawPanel and a rectangular Shape is selected.
	 * 
	 * @param theEvent is the MouseEvent.
	 */
	@Override
	public final void mouseReleased(final MouseEvent theEvent) {
		if (myStrokeWidth > MIN_STROKE ||
			(myCurrentTool.getShape() instanceof RectangularShape && myFillEnabled)) {
			myPreviousShapes.add( 
			      new ShapeAttributeContainer(myCurrentTool.getShape(), 
			        		  myFillEnabled, myEraserActivated, mySelectedColor, 
			        		                 myFillColor, myStrokeWidth, myShapeID));
		}
		myShapeID = createUniqueID();
	}

	/**
	 * Method called in response to the mouse entering this DrawPanel.
	 * Sets the mouse cursor to a crosshair cursor.
	 * 
	 * @param theEvent is theMouseEvent;
	 */
	@Override
	public final void mouseEntered(final MouseEvent theEvent) {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	/**
	 * Method called in response to the mouse leaving this DrawPanel.
	 * Sets the mouse cursor to the default cursor.
	 * 
	 * @param theEvent is theMouseEvent;
	 */
	@Override
	public final void mouseExited(final MouseEvent theEvent) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Method called in response to the mouse being pressed and dragged on
	 * this DrawPanel. Shape drawing ends at the Point provided by theEvent.
	 * Adds a new Shape to the collection of Shapes to be drawn on this DrawPanel 
	 * under the condition that the current thickness is greater than 0 and the 
	 * currently selected PaintTool draws with the mouse's drag path.
	 */
	@Override
	public final void mouseDragged(final MouseEvent theEvent) {
		myCurrentTool.setEndPoint(theEvent.getPoint());
		if (myCurrentTool.isPathFollower() && myStrokeWidth > MIN_STROKE) {
			myPreviousShapes.add( 
					new ShapeAttributeContainer(myCurrentTool.getShape(), 
	        		               myFillEnabled, myEraserActivated, mySelectedColor, 
	        		               myFillColor, myStrokeWidth, myShapeID));
			myCurrentTool.setStartPoint(theEvent.getPoint());
		}
		repaint();	
	}

	/**
	 * Method called in response to mouse movement - unused
	 * by this program.
	 * 
	 * @param theEvent is the MouseEvent.
	 */
	@Override
	public final void mouseMoved(final MouseEvent theEvent) {}	
}
}
