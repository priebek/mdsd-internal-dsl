package main.metamodel;

public class Transition {
    private State targetState;
    private String event;
    public boolean setOperation = false;
    public boolean incrementOperation = false;
    public boolean decrementOperation = false;
    public String operationVariableName = null;
    
    public Transition(State targetState, String event) {
    	this.targetState = targetState;
    	this.event = event;
    }
    
	public Object getEvent() {
		return event;
	}

	public State getTarget() {
		return targetState;
	}

	public boolean hasSetOperation() {
		return setOperation;
	}

	public boolean hasIncrementOperation() {
		return incrementOperation;
	}

	public boolean hasDecrementOperation() {
		return decrementOperation;
	}

	public Object getOperationVariableName() {
		return operationVariableName;
	}

	public boolean isConditional() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getConditionVariableName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getConditionComparedValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isConditionEqual() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionGreaterThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionLessThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasOperation() {
		// TODO Auto-generated method stub
		return false;
	}

}
