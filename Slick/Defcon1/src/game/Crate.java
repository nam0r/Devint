package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import actors.PhysicalEntity;

/**
 * A simple crate showing a default physics body being used. This entity
 * doesn't have the "special" rules applied to it that an Actor does.
 * 
 * @author kevin
 */
public class Crate extends PhysicalEntity {
	
	/**
	 * Create a new crate
	 * 
	 * @param x The x position of the centre of the crate
	 * @param y The y position fo the centre of the crate
	 * @param width The width of the crate 
	 * @param height The height of the crate
	 * @param mass The mass of the crate
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Crate(float x, float y, float width, float height, float mass) {
		super("res/crate.png", x, y, width, height, mass);
		
		body.setFriction(0.1f);
	}

	public void preUpdate(int delta) {}

	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
		image.draw(-width/2,-height/2,width,height);
		g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
		g.translate(-getX(), -getY());
	}

	public void update(int delta) {}
}
