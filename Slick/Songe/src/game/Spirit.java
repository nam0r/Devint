package game;


import net.phys2d.raw.shapes.DynamicShape;
import nodes.Node;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import utils.Conf;

/**
 * A spirit
 */
public class Spirit extends Emitter {
	/** the node */
	protected Node node;
	
	/**
	 * Create a new emitter
	 * @param emitterPath the path of the xml emitter file
	 * @param yOffset the y position offset of the emitter
	 * @param emitterNumber the number of emitters
	 * @param x The x position of the centre of the emitter
	 * @param y The y position fo the centre of the emitter
	 * @param mass The mass of the emitter
	 * @param shape the shape of the object
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Spirit(String emitterPath, float yOffset, int emitterNumber, float x, float y, float mass, DynamicShape shape, Node node) {
		super(emitterPath, "spirit", yOffset, emitterNumber, x, y, mass, shape);
		this.node = node;
	}
	
	/**
	 * Create a new emitter
	 * @param emitterPath the path of the xml emitter file
	 * @param yOffset the y position offset of the emitter
	 * @param emitterNumber the number of emitters
	 * @param x The x position of the centre of the emitter
	 * @param y The y position fo the centre of the emitter
	 * @param width The width of the emitter 
	 * @param height The height of the emitter
	 * @param mass The mass of the emitter
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Spirit(String emitterPath, String type, float yOffset, int emitterNumber, float x, float y, float width, float height, float mass , Node node) {
		super(Conf.IMG_PATH+"transparent.png", "spirit",yOffset, emitterNumber, x, y, width, height, mass);
		this.node = node;
	}

	public void render(Graphics g) {
		super.render(g);
	}

	public void update(int delta) {
		super.update(delta);
	}
	
	/**
	 * Makes additional actions when collision with the player
	 */
	@Override
	public void onCollision() {
		System.out.println("collision with spirit");
	}
}
