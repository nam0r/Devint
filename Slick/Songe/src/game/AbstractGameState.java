package game;

import java.util.ArrayList;

import sound.Sound2;
import utils.Conf;
import main.Hoorah;
import map.Map;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Globals;
import actors.Actor;
import actors.IA;
import actors.PhysicalEntity;

/**
 * Cette classe abstraite gere l'organisation generale d'un etat du jeu
 * (en particulier les timers).
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
	
	/** The player that is being controlled */
	protected Actor player;
	
	/** The amount of time passed since last control check */
	private int totalDelta;
	/** The interval to check the controls at */
	private int controlInterval = 50;
	
	private int stateToGoTo;
	
	protected Sound2 alreadyVisited;
	
	
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
		map = new Map(pathToBackground, pathToTilesDefinitions, pathToMap, tilesWidth, tilesHeight, backPar, backPar2);
		// On crée le joueur et on l'ajoute a la map
		player = createPlayer();
		restart();
		// On crée les objets mobiles et on les ajoute a la map
		ArrayList<PhysicalEntity> entities = createEntities();
		for(PhysicalEntity pe : entities) {
			map.addEntity(pe);
		}
		alreadyVisited = new Sound2(Conf.SND_VOIX_PATH+"deja_rencontres.ogg");
	}
	
	public void restart(){
		// Gestion des collisions
		manageCollisions();
		// On initialise la map
		map.init();
		map.setMainPlayer(player);
		stateToGoTo = -1;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(g, gc);	
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		// Gestion des etats
		statesManagement(gc, sbg, delta);
		
		totalDelta += delta;
		
		// Gestion des evenements independants du temps
		notTimedEvents(gc, sbg, delta);
		
		// Gestion des evenement clavier a un interval de temps donne.
		// Si on ne fait pas ca, des frame rates differents affecteront la
		// maniere dont les evenements sont interpretes.
		if (totalDelta > controlInterval) {
			controlInterval -= totalDelta;
			
			timedEvents(gc, sbg, delta);
		}
		
		// Mettre a jour la map
		map.update(delta, gc, player);
		
		if(stateToGoTo != -1)
			sbg.enterState(this.stateToGoTo, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.enter(gc, sbg);
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.leave(gc, sbg);
	}
	
	/* ******************* *
	 * Methodes abstraites *
	 * ******************* */
	
	protected abstract Actor createPlayer();
	protected abstract ArrayList<PhysicalEntity> createEntities() throws SlickException;
	protected abstract void notTimedEvents(GameContainer gc, StateBasedGame sbg, int delta);
	protected abstract void timedEvents(GameContainer gc, StateBasedGame sbg, int delta);
	protected abstract void statesManagement(GameContainer gc, StateBasedGame sbg, int delta);
	
	
	protected void manageCollisions() {
		
		map.getWorld().addListener(new CollisionListener() {

			@Override
			public void collisionOccured(CollisionEvent event) {
				
				Body bodyOther = null;
				if(event.getBodyA().equals(player.getBody()))
					bodyOther = event.getBodyB();
				else if(event.getBodyB().equals(player.getBody()))
					bodyOther = event.getBodyA();
				
				if(bodyOther != null) { // Si la collision implique le player principal
					PhysicalEntity other = map.getEntityByBody(bodyOther);
					if(other instanceof IA) {
						//game.enterState(Hoorah.HOVERCAVESTATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
						//player.setX(player.getX() + 100); // Attention au relief
						
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
						if(!((IA)other).isVisited()) {
							if(Globals.node.getQuestion() == null && Globals.node.getGame() == null) {
								stateToGoTo = Hoorah.SAVEHIGHSCORE;
							}
							if(Globals.node.getQuestion() != null) {
								stateToGoTo = Hoorah.QUESTIONSTATE;
							}
							if(Globals.node.getGame() != null) {
								stateToGoTo = Globals.node.getGame().getId();
							}
							((IA)other).onCollision();
						}
						else {
							//alreadyVisited.play();
						}
						
						//Question question = ((IA)other).getQuestion();
						//System.out.println(question.toString());
					}
				}
			}
			
		});
	}

}
