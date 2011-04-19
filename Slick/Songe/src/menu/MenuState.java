package menu;


import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
 
public abstract class MenuState extends BasicGameState {

	/** The id of the state for state based game */
	protected int stateID;
	/** For vocalize SIVOX */
	protected t2s.SIVOXDevint voix;
	/** The font to write the message with */
	protected Font font;
	/** The index of the selected option */
	protected int selected;
	/** Indicates how thick the cases are */
	protected int caseLarge;
	/** The width of the game */
	protected int width;
	/** the height of the game */
	protected int height;
	/** Indicates how thick the selected case is */
	protected int caseLargeSelected;
	/** The graphics */
	protected Graphics gfx;
	
	/** The title */
	protected String title;
	/** The title voice path */
	protected String titleVoice;
	/** The title sound */
	protected Sound titleSound;
	/** The menu options */
	protected String[] options;
	/** The menu options voices paths */
	protected String[] optionsVoices;
	/** The menu options sounds */
	protected Sound2[] optionsSounds;
	/** Indicates if the option 1 has been said for the 1st time */
	protected boolean firstOptionPlayed;
	/** Input */
	protected Input input;
	/** indicates if lines are already enlarged or not */
	protected boolean linesLarge;

	
    public MenuState(int stateID) {
    	this.stateID = stateID;
    	//The voice tool
    	voix = new t2s.SIVOXDevint();
    	firstOptionPlayed = false;
    	title = "";
    	titleVoice = "";
    	//titleSound = new Sound();
    	options = new String[0];
    	optionsVoices = new String[0];
    	optionsSounds = new Sound2[0];
    	selected = 0;
    }
    
    public void initSounds() throws SlickException{
    	titleSound = new Sound(titleVoice);
    	optionsSounds = new Sound2[options.length];
    	for(int i=0; i<options.length; i++){
    		optionsSounds[i] = new Sound2(optionsVoices[i]);
    	}
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		input = gc.getInput();
		input.enableKeyRepeat();
		font = new AngelCodeFont(Conf.RESS_PATH+"demo2.fnt",Conf.RESS_PATH+"demo2_00.tga");
		width = gc.getWidth();
		height = gc.getHeight();
		caseLarge = height/7;
		caseLargeSelected = height/7;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);
		this.gfx = gfx;
		if(!linesLarge){
			gfx.setLineWidth(20);
			linesLarge = false;
		}
		int suppHeight = 0;
		//title
		//rectangle border
		gfx.setColor(new Color(255, 255, 255));
		gfx.drawRect(width/10, (height/10), 4*width/5, caseLargeSelected-1);
		//rectangle
		gfx.setColor(new Color(250, 240, 240));
		gfx.fillRect(width/10+1, (height/10)+1, 4*width/5-1, caseLargeSelected-2);
		//text
		gfx.setColor(Color.black);
		gfx.drawString(title, width/2 - (font.getWidth(title)/2), height/10+caseLargeSelected/3);
		//menus
		for (int i=0;i<options.length;i++) {
			int caseHeightPosition = height/3;
			//selected menu
			if (selected == i) {
				int caseWidth = 9*width/10;
				suppHeight = caseLargeSelected-caseLarge;
				//rectangle border
				gfx.setColor(new Color(255, 255, 255));
				gfx.drawRect(width/20, caseHeightPosition+(i*caseLarge), caseWidth, caseLargeSelected-1);
				//rectangle
				gfx.setColor(new Color(250, 240, 240));
				gfx.fillRect(width/20+1, caseHeightPosition+(i*caseLarge)+1, caseWidth-1, caseLargeSelected-2);
				//text
				gfx.setColor(Color.black);
				gfx.drawString(options[i], width/2 - (font.getWidth(options[i])/2), caseHeightPosition+(i*caseLarge)+caseLargeSelected/3);
			}
			//not selected menus
			else{
				int caseWidth = 4*width/5;
				//rectangle border
				gfx.setColor(new Color(0, 0, 220));
				gfx.drawRect(width/10, caseHeightPosition+(i*caseLarge)+suppHeight, caseWidth, caseLarge);
				//rectangle
				gfx.setColor(new Color(0, 0, 200));
				gfx.fillRect(width/10+1, caseHeightPosition+(i*caseLarge)+suppHeight+1, caseWidth-1, caseLarge-1);
				//text
				gfx.setColor(new Color(220,220,220));
				gfx.drawString(options[i], width/2 - (font.getWidth(options[i])/2), caseHeightPosition+(i*caseLarge)+caseLarge/3);
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//If the title is finished, we play 1 time the 1st option
		if(!titleSound.playing() && !firstOptionPlayed){
			optionsSounds[selected].play();
			firstOptionPlayed = true;
		}
		if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_RIGHT)) {
			selected++;
			if (selected >= options.length)
				selected = 0;
			//voix.stop();
			//voix.playShortText(options[selected]);
			//voix.playWav(optionsVoices[selected]);
			stopSounds();
			optionsSounds[selected].play();
		}
		if (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_LEFT)) {
			selected--;
			if (selected < 0)
				selected = options.length - 1;
			//voix.stop();
			//voix.playShortText(options[selected]);
			//voix.playWav(optionsVoices[selected]);
			stopSounds();
			optionsSounds[selected].play();
		}
	}
	
	protected void stopSounds(){
		for(int i=0; i<optionsSounds.length; i++){
			optionsSounds[i].stop();
		}
		titleSound.stop();
	}
	
	@Override
	public int getID() {
		return this.stateID;
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//useful for the beautiful and fat menu
		//super.enter(gc, sbg);
		linesLarge = false;
		Input input = gc.getInput();
		input.clearKeyPressedRecord();
		//The listener should be at default position
		AlUtils.resetAlListener();
		//voix.stop();
		//voix.playShortText(title+". "+options[selected]);
		//voix.playWav(titleVoice);
		//voix.playWav(optionsVoices[selected]);
		titleSound.play();
		
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		selected = 0;
		//because it would cause graphical disaster in other states
		gfx.setLineWidth(1);
		firstOptionPlayed = false;
		stopSounds();
	}

}