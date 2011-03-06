package questions;

import main.Globals;
import main.Hoorah;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class QuestionState extends BasicGameState {
 
	private int stateID;
	
	private Music musique;
	
	/** The font to write the message with */
	private Font font;
	/** The question */
	private String question;
	/** The choices */
	private String[] choices;
	/** The index of the selected choice */
	private int selected;
	
    public QuestionState(int stateID) {
    	this.stateID = stateID;
    	question = "";
    	choices = new String[0];
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		font = new AngelCodeFont("res/demo2.fnt","res/demo2_00.tga");
		//musique = new Music("../Slick/snd/hope.ogg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		gfx.setFont(font);
		// Question
		gfx.setColor(Color.white);
		gfx.drawString(question, gc.getWidth()/2 - (font.getWidth(question)/2), gc.getHeight()/4);
		// Choices
		for (int i=0;i<choices.length;i++) {
			//selected menu
			if (selected == i) {
				gfx.setColor(Color.white);
				gfx.drawRect(gc.getWidth()/4, (gc.getHeight()/3-10)+(i*50), gc.getWidth()/2, 50);
				gfx.setColor(new Color(20,0,0));
				gfx.fillRect(gc.getWidth()/4+1, (gc.getHeight()/3-10)+(i*50)+1, gc.getWidth()/2-1, 50-1);
				gfx.setColor(Color.white);
				gfx.drawString(choices[i], gc.getWidth()/2 - (font.getWidth(choices[i])/2), gc.getHeight()/3+(i*50));
			}
			//not selected menus
			else{
				gfx.setColor(new Color(200,200,200));
				gfx.drawString(choices[i], gc.getWidth()/2 - (font.getWidth(choices[i])/2), gc.getHeight()/3+(i*50));
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			selected++;
			if (selected >= choices.length)
				selected = choices.length - 1;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			selected--;
			if (selected < 0)
				selected = 0;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if(Globals.question.isOk(selected))
				Globals.score += Globals.question.getPoints();
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				
		}
		else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
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
		choices = Globals.question.getChoices();
		question = Globals.question.getQuestion();
		//musique.loop();
		Input input = gc.getInput();
		input.clearKeyPressedRecord();
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		choices = new String[0];
		question = "";
		selected = 0;
		//musique.stop();
	}

}