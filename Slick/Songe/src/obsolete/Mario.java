package obsolete;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import utils.Conf;
import actors.MainPlayer;

/**
 * The Mario player
 * 
 * @author namor
 * @author Afnarel
 */
public class Mario extends MainPlayer {
	/** The Y offset for walking sprite sheet */
	private final int Y_OFFSET_WALK = 0;
	/** The amount of sheets for the walking sprite sheet */
	private final int NB_SPRITES_WALK = 3;
	
	public Mario(int n) {
		super(n, Conf.IMG_SPRITES_PATH+"mariowalk_big.png", 100, 150, 2f, 40, 62);
		
		walk = new SpriteSheet(image,40,62);
		
		try {
			Image im = new Image(Conf.IMG_SPRITES_PATH+"mariofall_big.png");
			jump = new SpriteSheet(im,56,54);
		} catch (SlickException e) {
			System.err.println("Image mario fall pas trouvées.");
		}
		
		moveForce = 150;
		jumpForce = 40000;
		MAX_JUMP_VEL = 120;
		body.setMaxVelocity(40, 120);
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
	
	public float stepRate(){
		return 35;
	}

}