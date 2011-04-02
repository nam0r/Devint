package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import actors.Actor;

/**
 * Un exemple de personnage
 */
public class Homer extends Actor {
	private SpriteSheet run;
	private SpriteSheet jump;
	
	private final int Y_OFFSET_WALK = 0;
	private final int NB_SPRITES_WALK = 8;
	
	public Homer() {
		super("ressources/all.png", 100, 150, 4f, 36, 63);
		
		//run = getSpriteSheet(0,36,65);
		//jump = getSpriteSheet(150,50,50);
		
		try {
			Image im = new Image("ressources/images/homeranim.png");
			run = new SpriteSheet(im, 36, 65);
		} catch (SlickException e) {
			System.err.println("Image Homer walk pas trouvées.");
		}
		
		moveForce = 100;
		jumpForce = 15000;
		MAX_JUMP_VEL = 50;
		body.setMaxVelocity(14, 45);
		
	}

	public void render(Graphics g) {
		// work out which animation we're showing based 
		// on the state of the actor
		SpriteSheet sheet = run;
		int sx = 0;
		int sy = 0;
		
		/*if (jumping()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} else if (falling()) {
			sheet = jump;
			sx = 2;
			sy = 0;
		} else*/ if (moving() && onGround()) {
			//int f = (frame % 6) + 1;
			sheet = run;
			sx = frame % 8;
			sy = 0;
 		} else if (onGround()) {
			sheet = run;
			sx = 7;
			sy = 0;
		}
		
		// get the appropriate sprite 
		Image image = sheet.getSprite(sx,sy);
		image = image.getFlippedCopy(true, false);
	
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		//image.drawCentered(getX(), getY()-12);
		image.draw(getX()-width/2, getY()-height/2, width, height+2);
	}

}