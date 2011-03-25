package game;

import main.Globals;
import main.Hoorah;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import questions.Question;
import sound.Sound2;
import actors.IA;

/**
 * Homer IA
 */
public class HomerIA extends IA {
	
	public HomerIA(int x, int y){
		super("res/img/homeranim.png", 8, x, y, 36, 65);
		body.setMaxVelocity(10, 45);
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
		
		image.draw(getX()-width/2, getY()-height/2, width, height+2);
		
		
	}
	
	@Override
	protected Question createQuestion() {
		String[] choices = {
			"14 ans",
			"80 ans",
			"142 ans"
		};
		return new Question("Quelle est l'age de mon grand père ?", choices, 2, 140);
	}
	
	public void onCollision() {
		Globals.question = question;
	}

	@Override
	public int stateToGoTo() {
		return Hoorah.QUESTIONSTATE;
	}

}