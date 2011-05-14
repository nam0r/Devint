package nodes;

public class QuestionCulture extends Question<ChoiceCulture> {

	private int points;
	
	public QuestionCulture(String text, String sound, int points) {
		super(text, sound);
		this.points = points;
	}
	
	public boolean isAnswer(int indice) {
		return choices.get(indice).getIsAnswer();
	}
	
	public int getPoints() {
		return this.points;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append(points);
		sb.append("\n");
		for(Choice choice : choices) {
			sb.append("\t");
			sb.append(choice.getWording());
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String getType() {
		return "C";
	}

}
