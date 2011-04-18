package actors;

import net.phys2d.math.Vector2f;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class IA extends Actor {

	protected SpriteSheet walk;
	protected int nb_sprites;
	
	private enum Way {RIGHT, LEFT};
	private Way way;
	
	private int walkingTimer;
	protected int walkingTime;
	
	protected boolean visited;
	
	public IA(String pathToSpriteSheet, int nb_sprites, float x, float y, float width, float height, float mass) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		
		this.nb_sprites = nb_sprites;
		walk = new SpriteSheet(image,(int)width,(int)height); // A revoir
		
		way = Way.LEFT;
		walkingTimer = 0;
		
		moveForce = 400;
		
		visited = false;
		walkingTime = 1000; //1 sec
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
		
		if(way == Way.LEFT)
			moveLeft();
		else
			moveRight();
		
	}
	
	/**
	 * Makes additional actions when collision with the player
	 */
	public void onCollision() {
		if(! visited) {
			//System.out.println(question.toString());
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
	
	//public abstract int stateToGoTo();
	
}
