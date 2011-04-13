package questions;

import main.Conf;
import main.Globals;
import menu.MenuState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class QuestionState extends MenuState {
	
	private Question question;
	
    public QuestionState(int stateID) throws SlickException {
    	super(stateID);
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
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
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if(question.isOk(selected))
				Globals.score += question.getPoints();
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
				
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		question = Globals.node.getQuestion();
		if(question == null) {
			System.err.println("Il n'y a pas de question a lire !!!");
		}
		
		//This is useful because we load here sounds that we didn't know at the beginning of the game, they are not deferred
		LoadingList.setDeferredLoading(false);
		
		options = question.getChoicesWordings(); // the choices
		
		title = question.getWording(); // the question
		
		// Le son a lire pour entendre l'enonce de la question
		titleVoice = question.getVoice();
		
		// Les sons a lire pour entendre les choix possibles pour la question
		//optionsVoices = new String[]{Conf.getVoice("14ans"), Conf.getVoice("80ans"), Conf.getVoice("140ans")};
		optionsVoices = question.getChoicesVoices();
		
		optionsSounds = new Sound[options.length];
		titleSound = new Sound(titleVoice);
		for(int i=0; i<options.length; i++){
    		optionsSounds[i] = new Sound(optionsVoices[i]);
    	}
		// ===============
		
		super.enter(gc, sbg); //It will read the options[selected]
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		options = new String[0];
		title = "";
		LoadingList.setDeferredLoading(true);
	}

}