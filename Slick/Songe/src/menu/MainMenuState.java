package menu;

import main.Songe;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import utils.Conf;
import utils.Globals;
 
public class MainMenuState extends MenuState {
	
    public MainMenuState(int stateID) throws SlickException {
    	super(stateID);
    	
		options = new String[] {"Jouer","Scores","Minijeux","Quitter"};
    	optionsVoices = new String[] {Conf.getVoice("jouer"),Conf.getVoice("scores"),Conf.getVoice("minijeux"),Conf.getVoice("quitter")};
    	title = "Bienvenue dans l'univers de Songe";
    	titleVoice = Conf.getVoice("bienvenue");
    	music = new Music(Conf.SND_MUSIC_PATH + "fond.ogg");
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
			answered = true;
			switch (selected) {
			case 0:
				sbg.enterState(Songe.CHOICEPERSOSTATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				break;
			case 1:
				sbg.enterState(Songe.SAVEHIGHSCORE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				break;
			case 2:
				sbg.enterState(Songe.CHOICEMINIGAMESTATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				break;
			case 3:
				gc.exit();
				break;
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
		}
		
		//we set the music volume, depending if voices are playing or not
		if(!AlUtils.anySoundPlaying()){
			if(music.getVolume() < 0.8)
				music.setVolume(music.getVolume()+0.05f);
			else
				music.setVolume(0.8f);
		}
		else
			music.setVolume(0.08f);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		//this state is important so we put it in Globals
		Globals.returnState = stateID;
		music.loop();
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		music.stop();
	}

}