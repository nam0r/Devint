package game;

import main.Conf;
import main.Hoorah;
import net.phys2d.math.Vector2f;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import actors.IA;

/**
 * Homer IA
 */
public class HomerIA extends IA {
	
	public HomerIA(int x, int y){
		super(Conf.IMG_SPRITES_PATH+"homeranim_big_flashy.png", 8, x, y, 72, 130, 10);
		body.setMaxVelocity(20, 90);
		moveForce = 300;
		walkingTime = 1500;
		
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
		image = image.getFlippedCopy(true, false);
		
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		image.draw(getX()-width/2, getY()-height/2, width, height+4);	
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		//Makes him jump and fall again and again
		if(!jumped && onGround){
			body.addForce(new Vector2f(0, -1000));
		}
	}
	
	@Override
	public void onCollision() {
		// TODO
	}

	@Override
	public int stateToGoTo() {
		return Hoorah.QUESTIONSTATE;
	}

}