
/**
 * Cette classe contient juste le main du projet.
 */


package classesMeres;

import javax.swing.JFrame;


public class Launcher{

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MainFrame frame;
		frame = new MainFrame(true);
	
		frame.pack();
		frame.setVisible(true);
	}
}
