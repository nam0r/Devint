package game;

import main.Conf;
import main.Hoorah;
import questions.Question;
import actors.IA;

/**
 * Mario IA
 */
public class MarioIA extends IA {
	
	public MarioIA(int x, int y) {
		super(Conf.IMG_SPRITES_PATH+"mariowalk_big.png", 3, x, y, 40, 62, 12);
		moveForce = 150;
	}

	@Override
	protected Question createQuestion() {
		String[] choices = {
			"14 ans",
			"80 ans",
			"142 ans"
		};
		return new Question("Quelle est l'age de mon grand père ?", choices, 2, 140);
	}

	@Override
	public int stateToGoTo() {
		return Hoorah.HOVERCAVESTATE;
	}

}