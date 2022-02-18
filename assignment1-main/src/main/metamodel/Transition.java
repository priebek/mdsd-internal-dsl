package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Transition {
	
	public enum ConditionType {
		SET, INCREMENT, DECREMENT, IFEQUALS, IFGREATERTHAN, IFLESSTHAN
	}
	
    private State targetState;
    private String event;
    public String operationVariableName = null;
    
    public List<ConditionType> conditionTypes = new ArrayList<>();
    public String conditionVariableName = null;
    public Integer conditionVariableValue = null;
    
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
		return (conditionTypes.contains(ConditionType.SET));
	}

	public boolean hasIncrementOperation() {
		return (conditionTypes.contains(ConditionType.INCREMENT));
	}

	public boolean hasDecrementOperation() {
		return (conditionTypes.contains(ConditionType.DECREMENT));
	}

	public Object getOperationVariableName() {
		return operationVariableName;
	}

	public boolean isConditional() {
		return (conditionTypes.size() > 0);
	}

	public Object getConditionVariableName() {
		return conditionVariableName;
	}

	public Integer getConditionComparedValue() {
		return conditionVariableValue;
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
