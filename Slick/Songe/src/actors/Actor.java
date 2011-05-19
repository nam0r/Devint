package actors;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import utils.Conf;


/**
 * 
 */
public abstract class Actor extends PhysicalEntity {
	/** The maximum velocity an actor can jump at - this is used to prevent some odd effects of a penetration based physics engine */
	protected int MAX_JUMP_VEL = 100;
	
	protected final int WAITING_TIME = 500;
	
	/** True if the actor is currently resting on the ground */
	protected boolean onGround = false;
	/** The amount of time the actor has been considered to be off the ground */
	protected int offGroundTimer = 0;
	/** The amount of time during which the actor hasn't moved */
	protected int waitingTimer = 0;
	/** True if the actor has jumped and hence are in the air */
	protected boolean jumped = false;
	/** True if the actor is facing right */
	protected boolean facingRight = true;
	/** True if the actor is moving - i.e. left or right */
	protected boolean moving = false;
	/** True if the actor is in the process of falling down */
	protected boolean falling = false;
	
	/** The x component of the velocity for the current update */
	protected float velx;
	/** The time the actor should be out of ground to be considered as jumping */
	protected int jumpTime = 200;
	
	/** The current frame of animation */
	protected int frame;
	/** The timer controlling frame change */
	protected int frameTimer = 0;
	/** The interval between animation frame change in milliseconds */
	protected int frameInterval = 100;
	/** Indicates if player is really on the ground */
	protected boolean on;
	/** indicates if the player faces to a wall to 1/3 of his body */
	protected boolean faceToWall;
	/** indicates if the player faces to a wall totally */
	protected boolean totalFaceToWall;
	/** indicates if the player has collided with something higher */
	protected boolean topCollided;
	
	
	// the forces applied for different actions. The move force is applied over and
	// over so is reasonably small. The jump force is a one shot deal and so is reasonably
	// big
	protected float moveForce = 200;
	protected float jumpForce = 40000;
	
	/*
	 * ATTENTION : la largeur et la hauteur passés en parametres sont les dimensions
	 * de la boite dans lequel est contenu le corps du personnage (utilisee pour les collisions).
	 * 
	 * La taille de l'image representant le personnage ne depend pas de ces dimensions. Elle
	 * est geree dans la classe derivee. (On pourrait d'ailleurs la gerer dans cette classe-ci).
	 */
	/**
	 * Create a new actor
	 * 
	 * @param x The x coordinate of the actor's position
	 * @param y The y coordinate of the actor's position
	 * @param mass The mass of the actor's physics body
	 * @param size The size of the actor's collision bounds
	 */
	public Actor(String pathToSpriteSheet, float x, float y, float mass, float width, float height) {
		super(pathToSpriteSheet, x, y, width, height, mass);
		
		body.setUserData(this);
		body.setRestitution(0);
		body.setFriction(0f);
		body.setMaxVelocity(40, 100);
		body.setRotatable(false);
		setPosition(x,y);
	}
	
	protected SpriteSheet getSpriteSheet(int y, int tw, int th) {
		Image im = image.getSubImage(0, y, image.getWidth(), image.getHeight());
		return new SpriteSheet(im,tw,th);
	}
	
	/**
	 * Apply force to the actor. This should be used to move the actor around the level
	 * 
	 * @param x The x component of the force to apply
	 * @param y The y component of the force to apply
	 */
	public void applyForce(float x, float y) {
		body.addForce(new Vector2f(x,y));
		
		// if the force applied is up into the air the actor is
		// considered to be jumping
		if (y < 0) {
			jumped = true;
		}
		
		// if the actor has just changed direction kill the x velocity
		// cause thats what happens in platformers
		if (x > 0) {
			if (!facingRight) {
				setVelocity(0, getVelY());
			}
			facingRight = true;
		}
		if (x < 0) {
			if (facingRight) {
				setVelocity(0, getVelY());
			}
			facingRight = false;
		}
	}
	
	/**
	 * Indicate whether this actor is being moved
	 * 
	 * @param moving True if this actor is being moved
	 */
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	/**
	 * @see org.newdawn.AbstractEntity.Entity#getBody()
	 */
	public Body getBody() {
		return body;
	}
	
	/**
	 * Check if this actor is facing right
	 * 
	 * @return True if the actor is facing right
	 */
	public boolean facingRight() {
		return facingRight;
	}
	
	/**
	 * Check if the actor is falling down the screen
	 * 
	 * @return True if the actor is falling down the screen
	 */
	public boolean falling() {
		return falling;
	}
	
	/**
	 * Check if the actor is being moved 
	 * 
	 * @return True if the actor is being moved
	 */
	public boolean moving() {
		return moving;
	}
	
	/**
	 * Check if the actor is jumping
	 * 
	 * @return True if the actor is jumping
	 */
	public boolean jumping() {
		return jumped;
	}
	
	/**
	 * Check if the actor is really on the ground
	 * 
	 * @return True if the actor is jumping
	 */
	public boolean on() {
		return on;
	}
	
	/**
	 * Check if the actor is waiting
	 * 
	 * @return True if the actor is waiting
	 */
	public boolean waiting() {
		return waitingTimer > WAITING_TIME;
	}
	
	/**
	 * @see org.newdawn.AbstractEntity.Entity#preUpdate(int)
	 */
	public void preUpdate(int delta) {
		// at the start of each frame kill the x velocity 
		// if the actor isn't being moved
		if(!moving) {
			setVelocity(0, getVelY());
		}
		
		falling = (getVelY() > 15)/* && (!onGround())*/;
		velx = getVelX();
		
		
		frameTimer -= delta;
		while (frameTimer < 0) {
			frame++;
			frameTimer += frameInterval;
		}
	}
	
