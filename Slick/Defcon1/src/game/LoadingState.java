package game;

import java.io.IOException;

import main.Hoorah;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
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
	private Music musique;
	private Sound sonSaut;
	
	public LoadingState(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		LoadingList.setDeferredLoading(true);
		musique = new Music("../Slick/snd/requiem.wav");
		sonSaut = new Sound("res/snd/over.wav");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		int x=200; int y=300;
		if (nextResource != null) {
			g.drawString("Chargement : "+nextResource.getDescription(), x, y);
		}
		System.out.println(LoadingList.get());
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
			
		float bar = loaded / (float) total;
		g.fillRect(x,y+50,loaded*40,20);
		g.drawRect(x,y+50,total*40,20);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
				// slow down loading for example purposes
				try { Thread.sleep(50); } catch (Exception e) {}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			
			nextResource = null;
		}
		
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			game.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}

	}
	
}

