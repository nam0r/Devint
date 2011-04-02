package minigame;

import java.awt.Dimension;
import java.util.Vector;

import main.Conf;
import main.Globals;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;

public class HoverCave extends BasicGameState {
	private int stateID;
	
	private final int MIN_WIDTH = 55 ;
	private final int WALL_RES = 20;
	private final int SENSITIVITY = 10;
	private GameContainer container;
	private double dudeHeight;
	private Dimension dudeSize;
	private boolean movingUp;
	private Vector<Integer> upperWall;
	private Vector<Integer> lowerWall;
	private float wallOffset;
	private boolean dead;
	private double speed;
	private int distance;
	private Sound2 sonG, sonD;
	private int sonDIndex, sonGIndex;
	private float distSonHaut, distSonBas;

	public HoverCave(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	private void popWall() {
		upperWall.remove(0);
		lowerWall.remove(0);
	}

	private void addToWall() {
		// TODO Do some of these numbers need to be tweaked?
		int offset1 = (int) (Math.random() * 50 - 20);
		int offset2 = (int) (Math.random() * 50 - 30);
		int nextUpper = upperWall.get(upperWall.size() - 1) + offset1;
		int nextLower = lowerWall.get(lowerWall.size() - 1) + offset2;
		if (nextUpper >= container.getHeight() - MIN_WIDTH) {
			nextUpper = container.getHeight() - MIN_WIDTH;
		}
		if (nextUpper < 0) {
			nextUpper = 0;
		}
		if (nextLower <= MIN_WIDTH) {
			nextLower = MIN_WIDTH;
		}
		if (nextLower > container.getHeight()) {
			nextLower = container.getHeight();
		}
		// TODO fixme: this behaves a little bit strange, but maybe it's okay
		// for now.
		if (nextLower - nextUpper <= MIN_WIDTH) {
			nextLower += nextLower - (nextUpper - MIN_WIDTH);
			nextUpper -= nextLower - (nextUpper - MIN_WIDTH);
		}
		upperWall.add(nextUpper);
		lowerWall.add(nextLower);
	}

	public void reset() {
		upperWall = new Vector<Integer>();
		lowerWall = new Vector<Integer>();
		upperWall.add(0);
		lowerWall.add(container.getHeight());
		for (int i = 0; i < container.getWidth() / WALL_RES; i++) {
			addToWall();
		}
		dudeHeight = container.getHeight() / 2;
		wallOffset = 0;
		dead = false;
		speed = 0.06;
		distance = 0;
		movingUp = false;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		sonG = new Sound2(Conf.SND_PATH+"bip6.ogg");
		sonD = new Sound2(Conf.SND_PATH+"bip6.ogg");
		this.container = container;
		dudeSize = new Dimension(20, 30);
		reset();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if (!dead) {
			if (movingUp) {
				dudeHeight -= ((double) delta) / 10.0;
			} else {
				dudeHeight += ((double) delta) / 10.0;
			}
			// TODO The speed can be adjusted here
			wallOffset -= (float) delta * speed;
			speed += ((double) delta / 1000000000.0) * 2000;
			if (wallOffset <= -WALL_RES) {
				wallOffset += WALL_RES;
				popWall();
				addToWall();
			}
			distance += delta;
			// detect collisions
			// TODO Improve collision detection to find the edge of the box
			// against the edge of the cave.
			if (dudeHeight + SENSITIVITY > lowerWall.get(2)
					|| dudeHeight - SENSITIVITY < upperWall.get(2)) {
				//sonG.play(2, 1);
				dead = true;
				sonD.stop();
				sonG.stop();
			} else {
				//Sounds are adjusted here
				distSonBas = (float) (1.0 / ((lowerWall.get(2) - dudeHeight + 20) / 50.0));
				distSonHaut = (float) (1.0 / ((dudeHeight - upperWall.get(2) + 20) / 50.0));
				sonD.setVolume(distSonBas*2, sonDIndex);
				sonD.setPitch(distSonBas, sonDIndex);
				sonG.setVolume(distSonHaut*2, sonGIndex);
				sonG.setPitch(distSonHaut, sonGIndex);
			}
			if (input.isKeyPressed(Input.KEY_ESCAPE)) {
				game.enterState(Globals.returnState, new FadeOutTransition(
						Color.black), new FadeInTransition(Color.black));
			}
		}
		else{
			//The score is set
			Globals.score += distance/1000;
			if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
				game.enterState(Globals.returnState, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
				//game.enterState(Hoorah.SAVEHIGHSCORE, null, new BlobbyTransition());
			}
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Graphics?
		g.drawString("Distance: " + distance, 10, 25);
		g.drawString(
				"Speed: " + ((double) ((int) (speed * 10000.0))) / 10000.0, 10,
				40);
		for (int i = 0; i < upperWall.size() - 1; i++) {
			g.drawLine(i * WALL_RES + wallOffset, upperWall.get(i), (i + 1)
					* WALL_RES + wallOffset, upperWall.get(i + 1));
		}
		for (int i = 0; i < lowerWall.size() - 1; i++) {
			g.drawLine(i * WALL_RES + wallOffset, lowerWall.get(i), (i + 1)
					* WALL_RES + wallOffset, lowerWall.get(i + 1));
		}
		g.drawRect(WALL_RES, (int) dudeHeight - dudeSize.height / 2,
				dudeSize.width, dudeSize.height);
		g.drawLine(WALL_RES * 1.5f, (int) dudeHeight, WALL_RES * 1.5f,
				(int) dudeHeight);
		if(dead) g.drawString("Le jeu est terminé, appuyez sur Entrée pour continuer", 250, 150);
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		reset();
		if(sonG.playing() || sonD.playing()){
			sonD.stop();
			sonG.stop();
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg)
	throws SlickException {
		//The listener should be at default position
		AlUtils.resetAlListener();
		//We play the two sounds since we enter
		sonDIndex = sonD.loop(1f, 0f, 1f, 0f, 0f);
		sonGIndex = sonG.loop(1f, 0f, -1f, 0f, 0f);
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = true;
		}
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = false;
		}
	}
}
