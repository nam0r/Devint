package game;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import utils.Conf;

import actors.Actor;

/**
 * Tux
 */
public class Tux extends Actor {
	private SpriteSheet walk;
	private SpriteSheet jump;
	
	private final int Y_OFFSET_WALK = 0;
	private final int NB_SPRITES_WALK = 7;
	
	public Tux() {
		super(Conf.IMG_SPRITES_PATH+"tux_jmp.png", 100, 150, 5f, 65, 95);
		
		jump = new SpriteSheet(image,65,95);
		
		try {
			Image im = new Image(Conf.IMG_SPRITES_PATH+"tux_walk.png");
			walk = new SpriteSheet(im,65,95);
		} catch (SlickException e) {
			System.err.println("Image sprites de Tux pas trouvées.");
		}
		
		moveForce = 270;
		jumpForce = 30000;
		MAX_JUMP_VEL = 105;
		body.setMaxVelocity(40, 105);
		jumpTime = 200;
		
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