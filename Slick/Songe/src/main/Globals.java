package main;

import actors.Actor;
import questions.Question;

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
	/** current question */
	public static Question question;
	/** Return to state */
	public static int returnState;
}
