package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import actors.Actor;

/**
 * Mario
 */
public class Mario extends Actor {
	private SpriteSheet walk;
	private SpriteSheet jump;
	
	private final int Y_OFFSET_WALK = 0;
	private final int NB_SPRITES_WALK = 3;
	
	public Mario() {
		super("res/all.png", 100, 150, 1f, 20, 31);
		
		try {
			Image im = new Image("res/img/mariowalk.png");
			walk = new SpriteSheet(im,20,31);
		} catch (SlickException e) {
			System.err.println("Image mario walk pas trouvées.");
		}
		
		try {
			Image im = new Image("res/img/mariofall.png");
			jump = new SpriteSheet(im,28,27);
		} catch (SlickException e) {
			System.err.println("Image mario fall pas trouvées.");
		}
		//walk = getSpriteSheet(0,50,50);
		//jump = getSpriteSheet(150,50,50);
	}

	public void render(Graphics g) {
		// work out which animation we're showing based 
		// on the state of the actor
		SpriteSheet sheet = jump;
		int sx = 0;
		int sy = 0;
		
		if (jumping()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} else if (falling()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} /*else if(waiting()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} */else if (moving() && onGround()) {
			sheet = walk;
			sx = frame % NB_SPRITES_WALK;
			sy = Y_OFFSET_WALK;
 		} else if (onGround() || waiting()) {
			sheet = walk;
			sx = 0;
			sy = Y_OFFSET_WALK;
		}
		
		// get the appropriate sprite 
		Image image = sheet.getSprite(sx,sy);
	
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		//image.drawCentered(getX(), getY()-12);
		image.draw(getX()-width/2, getY()-height/2, width, height);
	}

}