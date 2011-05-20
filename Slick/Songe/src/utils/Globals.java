package utils;

import game.Alien;
import game.AlienIA;
import game.Crate;
import game.Emitter;
import game.HomerIA;
import game.Lamasticot;
import game.MarioIA;
import game.Spirit;
import game.Timeo;
import game.Tux;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Polygon;
import nodes.Choice;
import nodes.Dialog;
import nodes.Event;
import nodes.Node;
import nodes.Question;
import nodes.QuestionCulture;
import nodes.QuestionScenario;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import bdd.SQLiteDB;

import actors.MainPlayer;
import actors.PhysicalEntity;

/**
 * Global variables of the whole game.
 */

public class Globals {
	/** The global score */
	public static int score = 0;
	/** current player type */
	public static int playerType;
	/** Current player */
	public static MainPlayer player;
	/** Return to state */
	public static int returnState;
	/** The current scenario Node */
	public static Node node;
	/** The current question */
	public static Question<? extends Choice> question;
	/** The current dialog */
	public static Dialog dialog;
	/** Tells whether the current node has changed */
	public static boolean nodeHasChanged;
	
	/** the gc height */
	public static int gcHeight;
	/** the gc width */
	public static int gcWidth;
	/** the real time x screen offset */
	public static float xoffset;
	/** the real time x screen offset */
	public static float yoffset;
	/** The list of the states the player has to go */
	//public static LinkedList<Integer> stateToGoTo = new LinkedList<Integer>();
	/** The list of the node's places */
	public static LinkedList<Dimension> nodes = new LinkedList<Dimension>();
	/** Indicates if the character is invulnerable */
	public static boolean invulnerable = false;
	/** Indicates the time that the player is invulnerable */
	public static int invulnerableTimer = 0;
	/** indicates if the game has started */
	public static boolean started;
	/** The actual Event */
	public static Event event;

	/**
	 * Indicates if the player has already played the game and therefore doesn't
	 * need a lot of explanation
	 */
	public static boolean hasAlreadyPlayed;
	/** Specifies a special state to go to from the dialog state */
	public static int dialogNextState = -1;

	public static ArrayList<HashMap<String,String>> questionsNotAsked;
	
	public static SQLiteDB bdd = new SQLiteDB("data");
	/**
	 * Returns an entity from its ID
	 * @param entityID the name-ID of the entity to create in the map
	 * @param n the node to associate to it
	 * @return entity the entity corresponding to the ID we gave
	 */
	// TODO Useless
	public static PhysicalEntity getEntityFromString(String entityID, Node n) {
		PhysicalEntity entity = null;
		
		Dimension d = nodes.poll();
		if(entityID.equals("homer")) {
			entity = new HomerIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("mario")) {
			entity = new MarioIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("alien")) {
			entity = new AlienIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("crate")) {
			entity = new Crate(Conf.IMG_TEXTURES_PATH+"crate3.jpg", (int)d.getWidth(), (int)d.getHeight(), 80, 80, 500);
		}
		else if(entityID.equals("spirit")) {
			ROVector2f[] points = {new Vector2f(-20,0), new Vector2f(0,-3), new Vector2f(20,0)};
			DynamicShape spirit = new Polygon(points);
			entity = new Spirit(Conf.EMITTERS_PATH+"blue_spirit.xml", -50, 1, (int)d.getWidth(), (int)d.getHeight(), 100, spirit, n);
		}
		else if(entityID.equals("flame")) {
			entity = new Emitter(Conf.EMITTERS_PATH+"flame.xml", "flame", 10, 2, (int)d.getWidth(), (int)d.getHeight(), 50, 1, 100);
		}
		return entity;
	}
	
	/**
	 * Prepare the next event
	 */
	public static void nextEvent(StateBasedGame sbg, int idNode){
		
		System.out.println("1 === " + Globals.node.getEvents().size());
		Globals.event = Globals.node.pollEvent();
		System.out.println("2 === " + Globals.node.getEvents().size());
		
		
		// No more event => we go to the next default node
		if(Globals.event == null) {
			if(idNode == -1) {
				Globals.node = new Node(Globals.node.getNextNodeId());
			}
			else {
				Globals.node = new Node(idNode);
			}
			Globals.nodeHasChanged = true;
			
			Globals.goToState(Globals.returnState, sbg);
		}
		else {
			// Dialog
			if (Globals.event.getType().equals("D")) {
				Dialog d = (Dialog) Globals.event;
				Globals.dialog = d;
			}
			// Scenario
			else if (Globals.event.getType().equals("S")) {
				QuestionScenario q = (QuestionScenario) Globals.event;
				Globals.question = q;
			}
			// Culture
			else if (Globals.event.getType().equals("C")) {
				QuestionCulture q = (QuestionCulture) Globals.event;
				Globals.question = q;
			}
			// Transition (nothing to do)
			
			Globals.goToState(Globals.event.getStateID(), sbg);
		}
	}
	
	private static void goToState(int idState, StateBasedGame sbg) {
		sbg.enterState(idState, new FadeOutTransition(Color.black),
				new FadeInTransition(Color.black));
	}
	
	public static void nextEvent(StateBasedGame sbg){
		Globals.nextEvent(sbg, -1);
	}
	
	public static void initQuestionsNotAsked() {
		Globals.questionsNotAsked = Globals.bdd.select("SELECT * FROM qcult");
	}
	
	/**
	 *  Resets the player
	 */
	public static void resetMainPlayer() {
		if (Globals.hasAlreadyPlayed) {
			if (Globals.player instanceof Tux)// Aurore
				Globals.player = new Tux(40000, "tux_walk.png", "tux_jmp.png");
			if (Globals.player instanceof Timeo)// Timéo
				Globals.player = new Timeo(40000);
			if (Globals.player instanceof Tux)// Tux
				Globals.player = new Tux(40000, "tux_walk.png", "tux_jmp.png");
			if (Globals.player instanceof Lamasticot)// Lamasticot
				Globals.player = new Lamasticot(40000);
		}
		else{
			if (Globals.player instanceof Tux)// Aurore
				Globals.player = new Tux(10000, "tux_walk_white.png", "tux_jmp_white.png");
			if (Globals.player instanceof Timeo)// Timéo
				Globals.player = new Timeo(10000);
			if (Globals.player instanceof Tux)// Tux
				Globals.player = new Tux(10000, "tux_walk_white.png", "tux_jmp_white.png");
			if (Globals.player instanceof Lamasticot)// Lamasticot
				Globals.player = new Lamasticot(10000);
		}
	}
	
}
