package minigame;

import java.io.IOException;

import main.Songe;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.ConfigurableEmitter.SimpleValue;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;

/**
 * Mini-game : battle against another character
 * 
 * @author namor
 *
 */
public class CheckTonScore extends BasicGameState {
	/** The state id */
	private int stateID;
	/** Sound when win the mini-game */
	private Sound2 gagne;
	/** Sound when lose the mini-game */
	private Sound2 perdu;
	/** The emitters of power */
	private ParticleSystem emit1, emit2;
	/** The X offsets of the emitter */
	private float rightXOffset, leftXOffset, rightYOffset, leftYOffset;
	/** the scale depending on the resolution of the screen */
	private float scale;
	/** Loading bar's base height */
	public static final int BAR_HEIGHT = 50;
	/** The actual amount representing the game situation */
	public float actual;
	/** The total amount representing the game situation */
	public float total;
	/** The actual key pressed for the alternative buttons */
	private String actualKey;


	public CheckTonScore(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		scale = (float) (gc.getWidth()) / 1024f;
		gagne = new Sound2(Conf.getVoice("oui"));
		perdu = new Sound2(Conf.getVoice("non"));
		try {
			emit1 = ParticleIO.loadConfiguredSystem(Conf.EMITTERS_PATH+"kameamea_red.xml");
			emit2 = ParticleIO.loadConfiguredSystem(Conf.EMITTERS_PATH+"kameamea_blue.xml");
		} catch (IOException e) {
			System.err.println("Couldn't load particle "+Conf.EMITTERS_PATH+"kameamea_X.xml");
		}
		for(int i=0; i<2; i++){
			((ConfigurableEmitter) emit1.getEmitter(i)).setPosition(gc.getWidth()/6, gc.getHeight()/2);
			((ConfigurableEmitter) emit2.getEmitter(i)).setPosition(5*gc.getWidth()/6, gc.getHeight()/2);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			if(!gagne.playing() && !perdu.playing()) {
				if(gagne.playedOnce()) {
					// if we are in main game
					if(Globals.returnState != Songe.MAINMENUSTATE){
						Globals.nextEvent(sbg, Globals.transitionSpeciale.getSuccess());
					}
					// if playing from the stand-alone version
					else{	
						sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
							new FadeInTransition(Color.black));
					}
				}
				// here the mini-game is finished, we go
				else if(perdu.playedOnce()) {
					// if we are in main game
					if(Globals.returnState != Songe.MAINMENUSTATE){
						Globals.nextEvent(sbg, Globals.transitionSpeciale.getFailure());
					}
					// if playing from the stand-alone version
					else{	
						sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
							new FadeInTransition(Color.black));
					}
				}
			}
		}
		
		//we lost
		if(actual < 0){
			if(!perdu.playedOnce())
				perdu.play();
		}
		//we won
		else if(actual > total){
			if(!gagne.playedOnce())
				gagne.play();
		}
		//we are playing
		else{
			emit1.update(delta);
			emit2.update(delta);
			leftXOffset += delta;
			//the actual goes down continually
			actual -= (float)delta/20;
			
			//the alternative buttons
			if(input.isKeyPressed(Input.KEY_LEFT)) {
				if(actualKey.equals("right")){
					actual += delta;
				}
				actualKey = "left";
			}
			else if(input.isKeyPressed(Input.KEY_RIGHT)) {
				if(actualKey.equals("left")){
					actual += delta;
				}
				actualKey = "right";
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		int x = gc.getWidth() / 2 - (int) ((total * scale) / 2);
		int y = 3*gc.getHeight() / 4 - (int) (30 * scale);
		g.fillRect(x, y + 50 * scale, actual * scale, BAR_HEIGHT
				* scale);
		g.drawRect(x, y + 50 * scale, total * scale, BAR_HEIGHT
				* scale);
		
		for(int i=0; i<2; i++){
			((ConfigurableEmitter) emit1.getEmitter(i)).setPosition(gc.getWidth()/6, gc.getHeight()/2);
			((SimpleValue)((ConfigurableEmitter) emit1.getEmitter(i)).windFactor).setValue(22);
			((ConfigurableEmitter) emit2.getEmitter(i)).setPosition(5*gc.getWidth()/6, gc.getHeight()/2);
			((SimpleValue)((ConfigurableEmitter) emit2.getEmitter(i)).windFactor).setValue(-22);
		}
		emit1.render();
		emit2.render();
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
	throws SlickException {
		AlUtils.resetAlListener();
		leftXOffset = 0;
		total = 700;
		actual = 350;
		actualKey = "left";
	}
}
