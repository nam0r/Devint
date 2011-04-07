package menu;

import main.Conf;
import main.Globals;
import main.Hoorah;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ChoicePersoState extends MenuState {

	/** The menu images */
	protected Image[] images;

	public ChoicePersoState(int stateID) {
		super(stateID);
		this.stateID = stateID;
		options = new String[] { "Aurore", "Timéo", "Tux", "Lamasticot" };
		optionsVoices = new String[] {Conf.getVoice("aurore"),Conf.getVoice("timeo"),Conf.getVoice("tux"),Conf.getVoice("lamasticot")};
    	title = "Choisissez le personnage que vous allez incarner.";
    	titleVoice = Conf.getVoice("title_choiceperso");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		images = new Image[] { new Image(Conf.IMG_PATH+"homer.jpg"), new Image(Conf.IMG_PATH+"alien.jpg"),
				new Image(Conf.IMG_PATH+"tux.png"), new Image(Conf.IMG_PATH+"mario.jpg") };
		initSounds();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);
		this.gfx = gfx;
		for (int i = 0; i < images.length; i++) {
			float scale = 1;
			// selected perso
			if (selected == i) {
				// The small image
				scale = (float)(gc.getWidth())/1450f;
				images[i].draw(gc.getWidth() / (images.length + 1) * (i + 1) - (images[i].getWidth() * (scale/2)),
						3 * gc.getHeight() / 4 - (images[i].getHeight() * (scale/4)), scale);
				// The big image
				scale = (float)(gc.getWidth())/1024f;
				images[i].draw(gc.getWidth() / 2 - images[i].getWidth() * (scale/2), gc.getHeight() / 3 - images[i].getHeight() * (scale/2), scale);
				gfx.setColor(Color.white);
				gfx.drawString(options[i], gc.getWidth() / 2
						- (font.getWidth(options[i]) / 2), gc.getHeight() / 3
						+ images[i].getHeight() * (scale/2) + 20);
			}
			// not selected persos
			else {
				// The small images
				scale = (float)(gc.getWidth())/1700f;
				images[i].draw(gc.getWidth() / (images.length + 1) * (i + 1) - images[i].getWidth() * (scale/2),
						3 * gc.getHeight() / 4 - (images[i].getHeight() * (scale/4)), scale);
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