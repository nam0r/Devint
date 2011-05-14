package nodes;

public class ChoiceScenario extends Choice {

	private int nodeToGoTo;
	
	public ChoiceScenario(String wording, String voice, int nodeToGoTo) {
		super(wording, voice);

		this.nodeToGoTo = nodeToGoTo;
		
	}
	
	public int getNodeToGoTo() {
		return this.nodeToGoTo;
	}

}
