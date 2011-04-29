package actors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import utils.Globals;

public abstract class IA extends Actor {

	protected SpriteSheet walk;
	protected int nb_sprites;
	
	private enum Way {RIGHT, LEFT};
	private Way way;
	
	private int walkingTimer;
	protected int walkingTime;
	
	protected boolean visited;
	/** indicates if the IA has been far */
	protected boolean hasBeenFar;
	
	public IA(String pathToSpriteSheet, int nb_sprites, float x, float y, float width, float height, float mass) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		
		this.nb_sprites = nb_sprites;
		walk = new SpriteSheet(image,(int)width,(int)height); // A revoir
		
		way = Way.LEFT;
		walkingTimer = 0;
		
		moveForce = 400;
		
		visited = false;
		walkingTime = 1000; //1 sec
		hasBeenFar = true;
	}

	@Override
	public void render(Graphics g) {
		
		int sx = 0;
		int sy = 0;
		
		if (moving() && onGround()) {
			sx = frame % nb_sprites;
			sy = 0;
 		} else if (onGround()) {
			sx = 0;
			sy = 0;
		}
		
		// get the appropriate sprite 
		Image image = walk.getSprite(sx,sy);
		
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		image.draw(getX()-width/2, getY()-height/2, width, height);
		
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
		setMoving(true);
		
		walkingTimer += delta;
		
		if(walkingTimer > walkingTime) {
			walkingTimer = 0;
			way = (way == Way.LEFT) ? Way.RIGHT : Way.LEFT; // On change de sens
		}
		
		if (way == Way.LEFT)
			moveLeft();
		else
			moveRight();
		
		// if is no more on the screen, then the IA is "far" from the main
		// character
		if (getX() < Globals.xoffset || getX() > (Globals.xoffset + Globals.gcWidth)
				|| getY() < Globals.yoffset || getY() > (Globals.yoffset + Globals.gcHeight)) {
			hasBeenFar = true;
		}
	}
	
	/**
	 * Makes additional actions when collision with the player
	 */
	public void onCollision() {
		if(! visited) {
			visited = true;
		}
	}
	
	/**
	 * Returns if the IA has already been visited by the player
	 * @return visited if the IA has already been visited
	 */
	public boolean isVisited(){
		return visited;
	}
	
	/**
	 * Returns if the character has been far enough from an already visited IA
	 * @return if the ia has been far from the character
	 */
	public boolean hasBeenFar(){
		return hasBeenFar;
	}
	
	/**
	 * Sets the hasBeenFar variable
	 * @param hasBeenFar if the IA has been far enough from the player
	 */
	public void setHasBeenFar(boolean hasBeenFar){
		this.hasBeenFar = hasBeenFar;
	}
	
}
