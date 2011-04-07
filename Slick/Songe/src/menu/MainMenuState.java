package menu;

import main.Conf;
import main.Globals;
import main.Hoorah;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class MainMenuState extends MenuState {
	
	
    public MainMenuState(int stateID) throws SlickException {
    	super(stateID);
    	//The voice tool
    	voix = new t2s.SIVOXDevint();
    	
		options = new String[] {"Jouer","Scores","Instructions","Quitter"};
    	optionsVoices = new String[] {Conf.SND_VOIX_PATH+"jouer.ogg",Conf.SND_VOIX_PATH+"scores.ogg",Conf.SND_VOIX_PATH+"instructions.ogg",Conf.SND_VOIX_PATH+"quitter.ogg"};
    	title = "Bienvenue dans l'univers de Songe";
    	titleVoice = Conf.SND_VOIX_PATH+"bienvenue.ogg";
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
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selected) {
			case 0:
				sbg.enterState(Hoorah.GAMEPLAYSTATE, new FadeOutTransition(Color.black),
						null);
				break;
			case 1:
				sbg.enterState(Hoorah.SAVEHIGHSCORE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				break;
			case 3:
				gc.exit();
				break;
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Hoorah.CHOICEPERSOSTATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		//this state is important so we put it in Globals
		Globals.returnState = stateID;
		
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
	}

}