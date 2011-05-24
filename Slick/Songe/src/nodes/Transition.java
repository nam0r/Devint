package nodes;

/**
 * A basic transition
 * 
 * @author Afnarel
 */
public class Transition implements Event {
	
	private int stateID;
	
	public Transition(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getStateID() {
		return this.stateID;
	}

	@Override
	public String getType() {
		return "T";
	}

}
