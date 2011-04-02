package menu;

import main.Conf;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public abstract class MenuState extends BasicGameState {

	/** The id of the state for state based game */
	protected int stateID;
	/** For vocalize SIVOX */
	protected t2s.SIVOXDevint voix;
	/** The font to write the message with */
	protected Font font;
	/** The menu options */
	protected String[] options;
	/** The index of the selected option */
	protected int selected;
	
    public MenuState(int stateID) {
    	this.stateID = stateID;
    	//The voice tool
    	voix = new t2s.SIVOXDevint();
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		font = new AngelCodeFont(Conf.RESS_PATH+"demo2.fnt",Conf.RESS_PATH+"demo2_00.tga");
		
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
		
		if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_RIGHT)) {
			selected++;
			if (selected >= options.length)
				selected = 0;
			voix.stop();
			voix.playShortText(options[selected]);
		}
		if (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_LEFT)) {
			selected--;
			if (selected < 0)
				selected = options.length - 1;
			voix.stop();
			voix.playShortText(options[selected]);
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
		Input input = gc.getInput();
		input.clearKeyPressedRecord();
		voix.stop();
		voix.playShortText(options[selected]);
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		selected = 0;
	}

}