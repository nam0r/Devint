package actors;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.DynamicShape;

/**
 * Rassemble les proprietes communes a toutes les entites
 */
public abstract class PhysicalEntity extends AbstractMovingEntity implements PhysicalEntityInterface {

	/** Le corps physique qui represente l'entite */
	protected Body body;
	
	/** Le monde auquel appartient l'entite **/
	protected World world;
	
	protected float xInital, yInitial;
	
	/**
	 * Builds a Physical Entity
	 * @param path the image path
	 * @param x the x position
	 * @param y the y position
	 * @param width the shape width
	 * @param height the shape height
	 * @param mass the mass
	 */
	public PhysicalEntity(String path, float x, float y, float width, float height, float mass) {
		super(path);
		this.xInital = x;
		this.yInitial = y;
		this.width = width;
		this.height = height;
		
		Body b = new Body(new Box(width, height), mass);
		setBody(b,x,y);
	}
	/**
	 * Builds a Physical Entity
	 * @param path the main image path
	 * @param x the x position
	 * @param y the y position
	 * @param mass the body mass
	 * @param shape the body shape
	 */
	public PhysicalEntity(String path, float x, float y, float mass, DynamicShape shape) {
		super(path);
		this.xInital = x;
		this.yInitial = y;
		this.width = shape.getBounds().getWidth();
		this.height = shape.getBounds().getHeight();
		
		Body b = new Body(shape, mass);
		setBody(b,x,y);
	}
	
	/* ******* *
	 * Setters *
	 * ******* */
	
	@Override
	public void setWorld(World world) {
		this.world = world;
	}
	
	@Override
	public void setX(float x) {
		body.setPosition(x, this.getY());
	}

	@Override
	public void setY(float y) {
		body.setPosition(this.getX(), y);
	}
	
	@Override
	public void setPosition(float x, float y) {
		body.setPosition(x,y);
	}

	
	public void setBody(Body body, float x, float y) {
		this.body = body;
		this.body.setPosition(x, y);
	}
	
	/**
	 * Definit la velocite de l'entite
	 * 
	 * @param x La composante x de la velocite a appliquer
	 * @param y La composante y de la velocite a appliquer
	 */
	public void setVelocity(float x, float y) {
		Vector2f vec = new Vector2f(body.getVelocity());
		vec.scale(-1);
		body.adjustVelocity(vec);
		body.adjustVelocity(new Vector2f(x,y));
	}
	
	/* ******* *
	 * Getters *
	 * ******* */
	
	@Override
	public float getX() {
		return body.getPosition().getX(); // + ???
	}

	@Override
	public float getY() {
		return body.getPosition().getY(); // + ???
	}

	/**
	 * Retourne la composante x de la velocite de cette entite
	 * 
	 * @return La composante x de la velocite de cette entite
	 */
	public float getVelX() {
		return body.getVelocity().getX();
	}

	/**
	 * Retourne la composante y de la velocite de cette entite
	 * 
	 * @return La composante y de la velocite de cette entite
	 */
	public float getVelY() {
		return body.getVelocity().getY();
	}
	
	/**
	 * Retourne le corps physique de l'entite
	 * 
	 * @return Le corps physique de l'entite
	 */
	public Body getBody() {
		return body;
	}
}