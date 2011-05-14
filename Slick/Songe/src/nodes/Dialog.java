package nodes;

public class Dialog implements Event {
	
	private String sound;
	
	public Dialog(String sound) {
		this.sound = sound;
	}
	
	public String getSound() {
		return this.sound;
	}

	@Override
	public String getType() {
		return "D";
	}

}
