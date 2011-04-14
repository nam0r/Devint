package nodes;

// On peut aussi penser faire une classe abstraite et la dériver
// en "ChoixScenario" et "ChoixNormal"
public class Choice {
	
	private String wording = null;
	private String voice = null;
	private int nodeToGoTo = -1;
	private boolean isAnswer = false;
	
	private Choice(String wording, String voice, boolean isAnswer) {
		this.wording = wording;
		this.voice = voice;
		this.isAnswer = isAnswer;
	}
	
	public Choice(String wording, String voice, boolean isAnswer, int nodeToGoTo) {
		this(wording, voice, isAnswer);
		this.nodeToGoTo = nodeToGoTo;
	}
	
	public String getWording() {
		return wording;
	}
	
	public String getVoice() {
		return voice;
	}
	
	public int getNodeToGoTo() {
		return nodeToGoTo;
	}
	
	public boolean getIsAnswer() {
		return isAnswer;
	}

}
