package menu;

import main.Songe;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Conf;
import utils.Globals;
 
public class InitialMenuState extends MenuState {
	
    public InitialMenuState(int stateID) throws SlickException {
    	super(stateID);
    	
		options = new String[] {"Non","Oui"};
    	optionsVoices = new String[] {Conf.getVoice("jouer"),Conf.getVoice("scores")};
    	title = "Avez-vous déjà joué au jeu ?";
    	titleVoice = Conf.getVoice("bienvenue");
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
    	initSounds();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		super.render(gc, sbg, gfx);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		
		Input input = gc.getInput();

		if (!chosen) {
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				chosen = true;
				switch (selected) {
				case 0:
					Globals.hasAlreadyPlayed = false;
					break;
				case 1:
					Globals.hasAlreadyPlayed = true;
					break;
				}
				sbg.enterState(Songe.MAINMENUSTATE,
						new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		//this state is important so we put it in Globals
		Globals.returnState = stateID;
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
	}

}