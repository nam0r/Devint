package map;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import utils.Globals;
import actors.Actor;
import actors.PhysicalEntity;
import environment.TileEnvironment;

public class Map {
	
	private Image background;
	private TileSet set;
	private MapLoader loader;
	private TileEnvironment env;
	
	private Actor player;
	
	/** The view x-axis offset */
	private float xoffset;
	/** The view y-axis offset */
	private float yoffset;
	/** One of the two factors to set the behavior of the background */
	private float backPar;
	/** the other one of the two factors to set the behavior of the background */
	private float backPar2;
	
	private boolean showBounds;
	
	public Map(String pathToBackground, String pathToTilesDefinitions, String pathToMap, 
			int tilesWidth, int tilesHeight, float backPar, float backPar2) {
		this.backPar = backPar;
		this.backPar2 = backPar2;
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
	
	public void removeEntity(PhysicalEntity entity) {
		env.removeEntity(entity);
	}
	
	public void setMainPlayer(Actor p) {
		if(player != null)
			env.removeEntity(player);
		player = p;
		env.addEntity(player);
	}
	
	public void render(Graphics g, GameContainer gc) {
		float width = gc.getWidth();
		float height = gc.getHeight();
		float bx = ((-xoffset * backPar) % width) / -width;
		float by = ((-yoffset * backPar) % height) / -height;
		
		background.bind();
		g.setColor(Color.white);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(bx,by);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(bx+backPar2,by);
			GL11.glVertex2f(width,0);
			GL11.glTexCoord2f(bx+backPar2,by+backPar2);
			GL11.glVertex2f(width,height);
			GL11.glTexCoord2f(bx,by+backPar2);
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
		
		Globals.xoffset = xoffset;
		Globals.yoffset = yoffset;
	}
	
	public Rectangle getBounds() {
		return env.getBounds();
	}
	
	public World getWorld() {
		return env.getWorld();
	}
	
	public PhysicalEntity getEntityByBody(Body body) {
		return env.getEntityByBody(body);
	}
	
	public float getScreenXOffset(){
		return xoffset;
	}
	
	public float getScreenYOffset(){
		return yoffset;
	}

}