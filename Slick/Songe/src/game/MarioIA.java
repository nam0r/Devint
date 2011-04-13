package game;

import main.Conf;
import main.Hoorah;
import actors.IA;

/**
 * Mario IA
 */
public class MarioIA extends IA {
	
	public MarioIA(int x, int y) {
		super(Conf.IMG_PATH+"mariowalk.png", 3, x, y, 20, 31);
	}

	@Override
	public int stateToGoTo() {
		return Hoorah.HOVERCAVESTATE;
	}

}