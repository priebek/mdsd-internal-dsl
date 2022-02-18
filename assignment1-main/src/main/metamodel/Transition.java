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
    public Integer operationVariableValue = null;
    
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
		return (operationVariableValue.equals(conditionVariableValue));
	}

	public boolean isConditionGreaterThan() {
		return (operationVariableValue > conditionVariableValue);
	}

	public boolean isConditionLessThan() {
		return (operationVariableValue < conditionVariableValue);
	}

	public boolean hasOperation() {
		return (operationVariableName != null);
	}

}
