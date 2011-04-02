package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.Hoorah;
import questions.Question;
import actors.IA;

/**
 * Mario IA
 */
public class AlienIA extends IA {
	private SpriteSheet run;
	
	public AlienIA(int x, int y) {
		super("res/all.png", 3, x, y, 24, 36);
		body.setMaxVelocity(25, 55);
		walkingTime = 1200;
		run = getSpriteSheet(0,50,50);
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
	
	
	@Override
	protected Question createQuestion() {
		String[] choices = {
			"14 ans",
			"80 ans",
			"142 ans"
		};
		return new Question("Quelle est l'age de mon grand père ?", choices, 2, 140);
	}

	@Override
	public int stateToGoTo() {
		return Hoorah.SAVEHIGHSCORE;
	}

}