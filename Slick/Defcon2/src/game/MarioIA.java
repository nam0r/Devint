package game;

import main.Hoorah;
import questions.Question;
import actors.IA;

/**
 * Mario IA
 */
public class MarioIA extends IA {
	
	public MarioIA(int x, int y) {
		super("res/img/mariowalk.png", 3, x, y, 20, 31);
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