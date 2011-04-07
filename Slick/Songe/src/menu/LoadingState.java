package menu;

import java.io.IOException;

import main.Conf;
import main.Hoorah;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LoadingState extends BasicGameState {
	/** the id of the state */
	private int stateID;
	/** The next resource to load */
	private DeferredResource nextResource;
	/** the scale depending on the resolution of the screen */
	private float scale;
	/** The game container */
	GameContainer gc;
	/** Loading bar's unit base width */
	public static final int BAR_WIDTH = 20;
	/** Loading bar's base height */
	public static final int BAR_HEIGHT = 50;
	/** Loading voice */
	private Sound voice;
	
	
	public LoadingState(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		voice = new Sound(Conf.getVoice("chargement"));
		LoadingList.setDeferredLoading(true);
		this.gc = gc;
		scale = (float)(gc.getWidth())/1024f;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
		int x=gc.getWidth()/2-(int)((total*BAR_WIDTH*scale)/2);
		int y=gc.getHeight()/2-(int)(30*scale);
		if (nextResource != null) {
			g.drawString("Chargement : "+nextResource.getDescription(), x, y);
		}
		g.fillRect(x, y+50*scale, loaded*BAR_WIDTH*scale, BAR_HEIGHT*scale);
		g.drawRect(x, y+50*scale, total*BAR_WIDTH*scale, BAR_HEIGHT*scale);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
				// slow down loading for example purposes
				try { Thread.sleep(0);} catch (Exception e) {}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			
			nextResource = null;
		}
		
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			game.enterState(Hoorah.CHOICEPERSOSTATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		voice.play();
	}
	
}
