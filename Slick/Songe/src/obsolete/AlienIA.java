package obsolete;

import nodes.Node;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import utils.Conf;
import actors.IA;

/**
 * Mario IA
 */
public class AlienIA extends IA {
	private SpriteSheet run;
	
	public AlienIA(int x, int y, Node node) {
		super(Conf.IMG_SPRITES_PATH+"all_big.png", 3, 0, false, x, y, 48, 72, 8, node);
		body.setMaxVelocity(30, 110);
		walkingTime = 1200;
		moveForce = 200;
		run = getSpriteSheet(0,100,101);
	}

	@Override
	public void render(Graphics g) {
		// work out which animation we're showing based 
		// on the state of the actor
		SpriteSheet sheet = run;
		int sx = 0;
		int sy = 0;
		
		if (moving() && onGround()) {
			int f = (frame % 6) + 1;
			sheet = run;
			sx = f % nb_sprites;
			sy = f / nb_sprites;
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
		image.draw(getX()-48, getY()-56, 100, 100);
	}
	
	public String toString() {
		return "Alien";
	}

}