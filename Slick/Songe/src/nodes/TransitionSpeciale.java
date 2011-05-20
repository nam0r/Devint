package nodes;

public class TransitionSpeciale implements Event {
	
	private int stateID;
	
	private int success;
	private int failure;
	
	public TransitionSpeciale(int stateID, int success, int failure) {
		this.stateID = stateID;
		this.success = success;
		this.failure = failure;
	}
	
	@Override
	public int getStateID() {
		return this.stateID;
	}

	@Override
	public String getType() {
		return "TS";
	}
	
	public int getSuccess() {
		return success;
	}
	
	public int getFailure() {
		return failure;
	}

}
