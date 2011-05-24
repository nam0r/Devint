package nodes;

/**
 * A choice for a question
 * 
 * @author Afnarel
 */
public abstract class Choice {
	
	protected String text = null;
	protected String sound = null;
	
	public Choice(String text, String sound) {
		this.text = text;
		this.sound = sound;
	}
	
	public String getWording() {
		return text;
	}
	
	public String getVoice() {
		return sound;
	}

}
