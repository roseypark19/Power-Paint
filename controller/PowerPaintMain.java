/*
 * TCSS 305 - Fall 2020
 * 
 * A PowerPaintMain class to instantiate and display a new PowerPaintFrame.
 */

package controller;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.PowerPaintFrame;

/**
 * This program creates and displays a new PowerPaintFrame.
 * 
 * @author Parker Rosengreen
 * @version 14 December 2020
 */
public final class PowerPaintMain {
	
	/**
	 * The starting point of the Power Paint program. Configures the LookAndFeel
	 * and instantiates a new PowerPaintFrame. 
	 * 
	 * @param theArgs are command line arguments - unused.
	 */
	public final static void main(final String[] theArgs) {
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
        	/**
        	 * Instantiates and displays a new PowerPaintFrame.
        	 */
        	@Override
            public final void run() {
                final PowerPaintFrame mainFrame = new PowerPaintFrame();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        });
	}
}
