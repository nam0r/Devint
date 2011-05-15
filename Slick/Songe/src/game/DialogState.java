package game;

import nodes.Dialog;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;
 
public class DialogState extends BasicGameState {
	
	/** The state id */
	private int stateID;
	/** The dialog */
	private Dialog dialog;
	/** Input */
	private Input input;
	/** The dialog sound to play */
	private Sound2 sound;
	
    public DialogState(int stateID) throws SlickException {
    	this.stateID = stateID;
    }
    
	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		input = gc.getInput();
		//if the player pushes a button he wants to pass
		if (input.isKeyPressed(Input.KEY_ENTER) || !sound.playing()) {

			Globals.nextEvent(sbg);
			
			/*
			Globals.nextEvent();
			if(Globals.nodeHasChanged) { // This event is the last of the node
				sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
			else { // There are still events to process
				sbg.enterState(Globals.event.getStateID(), new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
			*/
			
		}
		

	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		AlUtils.resetAlListener();
		dialog = Globals.dialog;
		if(dialog == null) {
			System.err.println("Il n'y a pas de dialogue a lire !!!");
		}
		
		LoadingList.setDeferredLoading(false);
		sound = new Sound2(Conf.getVoice(dialog.getSound())); 
		LoadingList.setDeferredLoading(true);
		
		sound.play();
		
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		AlUtils.stopAllSounds();
	}

}