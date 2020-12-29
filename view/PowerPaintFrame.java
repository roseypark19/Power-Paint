/*
 * TCSS 305 - Fall 2020
 * 
 * A PowerPaintFrame class to contain all components of the Power Paint GUI.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.DrawPanel;
import tools.CircleTool;
import tools.EllipseTool;
import tools.EraserTool;
import tools.LineTool;
import tools.PaintTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.ShapeAttributeContainer;
import tools.SquareTool;

/**
 * This program initializes and adds all components of the Power Paint GUI to
 * the main frame.
 * 
 * @author Parker Rosengreen
 * @version 10 December 2020
 */
public final class PowerPaintFrame extends JFrame {
	
	/** A generated serial version ID. */
	private static final long serialVersionUID = -5519457525041784163L;
	
	/** The minor tick value for the 'Thickness' JSlider. */
	private static final int THICKNESS_MIN_TICK = 1;
	
	/** The major tick value for the 'Thickness' JSlider. */
	private static final int THICKNESS_MAJ_TICK = 5;
	
	/** The minimum value for the 'Thickness' JSlider. */
	private static final int THICKNESS_MIN = 0;
	
	/** The initial value for the 'Thickness' JSlider. */
	private static final int THICKNESS_START = 10;
	
	/** The maximum value for the 'Thickness' JSlider. */
	private static final int THICKNESS_MAX = 20;
	
	/** A scaling constant for the main frame Icon. */
	private static final int SCALING_WINDOW = 15;
	
	/** A scaling constant for pop-up window Icons. */
	private static final int SCALING_POPUP = 45;
	
	/** The main frame's image file name. */
	private static final String PALETTE_FILE = "images//painting.png";
	
	/** The Undo image file name. */
	private static final String UNDO_FILE = "images//undoicon.png";
	
	/** The Redo image file name. */
	private static final String REDO_FILE = "images//redoicon.png";
	
	/** The name of the main Power Paint frame. */
	private static final String MAIN_FRAME_NAME = "Power Paint";
	
	/** The name of the check-box to enable/disable Shape filling. */
	private static final String FILL_CHECKBOX = "Fill Shapes";
	
	/** The name of the file menu. */
	private static final String FILE_MENU = "File";
	
	/** The name of the save sub-menu. */
	private static final String FILE_SAVE = "Save...";
	
	/** The title of file saving error pop-ups. */
	private static final String SAVE_ERROR = "Error Saving File!";
	
	/** The title of file loading error pop-ups. */
	private static final String LOAD_ERROR = "Error Loading File!";
	
	/** The title of the FileDialog used for saving. */
	private static final String FILE_SAVE_POPUP = "Save to File";
	
	/** The error message displayed on Save file i/o error pop-ups. */
	private static final String FILE_SAVE_ERROR = "Could not save file: ";
	
	/** The name of the load sub-menu. */
	private static final String FILE_LOAD = "Load..."; 
	
	/** The title of the FileDialog used for loading. */
	private static final String FILE_LOAD_POPUP = "Load From File";
	
	/** The error message displayed on Load file i/o error pop-ups. */
	private static final String FILE_LOAD_ERROR = "Could not load file: ";
	
	/** A String literal utilized in the display of save/load Exception types. */
	private static final String SOURCE = "Source: ";
	
	/** The name of the edit menu. */
	private static final String EDIT_MENU = "Edit";
	
	/** The name of the undo menu item. */
	private static final String EDIT_UNDO = "Undo";
	
	/** The name of the redo menu item. */
	private static final String EDIT_REDO = "Redo";
	
	/** The name of the Options menu. */
	private static final String OPTIONS_MENU = "Options";
	
	/** The name of the thickness sub-menu. */
	private static final String OPTIONS_THICKNESS = "Thickness";
	
	/** The name of the primary Color menu item. */
	private static final String OPTIONS_PRIMARY = "Primary Color...";
	
	/** The title of the primary Color chooser dialog. */
	private static final String PRIMARY_POPUP = "Choose a Primary Color";
	
	/** The name of the secondary Color menu item. */
	private static final String OPTIONS_SECONDARY = "Secondary Color...";
	
	/** The title of the secondary Color chooser dialog. */
	private static final String SECONDARY_POPUP = "Choose a Secondary Color";
	
	/** The name of the fill Color menu item. */
	private static final String OPTIONS_FILL = "Fill Color...";
	
