package menu;

import main.Conf;
import main.Globals;
import main.Hoorah;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ChoicePersoState extends MenuState {

	private int stateID;

	/** The menu images */
	protected Image[] images;

	public ChoicePersoState(int stateID) {
		super(stateID);
		this.stateID = stateID;
		options = new String[] { "Homer Simpson", "Alien", "Mario",
				"Tux" };
		optionsVoices = new String[] {Conf.SND_VOIX_PATH+"jouer.ogg",Conf.SND_VOIX_PATH+"scores.ogg",Conf.SND_VOIX_PATH+"instructions.ogg",Conf.SND_VOIX_PATH+"quitter.ogg"};
    	title = "Bienvenue dans l'univers de Songe";
    	titleVoice = Conf.SND_VOIX_PATH+"bienvenue.ogg";
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		images = new Image[] { new Image(Conf.IMG_PATH+"homer.jpg"), new Image(Conf.IMG_PATH+"alien.jpg"),
				new Image(Conf.IMG_PATH+"mario.jpg"), new Image(Conf.IMG_PATH+"tux.png") };
		initSounds();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);
		this.gfx = gfx;
		for (int i = 0; i < images.length; i++) {
			// selected perso
			if (selected == i) {
				// The small image
				images[i].draw(gc.getWidth() / (images.length + 1) * (i + 1)
						- (images[i].getWidth() / 4 * 1.1f),
						3 * gc.getHeight() / 4, 0.7f);
				// The big image
				images[i].draw(gc.getWidth() / 2 - images[i].getWidth() / 2, gc
						.getHeight()
						/ 3 - images[i].getHeight() / 2, 1f);
				gfx.setColor(Color.white);
				gfx.drawString(options[i], gc.getWidth() / 2
						- (font.getWidth(options[i]) / 2), gc.getHeight() / 3
						+ images[i].getHeight() / 2 + 20);
			}
			// not selected persos
			else {
				// The small images
				images[i].draw(gc.getWidth() / (images.length + 1) * (i + 1)
						- images[i].getWidth() / 4,
						3 * gc.getHeight() / 4,	0.6f);
			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);

		if (input.isKeyPressed(Input.KEY_ENTER)) {
			//The player chosen
			Globals.playerType = selected;
			sbg.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
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
		selected = 0;
	}

}