	@Override
	public void update(int delta) {
		// update the flag for the actor being on the ground. The 
		// physics engine will cause constant tiny bounces as the 
		// the body tries to settle - so don't consider the body
		// to have left the ground until it's done so for some time
		
		// here we do these affectations only one time per frame because calling
		// the functions is heavy
		on = onGroundImpl(body);
		faceToWall = faceToWall(3);
		totalFaceToWall = faceToWall(1);
		topCollided = topCollided();
		
		if (!on) {
			offGroundTimer += delta;
			if (offGroundTimer > jumpTime) {
				onGround = false;
			}
			waitingTimer = 0;
		} else {
			offGroundTimer = 0;
			onGround = true;
			
			if(moving) {
				waitingTimer = 0;
			} else {
				waitingTimer += delta;
			}
		}

		// if we've been pushed back from a collision horizontally
		// then kill the velocity - don't want to keep pushing during
		// this frame
		if ((getVelX() > 0) && (!facingRight)) {
			velx = 0;
		}
		if ((getVelX() < 0) && (facingRight)) {
			velx = 0;
		}
		
		// keep velocity constant throughout the updates
		setVelocity(velx, getVelY());
		// if we're standing on the ground negate gravity. This stops
		// some instability in physics
		body.setGravityEffected(!on);
		
		// clamp y 
		if (getVelY() < -MAX_JUMP_VEL) {
			setVelocity(getVelX(), -MAX_JUMP_VEL);
		}
		
		// handle jumping as opposed to be moving up. This prevents
		// bounces on edges
		if ((!jumped) && (getVelY() < 0)) {
			setVelocity(getVelX(),getVelY() * 0.90f);
		} 
		
		if (jumped) {
			if (getVelY() >= 15) {
				jumped = false;
			}
		}
		
		if(faceToWall && ((getVelX()<3 && facingRight()) || (getVelX()>-3 && !facingRight()))) moving = false;
		
	}
	
	/**
	 * @see org.newdawn.AbstractEntity.Entity#render(org.newdawn.slick.Graphics)
	 */
	public abstract void render(Graphics g);

	/**
	 * Check if this actor is currently resting on the ground
	 * 
	 * @return True if the actor is currently resting on the ground
	 */
	public boolean onGround() {
		return onGround;
	}
	
	/**
	 * Implementation on ground check. This can be expensive so best
	 * to try and limit is use by caching
	 * 
	 * @param body The body to check
	 * @return True if the body is reseting on the ground
	 */
	protected boolean onGroundImpl(Body body) {
		if (world == null) {
			return false;
		}
		// loop through the collision events that have occurred in the
		// world
		CollisionEvent[] events = world.getContacts(body);
		
		for (int i=0;i<events.length;i++) {
			// if the point of collision was below the center of the actor
			// i.e. near the feet
			if (events[i].getPoint().getY() > getY()+(height/4)) {
				// check the normal to work out which body we care about
				// if the right body is involved and a collision has happened
				// below it then we're on the ground
				if (events[i].getNormal().getY() < -0) {
					if (events[i].getBodyB() == body) {
						return true;
					}
				}
				if (events[i].getNormal().getY() > 0) {
					if (events[i].getBodyA() == body) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * Informs on the fact the user is blocked face to a wall or not
	 * 
	 * @param factor
	 *            The factor used to define which part of the body of the actor
	 *            will be taken in account. If factor is 1 it is all the body,
	 *            if more, the height of the body is divided by the factor
	 * @return true if the user is blocked
	 */
	protected boolean faceToWall(int factor) {
		if (world == null) {
			return false;
		}
		// loop through the collision events that have occurred in the
		// world
		CollisionEvent[] events = world.getContacts(body);
		
		for (int i=0;i<events.length;i++) {
			// if the point of collision was below the center of the actor
			// i.e. near the feet
			if (events[i].getPoint().getY() < getY()+(height/factor)) {
				// if the normal force's x component is not null, then the force
				// is not horizontal and it's probably due to a contact with a
				// wall or a crate or something
				if (events[i].getNormal().getX() < -0
						|| events[i].getNormal().getX() > 0)
					return true;
			}
		}
		return false;
	}

	/**
	 * Informs on the fact the user has collided with something higher than him
	 * on the map, this happens espacially when he is jumping
	 * 
	 * @return true if the user is blocked
	 */
	protected boolean topCollided() {
		if (world == null) {
			return false;
		}
		// loop through the collision events that have occurred in the
		// world
		CollisionEvent[] events = world.getContacts(body);
		
		for (int i=0;i<events.length;i++) {
			// if the point of collision was over the center of the actor
			// i.e. near the head
			if (events[i].getPoint().getY() < getY()+(height/2)) {
				//
				if (events[i].getNormal().getY() > 0) {
					if (events[i].getBodyB() == body) {
						return true;
					}
				}
				if (events[i].getNormal().getY() < -0) {
					if (events[i].getBodyA() == body) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean isTopCollided(){
		return topCollided;
	}
	
	public boolean isFacingToWall(){
		return faceToWall;
	}
	
	public boolean isTotallyFacingToWall(){
		return totalFaceToWall;
	}
	
	public void moveLeft() {
		applyForce(-moveForce, 0);
	}
	
	public void moveRight() {
		applyForce(moveForce, 0);
	}
	
	public void jump() {
		if (onGround()) {
			applyForce(0, -jumpForce);
		}
	}
	
	public void reinitPosition(){
		body.setPosition(xInital,yInitial);
		facingRight = true;
	}
	
	/** the step rate function */
	//public abstract float stepRate();
	//public abstract String getSoundWalk();
	
	public float stepRate(){
		return 35f;
	}
	
	public String getSoundWalk(){
		return Conf.SND_DEPLACEMENT_PATH + "wooden_stairs2.ogg";
	}
	
}
