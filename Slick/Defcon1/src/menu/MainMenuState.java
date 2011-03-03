package menu;

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
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tests.states.TestState1;
import org.newdawn.slick.tests.states.TestState2;

import utils.Utils;
 
public class MainMenuState extends BasicGameState {
 
	private int stateID;
	
	private Sound enter;
	private Music musique;
	
	/** The font to write the message with */
	private Font font;
	/** The menu options */
	private String[] options = new String[] {"Jouer","Highscores","Instructions","Exit"};
	/** The index of the selected option */
	private int selected;
	/** The game holding this state */
	private StateBasedGame game;
	/** The actual game container */
	GameContainer gc;
	
    public MainMenuState(int stateID) {
    	this.stateID = stateID;
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		font = new AngelCodeFont("../Slick/testdata/demo2.fnt","../Slick/testdata/demo2_00.tga");
		
		// Sound
		enter = new Sound("res/snd/enter.wav");
		this.game = sbg;
		this.gc = gc;
		musique = new Music("../Slick/snd/hope.ogg"); // A revoir
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);
		
		for (int i=0;i<options.length;i++) {
			//selected menu
			if (selected == i) {
				gfx.setColor(Color.white);
				gfx.drawRect(gc.getWidth()/4, (gc.getHeight()/3-10)+(i*50), gc.getWidth()/2, 50);
				gfx.setColor(new Color(20,0,0));
				gfx.fillRect(gc.getWidth()/4+1, (gc.getHeight()/3-10)+(i*50)+1, gc.getWidth()/2-1, 50-1);
				gfx.setColor(Color.white);
				gfx.drawString(options[i], gc.getWidth()/2 - (font.getWidth(options[i])/2), gc.getHeight()/3+(i*50));
			}
			//not selected menus
			else{
				gfx.setColor(new Color(200,200,200));
				gfx.drawString(options[i], gc.getWidth()/2 - (font.getWidth(options[i])/2), gc.getHeight()/3+(i*50));
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	}

	@Override
	public int getID() {
		return this.stateID;
	}
	
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_DOWN) {
			selected++;
			if (selected >= options.length)
				selected = options.length - 1;
		}
		if (key == Input.KEY_UP) {
			selected--;
			if (selected < 0)
				selected = 0;
		}
		if (key == Input.KEY_ENTER) {
			switch (selected) {
			case 0:
				game.enterState(Hoorah.GAMEPLAYSTATE, new FadeOutTransition(Color.black),
						null);
				break;
			case 1:
				game.enterState(Hoorah.SAVEHIGHSCORE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				break;
			case 3: gc.exit();
				break;
			}
		}
		else if (key == Input.KEY_ESCAPE) {
			gc.exit();
		}
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);

		musique.loop();
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);

		musique.stop();
	}

}