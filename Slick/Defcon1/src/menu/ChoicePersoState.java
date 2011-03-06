package menu;

import main.Globals;
import main.Hoorah;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ChoicePersoState extends BasicGameState {

	private int stateID;

	private Music musique;

	/** The font to write the message with */
	private Font font;
	/** The menu options */
	private String[] options;
	/** The menu images */
	private Image[] images;
	/** The index of the selected option */
	private int selected;

	public ChoicePersoState(int stateID) {
		this.stateID = stateID;
		options = new String[] { "Homer Simpson", "Link", "Alien", "Mario",
				"Tux" };
		selected = 0;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		images = new Image[] { new Image("res/img/homer.jpg"),
				new Image("res/img/link.jpg"), new Image("res/img/alien.jpg"),
				new Image("res/img/mario.jpg"), new Image("res/img/tux.png") };

		font = new AngelCodeFont("res/demo2.fnt",
				"res/demo2_00.tga");
		//musique = new Music("../Slick/snd/hope.ogg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);

		for (int i = 0; i < images.length; i++) {
			// selected perso
			if (selected == i) {
				// The small image
				images[i].draw(gc.getWidth() / (images.length + 1) * (i + 1)
						- (images[i].getWidth() / 4 * 1.1f),
						3 * gc.getHeight() / 4, 0.6f);
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
						- images[i].getWidth() / 4, 3 * gc.getHeight() / 4,
						0.5f);
			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_DOWN)
				|| input.isKeyPressed(Input.KEY_RIGHT)) {
			selected++;
			if (selected >= options.length)
				selected = options.length - 1;
		}
		if (input.isKeyPressed(Input.KEY_UP)
				|| input.isKeyPressed(Input.KEY_LEFT)) {
			selected--;
			if (selected < 0)
				selected = 0;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			Globals.playerType = selected;
			sbg.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
	}

	@Override
	public int getID() {
		return this.stateID;
	}

	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);

		//musique.loop();
		Input input = gc.getInput();
		input.clearKeyPressedRecord();
	}

	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		selected = 0;
		//musique.stop();
	}

}