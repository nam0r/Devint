package game;

import utils.Conf;
import main.Songe;
import actors.IA;

/**
 * Mario IA
 */
public class MarioIA extends IA {
	
	public MarioIA(int x, int y) {
		super(Conf.IMG_SPRITES_PATH+"mariowalk_big.png", 3, x, y, 40, 62, 12);
		moveForce = 150;
	}

}