package map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import actors.Actor;
import actors.PhysicalEntity;
import environment.TileEnvironment;

public class Map {
	
	private Image background;
	private TileSet set;
	private MapLoader loader;
	private TileEnvironment env;
	
	/** The view x-axis offset */
	private float xoffset;
	/** The view y-axis offset */
	private float yoffset;
	
	private boolean showBounds;
	
	public Map(String pathToBackground, String pathToTilesDefinitions, String pathToMap, 
			int tilesWidth, int tilesHeight) {
		try {
			background = new Image(pathToBackground);
		} catch (SlickException e) {
			System.err.println("Couldn't load the background of the map : " + pathToBackground);
		}
		
		showBounds = false;
		
		set = new TileSet(pathToTilesDefinitions);
		loader = new MapLoader(set);
		env = loader.load(pathToMap);
		
		env.setImageSize(tilesWidth,tilesHeight);
	}
	
	public Image getBackground() {
		return this.background;
	}
	
	public void showBounds() {
		showBounds = !showBounds;
	}
	
	/* ********************* *
	 * Appel aux methodes de *
	 *    l'environnement    *
	 * ********************* */
	public void init() {
		env.init();
	}
	
	public void addEntity(PhysicalEntity entity) {
		env.addEntity(entity);
	}
	
	public void render(Graphics g, GameContainer gc) {
		float width = gc.getWidth();
		float height = gc.getHeight();
		float backPar = 3f;
		float bx = ((-xoffset * backPar) % width) / -width;
		float by = ((-yoffset * backPar) % height) / -height;
		
		background.bind();
		g.setColor(Color.white);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(bx,by);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(bx+3,by);
			GL11.glVertex2f(width,0);
			GL11.glTexCoord2f(bx+3,by+3);
			GL11.glVertex2f(width,height);
			GL11.glTexCoord2f(bx,by+3);
			GL11.glVertex2f(0,height);
		GL11.glEnd();
		
		g.translate(-(int) xoffset, -(int) yoffset);
		
		env.render(g);
		
		if (showBounds) {
			env.renderBounds(g);
		}
		
		g.translate((int) xoffset, (int) yoffset);
	}
	
	public void update(int delta, GameContainer gc, Actor player) {
		env.update(delta);
		
		// calculate screen position clamping to the bounds of the level
		xoffset = player.getX() - gc.getWidth() / 2;
		yoffset = player.getY() - gc.getHeight() / 2;
		
		Rectangle bounds = env.getBounds();
		if (xoffset < bounds.getX()) {
			xoffset = bounds.getX();
		}
		if (yoffset < bounds.getY()) {
			yoffset = bounds.getY();
		}
		
		if (xoffset > (bounds.getX() + bounds.getWidth()) - gc.getWidth()) {
			xoffset = (bounds.getX() + bounds.getWidth()) - gc.getWidth();
		}
		if (yoffset > (bounds.getY() + bounds.getHeight()) - gc.getHeight()) {
			yoffset = (bounds.getY() + bounds.getHeight()) - gc.getHeight();
		}
	}
	
	public Rectangle getBounds() {
		return env.getBounds();
	}

}