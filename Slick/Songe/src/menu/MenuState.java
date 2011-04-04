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
	
	protected final int CASE_LARGE = 120;
	protected final int CASE_LARGE_SELECTED = 120;
	
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
		boolean selectedPassed = false;
		int suppHeight = 0;
		for (int i=0;i<options.length;i++) {
			//selected menu
			if (selected == i) {
				suppHeight = CASE_LARGE_SELECTED-CASE_LARGE;
				selectedPassed = true;
				//rectangle border
				gfx.setColor(new Color(255, 255, 255));
				gfx.drawRect(gc.getWidth()/20, (gc.getHeight()/3)+(i*CASE_LARGE), 9*gc.getWidth()/10, CASE_LARGE_SELECTED-1);
				//rectangle
				gfx.setColor(new Color(250, 240, 240));
				gfx.fillRect(gc.getWidth()/20+1, (gc.getHeight()/3)+(i*CASE_LARGE)+1, 9*gc.getWidth()/10-1, CASE_LARGE_SELECTED-2);
				//text
				gfx.setColor(Color.black);
				gfx.drawString(options[i], gc.getWidth()/2 - (font.getWidth(options[i])/2), gc.getHeight()/3+(i*CASE_LARGE)+CASE_LARGE_SELECTED/3);
			}
			//not selected menus
			else{
				//rectangle border
				gfx.setColor(new Color(0, 0, 220));
				gfx.drawRect(gc.getWidth()/10, (gc.getHeight()/3)+(i*CASE_LARGE)+suppHeight, 4*gc.getWidth()/5, CASE_LARGE);
				//rectangle
				gfx.setColor(new Color(0, 0, 200));
				gfx.fillRect(gc.getWidth()/10+1, (gc.getHeight()/3)+(i*CASE_LARGE)+suppHeight+1, 4*gc.getWidth()/5-1, CASE_LARGE-1);
				//text
				gfx.setColor(new Color(220,220,220));
				gfx.drawString(options[i], gc.getWidth()/2 - (font.getWidth(options[i])/2), gc.getHeight()/3+(i*CASE_LARGE)+CASE_LARGE/3);
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