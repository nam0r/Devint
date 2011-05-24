package obsolete;

import nodes.Node;
import utils.Conf;
import actors.IA;

/**
 * Mario IA
 */
public class MarioIA extends IA {
	
	public MarioIA(int x, int y, Node node) {
		super(Conf.IMG_SPRITES_PATH+"mariowalk_big.png", 3, 0, false, x, y, 40, 62, 12, node);
		moveForce = 150;
	}
	
	public String toString() {
		return "Mario";
	}

}