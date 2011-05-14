package menu;

import game.Alien;
import game.Lamasticot;
import game.Tux;
import main.Songe;
import nodes.Node;

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
import utils.Globals;
import actors.MainPlayer;

public class ChoicePersoState extends ChoiceMenuState {

	public ChoicePersoState(int stateID) {
		super(stateID);
		options = new String[] { "Aurore", "Timéo", "Tux", "Lamasticot" };
		optionsVoices = new String[] {Conf.getVoice("aurore"),Conf.getVoice("timeo"),Conf.getVoice("tux"),Conf.getVoice("lamasticot")};
    	title = "Choisissez le personnage que vous allez incarner.";
    	titleVoice = Conf.getVoice("title_choiceperso");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		images = new Image[] { new Image(Conf.IMG_PATH+"interrogation.png"), new Image(Conf.IMG_PATH+"interrogation.png"),
				new Image(Conf.IMG_PATH+"tux.png"), new Image(Conf.IMG_PATH+"lama.png") };
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
			// The player is created
			Globals.player = createPlayer();
			sbg.enterState(Songe.GAMEPLAYSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
		    sbg.enterState(Songe.MAINMENUSTATE, new FadeOutTransition(Color.black),
                    new FadeInTransition(Color.black));
		}
	}
	
	/**
	 * Create the player object
	 * @return the player itself
	 */
	protected MainPlayer createPlayer() {
		switch (selected) {
		case 0:
			return new Tux(new Node(1));
		case 1:
			return new Alien(new Node(1));
		case 2:
			return new Tux(new Node(1));
		case 3:
			return new Lamasticot(new Node(1));
		default:
			return new Tux(new Node(1));
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