package game;

import nodes.Node;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import utils.Conf;
import utils.Globals;
import actors.Actor;

/**
 * Un exemple de personnage
 */
public class Alien extends Actor {
	private SpriteSheet run;
	private SpriteSheet jump;
	
	public Alien(Node n) {
		super(Conf.IMG_SPRITES_PATH+"all_big.png", 100, 150, 3f, 48, 72, n);

		run = getSpriteSheet(0,100,101);
		jump = getSpriteSheet(301,100,100);
		
		moveForce = 200;
		jumpForce = 40000;
		MAX_JUMP_VEL = 100;
		body.setMaxVelocity(40, 100);
		
		Globals.node = node;

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
		image.draw(getX()-48, getY()-57, 100, 100);
	}

}