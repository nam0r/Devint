package nodes;

/**
 * A scenario question
 * 
 * @author Afnarel
 */
public class QuestionScenario extends Question<ChoiceScenario> {

	public QuestionScenario(String text, String sound, int yes, int no) {
		super(text, sound);
		
		choices.add(new ChoiceScenario("Oui", "oui", yes));
		choices.add(new ChoiceScenario("Non", "non", no));
	}

	@Override
	public String getType() {
		return "S";
	}

}
