package actors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import questions.Question;

public abstract class IA extends Actor {

	private SpriteSheet walk;
	private int nb_sprites;
	
	private enum Way {RIGHT, LEFT};
	private Way way;
	
	private int walkingTimer;
	private final int WALKING_TIME = 1000; // 1 seconds
	
	protected Question question;
	
	public IA(String pathToSpriteSheet, int nb_sprites, float x, float y, float width, float height) {
		super(pathToSpriteSheet, x, y, 10f, width, height);
		
		this.nb_sprites = nb_sprites;
		walk = new SpriteSheet(image,(int)width,(int)height); // A revoir
		
		way = Way.LEFT;
		walkingTimer = 0;
		
		moveForce = 1;
		
		question = createQuestion();
		
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	protected abstract Question createQuestion();

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
		
		if(walkingTimer > WALKING_TIME) {
			walkingTimer = 0;
			way = (way == Way.LEFT) ? Way.RIGHT : Way.LEFT; // On change de sens
		}
		
		if(way == Way.LEFT)
			moveLeft();
		else
			moveRight();
	}
	
}
