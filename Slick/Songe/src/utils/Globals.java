package utils;

import nodes.Node;
import actors.Actor;

/**
 * Global variables of the whole game.
 */

public class Globals {
	/** The global score */
	public static int score=0;
	/** current player type */
	public static int playerType;
	/** Current player */
	public static Actor player;
	/** Return to state */
	public static int returnState;
	/** The current scenario Node */ 
	public static Node node;
}
