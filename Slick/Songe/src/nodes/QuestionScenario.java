package nodes;

public class QuestionScenario extends Question<ChoiceScenario> {

	public QuestionScenario(String text, String sound, int yes, int no) {
		super(text, sound);
		
		choices.add(new ChoiceScenario("Oui", "oui", yes));
	}

	@Override
	public String getType() {
		return "S";
	}

}
