package game;

import java.util.ArrayList;
import java.util.LinkedList;

import map.Map;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
import nodes.Node;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Globals;
import actors.IA;
import actors.PhysicalEntity;

/**
 * Abstract class that manages the main game
 */
public abstract class AbstractGameState extends BasicGameState {
	
	protected Map map;
	
	private String pathToBackground;
	private String pathToTilesDefinitions;
	private String pathToMap;
	private int tilesWidth;
	private int tilesHeight;
	private float backPar;
	private float backPar2;
	
	protected int stateID;
	
	/** The amount of time passed since last control check */
	private int totalDelta;
	/** The interval to check the controls at */
	private int controlInterval = 50;
	
	
	
	public AbstractGameState(int id, String pathToBackground, String pathToTilesDefinitions, String pathToMap, 
			int tilesWidth, int tilesHeight, float backPar, float backPar2) {
		this.stateID = id;
		this.pathToBackground = pathToBackground;
		this.pathToTilesDefinitions = pathToTilesDefinitions;
		this.pathToMap = pathToMap;
		this.tilesWidth = tilesWidth;
		this.tilesHeight = tilesHeight;
		this.backPar = backPar;
		this.backPar2 = backPar2;
		
	}
	
	@Override
	public int getID() {
		return this.stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	
		createMap();
		// Moving objects are created and added
		/*
		ArrayList<PhysicalEntity> entities = createEntities();
		for(PhysicalEntity pe : entities) {
			map.addEntity(pe);
		}
		*/
	}
	
	protected void createMap() {
		
		map = new Map(pathToBackground, pathToTilesDefinitions, pathToMap, tilesWidth, tilesHeight, backPar, backPar2);
		// We initialize the map
		map.init();
		
		// We manage collisions (especially for IAs)
		manageCollisions();
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(g, gc);	
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		// States management
		statesManagement(gc, sbg, delta);
		
		totalDelta += delta;
		
		// Events not depending on time
		notTimedEvents(gc, sbg, delta);
		
		// Events that need to be executed every amount of time and not at each
		// frame. If they were executed at each frame, the framerate of the game
		// would affect the gameplay
		if (totalDelta > controlInterval) {
			controlInterval -= totalDelta;
			
			timedEvents(gc, sbg, delta);
		}
		
		// Update the map
		map.update(delta, gc, Globals.player);
		
		if(Globals.stateToGoTo.peek() != null)
			sbg.enterState(Globals.stateToGoTo.poll(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.enter(gc, sbg);
		map.setMainPlayer(Globals.player);
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.leave(gc, sbg);
	}
	
	/* ******************* *
	 *  Abstract methods   *
	 * ******************* */
	
	protected abstract ArrayList<PhysicalEntity> createEntities() throws SlickException;
	protected abstract void notTimedEvents(GameContainer gc, StateBasedGame sbg, int delta);
	protected abstract void timedEvents(GameContainer gc, StateBasedGame sbg, int delta);
	protected abstract void statesManagement(GameContainer gc, StateBasedGame sbg, int delta);
	protected abstract void collisions(IA ia);
	
	
	private void manageCollisions() {
		
		map.getWorld().addListener(new CollisionListener() {

			@Override
			public void collisionOccured(CollisionEvent event) {
				
				Body bodyOther = null;
				if(event.getBodyA().equals(Globals.player.getBody()))
					bodyOther = event.getBodyB();
				else if(event.getBodyB().equals(Globals.player.getBody()))
					bodyOther = event.getBodyA();
				
				if(bodyOther != null) { // Si la collision implique le player principal
					PhysicalEntity other = map.getEntityByBody(bodyOther);
					//If the object is an IA
					if(other instanceof IA) {
						
						/* Idee : creer un attribut "stateToGoTo" initialise a null.
						 * Mettre une methode "public State stateToGoTo" dans IA qui renvoie l'etat dans lequel
						 * on passe apres une collision avec l'IA.
						 * Ici, faire "this.stateToGoTo = ((IA)other).stateToGoTo();
						 * 
						 * Dans update, faire :
						 * if(this.stateToGoTo != null) {
						 *     sbg.enterState(this.stateToGoTo, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
						 * }
						 * 
						 * Dans leave(), rajouter "this.stateToGoTo = null;"
						 */
						
						
						/*
						& * ====================================================
						((IA)other).onCollision();
						stateToGoTo = ((IA)other).stateToGoTo();
						=======================================================
						*/
						
						collisions(((IA)other));
						
						//Question question = ((IA)other).getQuestion();
					}
				}
			}
			
		});
	}

}
