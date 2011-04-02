package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import actors.Actor;

/**
 * Un exemple de personnage
 */
public class Alien extends Actor {
	private SpriteSheet run;
	private SpriteSheet jump;
	
	public Alien() {
		super("ressources/all.png", 100, 150, 1f, 24, 36);
		
		run = getSpriteSheet(0,50,50);
		jump = getSpriteSheet(150,50,50);
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
			sx = 2;
			sy = 0;
		} else if (moving() && onGround()) {
			int f = (frame % 6) + 1;
			sheet = run;
			sx = f % 3;
			sy = f / 3;
 		} else if (onGround()) {
			sheet = run;
			sx = 0;
			sy = 0;
		}
		
		// get the appropriate sprite 
		Image image = sheet.getSprite(sx,sy);
	
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		//image.drawCentered(getX(), getY()-12);
		image.draw(getX()-24, getY()-28, 50, 50);
	}

}