package utils;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

/**
 * General Utilities
 * 
 * @author Afnarel
 */
public class Utils {

	/**
	 * Draw a centered line
	 * 
	 * @param gfx
	 * @param str
	 * @param y
	 * @param c
	 */
	public static void drawCenteredString(Graphics gfx, String str, int width, int y, Color c) {
		int x = (width - gfx.getFont().getWidth(str)) / 2;
		
		Utils.drawString(gfx, str, x, y, c);
	}
	
	public static void drawCenteredString(Graphics gfx, String str, int width, int y, Color c, int size) {
		// Font
		Font f = new Font("Verdana", Font.BOLD, 40);
		UnicodeFont font = new UnicodeFont(f);
		int x = (width - font.getWidth(str)) / 2;
		
		TrueTypeFont ttf = new TrueTypeFont(f,true);
		ttf.drawString(x, y, "Menu", c);
	}
	
	public static void drawString(Graphics gfx, String str, int x, int y, Color c) {
		Color cTemp = gfx.getColor();
		gfx.setColor(c);
		gfx.drawString(str, x, y);
		gfx.setColor(cTemp);
	}
	
}
