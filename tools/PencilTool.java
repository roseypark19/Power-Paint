/*
 * TCSS 305 - Fall 2020
 * 
 * A simple PencilTool class to contain attributes specific to PencilTools.
 */

package tools;

/**
 * This program contains attributes specific to PencilTools.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public class PencilTool extends LineTool {
	
	/** A PencilTool's Path-dependency while drawing. */
	private static final boolean FOLLOWER = true;
	
	/** A PencilTool's erasability. */
	private static final boolean ERASABLE = true;
	
	/** A PencilTool's basic name. */
	private static final String BASIC_NAME = "Pencil";
	
	/** A PencilTool's Icon file name. */
	private static final String ICON_FILE = "images//pencil_bw.gif";
	
	/** Constructs a PencilTool with the defined PencilTool attributes. */
	public PencilTool() {
		this(FOLLOWER, ERASABLE, BASIC_NAME, ICON_FILE);
	}
	
	/**
	 * Constructs a new PencilTool and calls PencilTool's superclass constructor 
	 * with pre-defined PaintTool attributes.
	 * 
	 * @param theFollower is the Path-dependency while drawing.
	 * @param theErasable is the erasability.
	 * @param theBasicName is the basic name.
	 * @param theIconFile is the Icon file name.
	 */
	protected PencilTool(final boolean theFollower, final boolean theErasable,
			             final String theBasicName, final String theIconFile) {
		super(theFollower, theErasable, theBasicName, theIconFile);
	}
}
