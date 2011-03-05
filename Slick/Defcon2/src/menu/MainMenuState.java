package menu;

import main.Hoorah;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class MainMenuState extends BasicGameState {
 
	private int stateID;
	
	private Music musique;
	
	/** The font to write the message with */
	private Font font;
	/** The menu options */
	private String[] options = new String[] {"Jouer","Highscores","Instructions","Exit"};
	/** The index of the selected option */
	private int selected;
	
    public MainMenuState(int stateID) {
    	this.stateID = stateID;
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		font = new AngelCodeFont("../Slick/testdata/demo2.fnt","../Slick/testdata/demo2_00.tga");
		
		musique = new Music("../Slick/snd/hope.ogg");
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
	
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			selected++;
			if (selected >= options.length)
				selected = options.length - 1;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			selected--;
			if (selected < 0)
				selected = 0;
		}
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
		else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
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

		musique.loop();
		selected = 0;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);

		musique.stop();
	}

}