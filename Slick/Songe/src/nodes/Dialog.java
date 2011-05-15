package nodes;

import main.Songe;

public class Dialog implements Event {
	
	private String sound;
	private String image;
	
	public Dialog(String sound, String image) {
		this.sound = sound;
		this.image = image;
	}
	
	public String getSound() {
		return this.sound;
	}
	
	public String getImage(){
		return image;
	}

	@Override
	public String getType() {
		return "D";
	}
	
	@Override
	public int getStateID() {
		return Songe.DIALOGSTATE;
	}

}
