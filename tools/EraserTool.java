package tools;

public final class EraserTool extends PencilTool {
	
	private static final boolean FOLLOWER = true;
	private static final boolean ERASABLE = false;
	private static final String BASIC_NAME = "Eraser";
	private static final String ICON_FILE = "images//eraser.gif";
	
	public EraserTool() {
		super(FOLLOWER, ERASABLE, BASIC_NAME, ICON_FILE);
	}
	
}
