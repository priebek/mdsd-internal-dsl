// Transition.java
package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Transition {

	public enum OperationType {
		SET, INCREMENT, DECREMENT
	}

	public enum ConditionType {
		IFEQUALS, IFGREATERTHAN, IFLESSTHAN
	}

	private State targetState;
	private String event;
	public String operationVariableName = null;
	public Integer operationVariableValue = null;

	public List<OperationType> operationTypes = new ArrayList<>();

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
		return (operationTypes.contains(OperationType.SET));
	}

	public boolean hasIncrementOperation() {
		return (operationTypes.contains(OperationType.INCREMENT));
	}

	public boolean hasDecrementOperation() {
		return (operationTypes.contains(OperationType.DECREMENT));
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
		return (conditionTypes.contains(ConditionType.IFEQUALS));
	}

	public boolean isConditionGreaterThan() {
		return (conditionTypes.contains(ConditionType.IFGREATERTHAN));
	}

	public boolean isConditionLessThan() {
		return (conditionTypes.contains(ConditionType.IFLESSTHAN));
	}

	public boolean hasOperation() {
		return (operationVariableName != null);
	}

}
