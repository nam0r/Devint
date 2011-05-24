package nodes;

/**
 * A choice for a culture question
 * 
 * @author Afnarel
 */
public class ChoiceCulture extends Choice {

	private boolean isAnswer;
	
	public ChoiceCulture(String text, String sound, boolean isAnswer) {
		super(text, sound);
		
		this.isAnswer = isAnswer;
	}
	
	public boolean getIsAnswer() {
		return this.isAnswer;
	}

}
