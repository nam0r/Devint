package questions;

import main.Globals;
import menu.MenuState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class QuestionState extends MenuState {

	/** The question */
	private String question;
	
    public QuestionState(int stateID) {
    	super(stateID);
    	question = "";
    	options = new String[0];
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		super.render(gc, sbg, gfx);
		// Question
		gfx.setColor(Color.white);
		gfx.drawString(question, gc.getWidth()/2 - (font.getWidth(question)/2), gc.getHeight()/4);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if(Globals.question.isOk(selected))
				Globals.score += Globals.question.getPoints();
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		options = Globals.question.getChoices();
		question = Globals.question.getQuestion();
		// TODO cette façon de faire est vraiment nulle ...
		String tempOpt1 = options[0];
		options[selected] = question;
		super.enter(gc, sbg); //It will read the options[selected]
		options[selected] = tempOpt1;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		options = new String[0];
		question = "";
	}

}