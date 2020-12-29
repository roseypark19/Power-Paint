/*
 * TCSS 305 - Fall 2020
 * 
 * A simple ColorIcon class to create Icons corresponding to each Color
 * MenuItem in a PowerPaintFrame's Options Menu.
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import javax.swing.Icon;

/**
 * This program creates and updates ColorIcons corresponding to the
 * individual Color MenuItems within the Options Menu of a PowerPaintFrame.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public final class ColorIcon implements Icon {
	
	/** A ColorIcon's width. */
	private static final double WIDTH = 15;
	
	/** A ColorIcon's height. */
	private static final double HEIGHT = 15;
	
	/** A ColorIcon's border width. */
	private static final float BORDER_WIDTH = 2;
	
	/** A ColorIcon's border Color. */
	private static final Color BORDER_COLOR = Color.BLACK;
	
	/** A ColorIcon's fill Color. */
	private Color myFillColor;
	
	/**
	 * Constructs a new ColorIcon with a provided fill Color.
	 * 
	 * @param theColor is the Color.
	 * @throws NullPointerException if theColor is null.
	 */
	public ColorIcon(final Color theColor) {
		Objects.requireNonNull(theColor, "Colors must be non-null!");
		myFillColor = theColor;
	}

	/**
	 * Paints this ColorIcon.
	 * 
	 * @param theComp is a Component to get properties useful for painting - unused.
	 * @param theGraph is the Graphics context.
	 * @param theX is the X coordinate of the icon's top-left corner - unused.
	 * @param theY is the Y coordinate of the icon's top-left corner - unused.
	 */
	@Override
	public final void paintIcon(final Component theComp, final Graphics theGraph, 
			                                       final int theX, final int theY) {
		final Graphics2D g2d = (Graphics2D) theGraph;
		final Rectangle2D.Double rect = 
				                      new Rectangle2D.Double(theX, theY, WIDTH, HEIGHT);
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(BORDER_COLOR);
		g2d.draw(rect);
		g2d.setColor(myFillColor);
		g2d.fill(rect);
	}
	
	/**
	 * Sets this ColorIcon's fill Color to a specified Color.
	 * 
	 * @param theColor is the fill Color.
	 * @throws NullPointerException if theColor is null.
	 */
	public final void setColor(final Color theColor) {
		Objects.requireNonNull(theColor, "Colors must be non-null!");
		myFillColor = theColor;
	}

	/**
	 * Provides access to this ColorIcon's width.
	 * 
	 * @return this ColorIcon's width.
	 */
	@Override
	public final int getIconWidth() {
		return (int) WIDTH;
	}

	/**
	 * Provides access to this ColorIcon's height.
	 * 
	 * @return this ColorIcon's height.
	 */
	@Override
	public final int getIconHeight() {
		return (int) HEIGHT;
	}

}
