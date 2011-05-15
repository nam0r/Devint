package game;


import java.io.IOException;

import net.phys2d.raw.shapes.DynamicShape;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.StateBasedGame;

import utils.Conf;
import actors.PhysicalEntity;

/**
 * An Emitter object
 */
public class Emitter extends PhysicalEntity {
	/** The particle system */
	protected ParticleSystem particle;
	/** The number of emitters */
	protected int emitterNumber;
	/** The y offset of the emitter */
	protected float yOffset;
	/** The type of this emitter */
	protected String type;
	
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
	public Emitter(String emitterPath, String type, float yOffset, int emitterNumber, float x, float y, float mass, DynamicShape shape) {
		super(Conf.IMG_PATH+"transparent.png", x, y, mass, shape);
		this.emitterNumber = emitterNumber;
		this.yOffset = yOffset;
		this.type = type;
		body.setFriction(0.1f);
		
		try {
			particle = ParticleIO.loadConfiguredSystem(emitterPath);			
		} catch (IOException e) {
			System.err.println("Couldn't load particle "+emitterPath);
		}
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
	public Emitter(String emitterPath, String type, float yOffset, int emitterNumber, float x, float y, float width, float height, float mass) {
		super(Conf.IMG_PATH+"transparent.png", x, y, width, height, mass);
		this.emitterNumber = emitterNumber;
		this.yOffset = yOffset;
		this.type = type;
		body.setFriction(0.1f);
		try {
			particle = ParticleIO.loadConfiguredSystem(emitterPath);			
		} catch (IOException e) {
			System.err.println("Couldn't load particle "+emitterPath);
		}
	}

	public void preUpdate(int delta) {}

	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
		//image.draw(-width/2,-height/2,width,height);
		g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
		g.translate(-getX(), -getY());
		for(int i=0; i<emitterNumber; i++){
			((ConfigurableEmitter) particle.getEmitter(i)).setPosition(getX(), (getY()+yOffset));
		}
		particle.render();
	}

	public void update(int delta) {
		particle.update(delta);
	}
	
	/**
	 * Returns the string type of this emitter
	 * @return type the type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Makes additional actions when collision with the player
	 */
	public void onCollision(StateBasedGame sbg) {
		System.out.println("collision with spirit");
	}
}
