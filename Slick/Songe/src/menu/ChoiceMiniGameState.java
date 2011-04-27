package menu;

import main.Songe;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Conf;

public class ChoiceMiniGameState extends ChoiceMenuState {

	public ChoiceMiniGameState(int stateID) {
		super(stateID);
		options = new String[] { "HoverCave"};
		optionsVoices = new String[] {Conf.getVoice("hovercave")};
    	title = "Choisis le minijeu auquel tu veux jouer.";
    	titleVoice = Conf.getVoice("title_choiceminigame");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		images = new Image[] { new Image(Conf.IMG_PATH+"interrogation.png")};
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

		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selected) {
			case 0:
				sbg.enterState(Songe.HOVERCAVESTATE, new FadeOutTransition(
						Color.black), new FadeInTransition(Color.black));
				break;
			}
			
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
		    sbg.enterState(Songe.MAINMENUSTATE, new FadeOutTransition(Color.black),
                    new FadeInTransition(Color.black));
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
	}

}