	/** The title of the fill Color chooser dialog. */
	private static final String FILL_POPUP = "Choose a Fill Color";
	
	/** The name of the background Color menu item. */
	private static final String OPTIONS_BACKGROUND = "Background Color...";
	
	/** The title of the background Color chooser dialog. */
	private static final String BACKGROUND_POPUP = "Choose a Background Color";
	
	/** The name of the clear menu item. */
	private static final String OPTIONS_CLEAR = "Clear";
	
	/** The title of the clear sub-menu verification pop-up. */
	private static final String CLEAR_POPUP = "Are You Sure You Want to Clear?";
	
	/** The warning message displayed to the user prior to performing a clear. */
	private static final String CLEAR_WARNING = "Warning!\nAll progress will be lost!"; 
	
	/** The name of the tools menu. */
	private static final String TOOLS_MENU = "Tools";
	
	/** The name of the help menu. */
	private static final String HELP_MENU = "Help";
	
	/** The name of the about menu item. */
	private static final String HELP_ABOUT = "About...";
	
	/** The name of the about sub-menu pop-up. */
	private static final String ABOUT_POPUP = "About";
	
	/** The panel on which Shapes are drawn. */
	private final DrawPanel myDrawPanel;
	
	/** A list of the individual PaintTool actions assigned to buttons on this frame. */
	private final List<PaintToolAction> myToolActions;
	
	/** Holds all mnemonic characters of top-level buttons and menus to avoid unintended
	 *  mnemonic duplications. 
	 */
	private final List<Character> myTopMnemonics;
	
	/** Holds all mnemonic characters of sub-menu items to avoid unintended
	 *  mnemonic duplications. 
	 */
	private final List<Character> mySubMnemonics;

	/**
	 * Constructs a new PowerPaintFrame, adding all JToolBar and JMenu components.
	 */
	public PowerPaintFrame() {
		super(MAIN_FRAME_NAME);
		final ImageIcon pic = new ImageIcon(PALETTE_FILE);
		final Image image =
                pic.getImage().getScaledInstance(SCALING_WINDOW, -1, 
                		                          java.awt.Image.SCALE_SMOOTH);
		setIconImage(image);
		myDrawPanel = new DrawPanel(new LineTool());
		add(myDrawPanel, BorderLayout.CENTER);
		myToolActions = new ArrayList<PaintToolAction>();
		myTopMnemonics = new ArrayList<Character>();
		mySubMnemonics = new ArrayList<Character>();
		addPaintActions();
		add(createToolBar(), BorderLayout.SOUTH);
		setJMenuBar(createMenuBar());
	}
	
	/**
	 * Constructs individual PaintTool Actions for each PaintTool offered on this
	 * PowerPaintFrame.
	 */
	private void addPaintActions() {
		final PaintTool pencil = new PencilTool();
		final PaintTool line = new LineTool();
		final PaintTool rectangle = new RectangleTool();
		final PaintTool square = new SquareTool();
		final PaintTool ellipse = new EllipseTool();
		final PaintTool circle = new CircleTool();
		final PaintTool eraser = new EraserTool();
		myToolActions.add(new PaintToolAction(pencil.getBasicName(), 
				                           new ImageIcon(pencil.getIconFile()), pencil));
		myToolActions.add(new PaintToolAction(line.getBasicName(), 
				                           new ImageIcon(line.getIconFile()), line));
		myToolActions.add(new PaintToolAction(rectangle.getBasicName(), 
				                           new ImageIcon(rectangle.getIconFile()), 
				                                                             rectangle));
		myToolActions.add(new PaintToolAction(square.getBasicName(), 
				                           new ImageIcon(square.getIconFile()), square));
		myToolActions.add(new PaintToolAction(ellipse.getBasicName(), 
				                           new ImageIcon(ellipse.getIconFile()), 
				                                                               ellipse));
		myToolActions.add(new PaintToolAction(circle.getBasicName(), 
				                           new ImageIcon(circle.getIconFile()), circle));
		myToolActions.add(new PaintToolAction(eraser.getBasicName(), 
				                           new ImageIcon(eraser.getIconFile()), eraser));
	}
	
