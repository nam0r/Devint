package game;

import java.io.IOException;

import nodes.Dialog;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
	/** the image to display */
	private Image image;
	/** indicates if the image is set */
	private boolean imageIsSet;
	/** the image to display */
	private ParticleSystem emitter;
	/** indicates if the image is set */
	private boolean emitterIsSet;
	
    public DialogState(int stateID) throws SlickException {
    	this.stateID = stateID;
    	imageIsSet = false;
    	emitterIsSet = false;
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
		if(imageIsSet)
			image.draw(gc.getWidth()/2 - image.getWidth()/2, gc.getHeight()/2 - image.getHeight()/2);
		if(emitterIsSet){
			((ConfigurableEmitter) emitter.getEmitter(0)).setPosition(gc.getWidth()/2, gc.getHeight()/2);
			emitter.render();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		input = gc.getInput();
		//if the player pushes a button he wants to pass
		if (input.isKeyPressed(Input.KEY_ENTER) || !sound.playing()) {
			
			// if there is no specified state to go to from here, we go to the next event
			if(Globals.dialogNextState == -1)
				Globals.nextEvent(sbg);
			//else we go to that state
			else{
				int state = Globals.dialogNextState;
				Globals.dialogNextState = -1;
				sbg.enterState(state, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
				
			
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
		
		if(emitterIsSet)
			emitter.update(delta);
		

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
		if(dialog.getImage().substring((dialog.getImage().length()-3), dialog.getImage().length()).equals("xml")){
			try {
				emitter = ParticleIO.loadConfiguredSystem(Conf.EMITTERS_PATH+dialog.getImage());			
			} catch (IOException e) {
				System.err.println("Couldn't load particle "+Conf.EMITTERS_PATH+dialog.getImage());
			}
			((ConfigurableEmitter) emitter.getEmitter(0)).setPosition(gc.getWidth()/2, gc.getHeight()/2);
			emitterIsSet = true;
		}
		else{
			image = new Image(Conf.IMG_PATH+dialog.getImage());
			imageIsSet = true;
		}
		LoadingList.setDeferredLoading(true);
		
		sound.play();
		
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		AlUtils.stopAllSounds();
		imageIsSet = false;
		emitterIsSet = false;
	}

}