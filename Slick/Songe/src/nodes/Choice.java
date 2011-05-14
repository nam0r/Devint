package nodes;

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