	/**
	 * Creates a JToolBar to be added to this PowerPaintFrame, adding JToggleButtons
	 * linked to the individual PaintToolActions of this PowerPaintFrame.
	 * 
	 * @return the newly created JToolBar with configured JToggleButtons.
	 */
	private JToolBar createToolBar() {
		final JToolBar bar = new JToolBar();
		final ButtonGroup bGroup = new ButtonGroup();
		for (final PaintToolAction action : myToolActions) {
			final JToggleButton button = new JToggleButton(action);
			if (action.getValue(Action.NAME).equals(myDrawPanel.getCurrentToolName())) {	
				button.setSelected(true);
			}
			bGroup.add(button);
			bar.add(button);
		}
		return bar;
	}
	
	/**
	 * Creates a JMenuBar to be added to this PowerPaintFrame, adding all JMenus
	 * associated with the unique functionalities of this PowerPaintFrame.
	 * 
	 * @return the newly constructed JMenuBar with configured JMenus.
	 */
	private JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		mySubMnemonics.clear();
		menuBar.add(createEditMenu());
		mySubMnemonics.clear();
		menuBar.add(createOptionsMenu());
		mySubMnemonics.clear();
		menuBar.add(createToolsMenu());
		menuBar.add(createHelpMenu());
		mySubMnemonics.clear();
		menuBar.add(createFillCheckBox());
		return menuBar;
	}
	
	/**
	 * Creates the Options Menu to be added to this PowerPaintFrame's JMenuBar. Creates
	 * and adds all corresponding sub-menus and top-level MenuListeners.
	 * 
	 * @return the newly constructed Options Menu with configured 
	 * sub-menus and MenuListeners.
	 */
	private JMenu createOptionsMenu() {
		final JMenu options = new JMenu(OPTIONS_MENU);
		options.setMnemonic(OPTIONS_MENU.charAt(getTopLevelMnemonicIndex(OPTIONS_MENU)));
		options.add(createThicknessSubmenu());
		options.addSeparator();
		options.add(createPrimaryColorMenuItem());
		options.add(createSecondaryColorMenuItem());
		options.add(createFillColorMenuItem());
		options.add(createBackgroundColorMenuItem());
		options.addSeparator();
		final JMenuItem clear = createClearMenuItem();
		options.addMenuListener(new OptionsMenuListener(clear));
		options.add(clear);
		return options;
	}
	
	/**
	 * Creates the Thickness sub-menu to be added to this PowerPaintFrame's Options Menu.
	 * Added to this sub-menu is a JSlider to control the thickness of PaintTool 
	 * drawings. This JSlider's maximum, minimum, and initial values are initialized in 
	 * alignment with the constraints defined by this PowerPaintFrame's DrawPanel.
	 * 
	 * @return the newly constructed JMenu with configured JSlider.
	 */
	private JMenu createThicknessSubmenu() {
		final JMenu thickness = new JMenu(OPTIONS_THICKNESS);
		final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 
										  THICKNESS_MIN, THICKNESS_MAX, THICKNESS_START);
		slider.addChangeListener((theEvent) -> 
		                          myDrawPanel.setStrokeWidth(slider.getValue()));
		slider.setMinorTickSpacing(THICKNESS_MIN_TICK);
		slider.setMajorTickSpacing(THICKNESS_MAJ_TICK);
		slider.setPaintLabels(true);
        slider.setPaintTicks(true);
		thickness.add(slider);
		thickness.setMnemonic(OPTIONS_THICKNESS.
				              charAt(getLowLevelMnemonicIndex(OPTIONS_THICKNESS)));
		return thickness;
	}
	
	/**
	 * Creates the Primary Color JMenuItem to be added to this PowerPaintFrame's
	 * Options Menu. The Primary Color JMenuItem is attached an ActionListener giving
	 * the user the ability to modify the Primary Color via a JColorChooserDialog.
	 * 
	 * @return the newly constructed JMenuItem with attached ActionListener.
	 */
	private JMenuItem createPrimaryColorMenuItem() {
		final ColorIcon cI = new ColorIcon(myDrawPanel.getPrimaryColor());
		final JMenuItem pC = new JMenuItem(OPTIONS_PRIMARY, cI);
		pC.addActionListener((theEvent) -> {
			final Color col = JColorChooser.showDialog(this, PRIMARY_POPUP, 
                                                       myDrawPanel.getPrimaryColor());
			if (col != null) {
				myDrawPanel.setPrimaryColor(col);
				cI.setColor(myDrawPanel.getPrimaryColor());
			}
		});
		pC.setMnemonic(OPTIONS_PRIMARY.
				                      charAt(getLowLevelMnemonicIndex(OPTIONS_PRIMARY)));
		return pC;
	}
	
	/**
	 * Creates the Secondary Color JMenuItem to be added to this PowerPaintFrame's
	 * Options Menu. The Secondary Color JMenuItem is attached an ActionListener giving
	 * the user the ability to modify the Secondary Color via a JColorChooserDialog.
	 * 
	 * @return the newly constructed JMenuItem with attached ActionListener.
	 */
	private JMenuItem createSecondaryColorMenuItem() {
		final ColorIcon cI = new ColorIcon(myDrawPanel.getSecondaryColor());
		final JMenuItem sC = new JMenuItem(OPTIONS_SECONDARY, cI);
		sC.addActionListener((theEvent) -> {
			final Color col = JColorChooser.showDialog(this, SECONDARY_POPUP, 
                                                       myDrawPanel.getSecondaryColor());
			if (col != null) {
				myDrawPanel.setSecondaryColor(col);
				cI.setColor(myDrawPanel.getSecondaryColor());
			}
		});
		sC.setMnemonic(OPTIONS_SECONDARY.
				                    charAt(getLowLevelMnemonicIndex(OPTIONS_SECONDARY)));
		return sC;
	}
	
	/**
	 * Creates the Fill Color JMenuItem to be added to this PowerPaintFrame's
	 * Options Menu. The Fill Color JMenuItem is attached an ActionListener giving
	 * the user the ability to modify the Fill Color via a JColorChooserDialog.
	 * 
	 * @return the newly constructed JMenuItem with attached ActionListener.
	 */
	private JMenuItem createFillColorMenuItem() {
		final ColorIcon cI = new ColorIcon(myDrawPanel.getFillColor());
		final JMenuItem fC = new JMenuItem(OPTIONS_FILL, cI);
		fC.addActionListener((theEvent) -> {
			final Color col = JColorChooser.showDialog(this, FILL_POPUP, 
                                                       myDrawPanel.getFillColor());
			if (col != null) {
				myDrawPanel.setFillColor(col);
				cI.setColor(myDrawPanel.getFillColor());
			}
		});
		fC.setMnemonic(OPTIONS_FILL.charAt(getLowLevelMnemonicIndex(OPTIONS_FILL)));
		return fC;
	}
	
	/**
	 * Creates the Background Color JMenuItem to be added to this PowerPaintFrame's
	 * Options Menu. The Background Color JMenuItem is attached an ActionListener giving
	 * the user the ability to modify the Background Color via a JColorChooserDialog.
	 * 
	 * @return the newly constructed JMenuItem with attached ActionListener.
	 */
	private JMenuItem createBackgroundColorMenuItem() {
		final ColorIcon cI = new ColorIcon(myDrawPanel.getBackground());
		final JMenuItem bC = new JMenuItem(OPTIONS_BACKGROUND, cI);
		bC.addActionListener((theEvent) -> {
			final Color col = JColorChooser.showDialog(this, BACKGROUND_POPUP, 
                                                       myDrawPanel.getBackground());
			if (col != null) {
				myDrawPanel.setBackground(col);
				cI.setColor(myDrawPanel.getBackground());
			}
		});
		bC.setMnemonic(OPTIONS_BACKGROUND.
				                   charAt(getLowLevelMnemonicIndex(OPTIONS_BACKGROUND)));
		return bC;
	}
	
	/**
	 * Create the Clear JMenuItem to be added to this PowerPaintFrame's Options Menu.
	 * The Clear JMenuItem is attached an ActionListener giving the user the ability
	 * to clear all drawn Shapes from this PowerPaintFrame's DrawPanel. 
	 * 
	 * @return the newly constructed JMenuItem with attached ActionListener.
	 */
	private JMenuItem createClearMenuItem() {
		final JMenuItem clear = new JMenuItem(OPTIONS_CLEAR);
		clear.setMnemonic(OPTIONS_CLEAR.
				          charAt(getLowLevelMnemonicIndex(OPTIONS_CLEAR)));
		clear.addActionListener((theEvent) -> {
			if (generateWarning() == JOptionPane.YES_OPTION) {
				myDrawPanel.clearShapes();
			}
		});
		return clear;
	}
	
	/**
	 * Creates the Tools Menu to be added to this PowerPaintFrame's JMenuBar,
	 * adding JRadioButtonMenuItems linked to the individual PaintToolActions 
	 * of this PowerPaintFrame.
	 * 
	 * @return the newly constructed JMenu with configured JRadioButtonMenuItems.
	 */
	private JMenu createToolsMenu() {
		final JMenu tools = new JMenu(TOOLS_MENU);
		final ButtonGroup bGroup = new ButtonGroup();
		for (final PaintToolAction action : myToolActions) {
			final JRadioButtonMenuItem button = new JRadioButtonMenuItem(action);
			bGroup.add(button);
			tools.add(button);
		}
		tools.setMnemonic(TOOLS_MENU.charAt(getTopLevelMnemonicIndex(TOOLS_MENU)));
		return tools;
	}
	
	/**
	 * Creates the Help JMenu to be added to this PowerPaintFrame's JMenuBar.
 	 * The Help JMenu contains a JMenuItem named 'About...' with an attached 
 	 * ActionListener to display an informational JOptionPane.
 	 * 
	 * @return the newly constructed JMenu with attached JMenuItem and ActionListener.
	 */
	private JMenu createHelpMenu() {
		final JMenu help = new JMenu(HELP_MENU);
		help.setMnemonic(HELP_MENU.charAt(getTopLevelMnemonicIndex(HELP_MENU)));
		final JMenuItem about = new JMenuItem(HELP_ABOUT);
		about.setMnemonic(HELP_ABOUT.charAt(getLowLevelMnemonicIndex(HELP_ABOUT)));
		about.addActionListener((theEvent) -> generatePopUp());
		help.add(about);
		return help;
	}
	
	/**
	 * Creates the Edit JMenu to be added to this PowerPaintFrame's JMenuBar.
 	 * The Edit JMenu contains JMenuItems 'Undo' and 'Redo' with attached
 	 * ActionListeners giving the user the ability to undo and redo the most 
 	 * recently drawn Shape on this PowerPaintFrame's DrawPanel.
 	 * 
	 * @return the newly constructed JMenu with attached JMenuItems and
	 * ActionListeners. 
	 */
	private JMenu createEditMenu() {
		final JMenu edit = new JMenu(EDIT_MENU);
		edit.setMnemonic(EDIT_MENU.charAt(getTopLevelMnemonicIndex(EDIT_MENU)));
		final JMenuItem undo = new JMenuItem(EDIT_UNDO, new ImageIcon(UNDO_FILE));
		undo.addActionListener((theEvent) -> myDrawPanel.undo());
		undo.setMnemonic(EDIT_UNDO.charAt(getLowLevelMnemonicIndex(EDIT_UNDO)));
		undo.setAccelerator(KeyStroke.
			                       getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		final JMenuItem redo = new JMenuItem(EDIT_REDO, new ImageIcon(REDO_FILE));
		redo.addActionListener((theEvent) -> myDrawPanel.redo());
		redo.setMnemonic(EDIT_REDO.charAt(getLowLevelMnemonicIndex(EDIT_REDO)));
		redo.setAccelerator(KeyStroke.
                                   getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		edit.addMenuListener(new EditMenuListener(undo, redo));
		edit.add(undo);
		edit.addSeparator();
		edit.add(redo);
		return edit;
	}
	
	/**
	 * Creates the File JMenu to be added to this PowerPaintFrame's JMenuBar.
 	 * The File JMenu contains JMenuItems 'Save...' and 'Load...' with attached
 	 * ActionListeners giving the user the ability to save the shapes currently
 	 * drawn on this PowerPaintFrame's DrawPanel or load Shapes from a 
 	 * previously saved DrawPanel onto this PowerPaintFrame's DrawPanel.
 	 * 
	 * @return the newly constructed JMenu with attached JMenuItems and
	 * ActionListeners. 
	 */
	private JMenu createFileMenu() {
		final JMenu file = new JMenu(FILE_MENU);
		file.setMnemonic(FILE_MENU.charAt(getTopLevelMnemonicIndex(FILE_MENU)));
		final JMenuItem save = new JMenuItem(FILE_SAVE);
		save.addActionListener((theEvent) -> saveToFile());
		save.setMnemonic(FILE_SAVE.charAt(getLowLevelMnemonicIndex(FILE_SAVE)));
		save.setAccelerator(KeyStroke.
                                   getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		final JMenuItem load = new JMenuItem(FILE_LOAD);
		load.addActionListener((theEvent) -> loadFromFile());
		load.setMnemonic(FILE_LOAD.charAt(getLowLevelMnemonicIndex(FILE_LOAD)));
		file.add(save);
		file.addSeparator();
		file.add(load);
		return file;
	}
	
	/**
	 * Generates and displays the informational JOptionPane when the user interacts with
	 * the About... JMenuItem within this PowerPaintFrame's Help JMenu.
	 */
	private void generatePopUp() {
		final Icon painting = new ImageIcon(PALETTE_FILE);
		final Image image =
                ((ImageIcon) painting).getImage().
                       getScaledInstance(SCALING_POPUP, -1, java.awt.Image.SCALE_SMOOTH);
		JOptionPane.showMessageDialog(myDrawPanel, createAboutMessage(), 
				           ABOUT_POPUP, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
	}
	
	/**
	 * Generates and displays a warning JOptionPane when the user interacts with
	 * the Clear JMenuItem within this PowerPaintFrame's Options JMenu. This JOptionPane
	 * serves as an additional layer of security in the event that the user inadvertently
	 * selects the Clear JMenuItem.
	 */
	private int generateWarning() {
		return JOptionPane.showConfirmDialog(myDrawPanel, CLEAR_WARNING, 
				                             CLEAR_POPUP,
				                             JOptionPane.YES_NO_OPTION,
				                             JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Creates the message displayed on the informational JOptionPane when the user
	 * interacts with the About... JMenuItem within this PowerPaintFrame's Help JMenu.
	 * @return
	 */
	private String createAboutMessage() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Parker Rosengreen\n");
		sb.append("Autumn 2020\n");
		sb.append("TCSS 305 Assignment 4");
		return sb.toString();
	}
	
	/**
	 * Creates the Fill JCheckBox to be added to this PowerPaintFrame's JMenuBar.
	 * Attached to the Fill JCheckBox is an ActionListener giving the user the
	 * ability to enable and disable the filling of Shapes drawn on this
	 * PowerPaintFrame's DrawPanel in a selected Color. This JCheckBox is initially
	 * disabled as specified by the initial conditions of this PowerPaintFrame's
	 * DrawPanel.
	 * 
	 * @return the newly constructed JCheckBox with configured ActionListener.
	 */
	private JCheckBox createFillCheckBox() {
		final JCheckBox jcb = new JCheckBox(FILL_CHECKBOX, false);
		jcb.addActionListener((theEvent) -> {
			if (jcb.isSelected()) {
				myDrawPanel.manageFill(true);
			} else {
				myDrawPanel.manageFill(false);
			}
		});
		jcb.setMnemonic(FILL_CHECKBOX.charAt(getTopLevelMnemonicIndex(FILL_CHECKBOX)));
		return jcb;
	}
	
	/**
	 * Saves the list of currently drawn on this PowerPaintFrame's DrawPanel to a
	 * file and directory specified by the user.
	 */
	private void saveToFile() {
		final FileDialog fd = new FileDialog(this, FILE_SAVE_POPUP, FileDialog.SAVE);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			final String fileName = fd.getFile() + ".shp";
			try {
				final File f = new File(fd.getDirectory(), fileName);
				final FileOutputStream file = new FileOutputStream(f);
				final ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(myDrawPanel.getDrawnShapes());
				out.close();
				file.close();
			} catch (IOException ex) {
				showSaveError(ex.getClass().getSimpleName(), fileName);
			}
		}
	}
	
	/**
	 * Loads all Shapes currently drawn on a previously saved Shape list from a
	 * file and directory specified by the user.
	 */
	private void loadFromFile() {
		final FileDialog fd = new FileDialog(this, FILE_LOAD_POPUP, FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() != null) {
			try {
				final File f = new File(fd.getDirectory(), fd.getFile());
				final FileInputStream file = new FileInputStream(f);
				final ObjectInputStream in = new ObjectInputStream(file);
				final Object obj = in.readObject();
				myDrawPanel.addSavedShapes((List<ShapeAttributeContainer>) obj);
				in.close();
				file.close();
			} catch (Exception ex) {
				showLoadError(ex.getClass().getSimpleName(), fd.getFile());
			}
		}
	}
	
	/**
	 * Displays an error JOptionPane when an exception is thrown during the process of
	 * saving the list of Shapes drawn on this PowerPaintFrame's DrawPanel.
	 * 
	 * @param theExceptionName is the thrown IOException.
	 * @param theFileName is the name of the file associated with the thrown IOException.
	 */
	private void showSaveError(final String theExceptionName, final String theFileName) {
		final String message = FILE_SAVE_ERROR + "\"" + theFileName + "\"" + "\n" + 
	                                                    SOURCE + theExceptionName;
		JOptionPane.showConfirmDialog(this, message, SAVE_ERROR, 
				                                     JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Displays an error JOptionPane when an exception is thrown during the process of
	 * loading a saved list of Shapes drawn on a previous DrawPanel.
	 * 
	 * @param theExceptionName is the thrown Exception.
	 * @param theFileName is the name of the file associated with the thrown Exception.
	 */
	private void showLoadError(final String theExceptionName, final String theFileName) {
		final String message = FILE_LOAD_ERROR + "\"" + theFileName + "\"" + "\n" + 
                                                        SOURCE + theExceptionName;
		JOptionPane.showMessageDialog(this, message, LOAD_ERROR, 
				                                     JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Determines the appropriate mnemonic index of a provided top-level JMenu
	 * or JButton name and prevents the possibility of shared mnemonic characters.
	 * 
	 * @param theName is the name of the top-level JMenu or JButton.
	 * @return the index corresponding to the unique mnemonic character.
	 */
	private int getTopLevelMnemonicIndex(final String theName) {
		int index = 0;
		while (myTopMnemonics.contains(theName.toLowerCase().charAt(index))) {
			index++;
		}
		myTopMnemonics.add(theName.toLowerCase().charAt(index));
		return index;
	}
	
	/**
	 * Determines the appropriate mnemonic index of a provided top-level JMenu
	 * or JButton name and prevents the possibility of shared mnemonic characters.
	 * 
	 * @param theName is the name of the top-level JMenu or JButton.
	 * @return the index corresponding to the unique mnemonic character.
	 */
	private int getLowLevelMnemonicIndex(final String theName) {
		int index = 0;
		while (mySubMnemonics.contains(theName.toLowerCase().charAt(index))) {
			index++;
		}
		mySubMnemonics.add(theName.toLowerCase().charAt(index));
		return index;
	}

/**
 * A PaintToolAction class to handle all events associated with and configure
 * the PaintTools offered by this PowerPaintFrame. 
 * 
 * @author Parker Rosengreen
 * @version 10 December 2020
 */
private class PaintToolAction extends AbstractAction {
	
	/** A generated serial version ID. */
	private static final long serialVersionUID = 2479526143007360683L;
	
	/** This PaintToolAction's PaintTool. */
	private final PaintTool myPaintTool;

	/**
	 * Constructs a new PaintToolAction with the provided PaintTool name, Icon, and
	 * PaintTool itself.
	 * 
	 * @param theName is the PaintTool's basic name.
	 * @param theIcon is the PaintTool's Icon.
	 * @param theTool is the PaintTool.
	 */
	private PaintToolAction(final String theName, final Icon theIcon,
											  final PaintTool theTool) {
		super(theName);
        putValue(Action.LARGE_ICON_KEY, theIcon);
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.
                		 charAt(getTopLevelMnemonicIndex(theName))));
        putValue(Action.SELECTED_KEY, false);
        myPaintTool = theTool;
	}
	
	/**
	 * Sets the currently selected PaintTool of this PowerPaintFrame's DrawPanel
	 * to this PaintToolAction's PaintTool. If this PaintToolAction's PaintTool is
	 * not erasable, then this PowerPaintFrame's DrawPanel's 'erase' mode is enabled.
	 * Otherwise, this PowerPaintFrame's DrawPanel's 'erase' mode is disabled.
	 * 
	 * @param theEvent is the ActionEvent - unused.
	 */
	@Override
	public final void actionPerformed(final ActionEvent theEvent) {
		myDrawPanel.setCurrentTool(myPaintTool);
		myDrawPanel.setErase(!myPaintTool.isErasable());
	}
}

/**
 * An EditMenuListener class to appropriately enable/disable the Undo and Redo
 * JMenuItems within the Edit JMenu when the Edit JMenu is selected by the user.
 * 
 * @author Parker Rosengreen
 * @version 10 December 2020
 */
private class EditMenuListener implements MenuListener {
	
	/** The Undo JMenuItem associated with this EditMenuListener. */
	private final JMenuItem myUndo;
	
	/** The Redo JMenuItem associated with this EditMenuListener. */
	private final JMenuItem myRedo;
	
	/**
	 * Constructs a new EditMenuListener with the provided Undo and Redo JMenuItems.
	 * 
	 * @param theUndo is the Undo JMenuItem associated with this PowerPaintFrame's 
	 * Edit JMenu.
	 * @param theRedo is the Redo JMenuItem associated with this PowerPaintFrame's 
	 * Edit JMenu.
	 */
	private EditMenuListener(final JMenuItem theUndo, final JMenuItem theRedo) {
		myUndo = theUndo;
		myRedo = theRedo;
	}

	/**
	 * Assigns the appropriate enabled/disabled values to this EditMenuListener's Undo 
	 * and Redo JMenuItems according to whether or not Shapes have been drawn on this
	 * PowerPaintFrame's DrawPanel and if one or more Shapes have been undone and can 
	 * be redrawn, respectively.
	 * 
	 * @param theEvent is the fired MenuEvent - unused.
	 */
	@Override
	public void menuSelected(final MenuEvent theEvent) {
		if (myDrawPanel.areShapesDrawn()) {
			myUndo.setEnabled(true);
		} else {
			myUndo.setEnabled(false);
		}
		if (myDrawPanel.isRedoAvailable()) {
			myRedo.setEnabled(true);
		} else {
			myRedo.setEnabled(false);
		}
	}

	/**
	 * Enables both the Undo and Redo JMenuItems when the user deselects the Edit JMenu.
	 * This prevents the possibility of a failure to undo/redo properly if the Edit
	 * JMenu is deselected while either or the Undo/Redo JMenuItems are 
	 * initially disabled.
	 * 
	 * @param theEvent is the MenuEvent - unused.
	 */
	@Override
	public void menuDeselected(final MenuEvent theEvent) {
		myUndo.setEnabled(true);
		myRedo.setEnabled(true);
	}

	/**
	 * Called when the user cancels the Edit JMenu associated with this EditMenuListener.
	 * This method is unused by the PowerPaintFrame.java program.
	 * 
	 * @param theEvent is the MenuEvent - unused.
	 */
	@Override
	public void menuCanceled(final MenuEvent theEvent) {}
}

/**
 * An OptionsMenuListener class to appropriately enable/disable the Clear
 * JMenuItem within the Options JMenu when the Options JMenu is selected by the user.
 * 
 * @author Parker Rosengreen
 * @version 10 December 2020
 */
private class OptionsMenuListener implements MenuListener {
	
	/** The Clear JMenuItem associated with this OptionsMenuListener. */
	private final JMenuItem myClear;
	
	/**
	 * Constructs a new OptionsMenuListener with the provided Clear JMenuItem.
	 * 
	 * @param theClear is the Clear JMenuItem associated with this PowerPaintFrame's
	 * Options JMenu.
	 */
	private OptionsMenuListener(final JMenuItem theClear) {
		myClear = theClear;
	}

	/**
	 * Assigns the appropriate enabled/disabled values to this OptionsMenuListener's
	 * Clear JMenuItem according to whether or not Shapes have been drawn on this
	 * PowerPaintFrame's DrawPanel.
	 * 
	 * @param theEvent is the fired MenuEvent - unused.
	 */
	@Override
	public void menuSelected(final MenuEvent theEvent) {
		if (myDrawPanel.areShapesDrawn()) {
			myClear.setEnabled(true);
		} else {
			myClear.setEnabled(false);
		}
	}

	/**
	 * Called when the user deselects the Options JMenu associated with this
	 * OptionsMenuListener. This method is unused by the PowerPaintFrame.java program.
	 * 
	 * @param theEvent is the MenuEvent - unused.
	 */
	@Override
	public void menuDeselected(final MenuEvent theEvent) {}

	/**
	 * Called when the user cancels the Options JMenu associated with this
	 * OptionsMenuListener. This method is unused by the PowerPaintFrame.java program.
	 * 
	 * @param theEvent is the MenuEvent - unused.
	 */
	@Override
	public void menuCanceled(final MenuEvent theEvent) {}	
}

}
