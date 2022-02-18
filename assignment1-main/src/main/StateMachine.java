package main;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.Transition.ConditionType;
import main.metamodel.Transition.OperationType;

public class StateMachine {
	private Map<String, State> states = new HashMap<>();
	private State currentState;
	private State initialState;
	private String event;
	private Map<String, Integer> integers = new HashMap<>();

	public Machine build() {
		return new Machine(states.values(), initialState, integers);
	}

	public StateMachine state(String stateName) {
		if (states.containsKey(stateName)) {
			currentState = states.get(stateName);
		} else {
			currentState = new State(stateName);
			states.put(stateName, currentState);
		}

		return this;
	}

	public StateMachine initial() {
		initialState = currentState;
		return this;
	}

	public StateMachine when(String eventName) {
		event = eventName;
		return this;
	}

	public StateMachine to(String targetState) {
		if (!states.containsKey(targetState)) {
			State newState = new State(targetState);
			states.put(targetState, newState);
		}

		Transition t = new Transition(states.get(targetState), event);
		currentState.getTransitions().add(t);
		return this;
	}

	public StateMachine integer(String string) {
		integers.put(string, 0);
		return this;
	}

	public StateMachine set(String operationVariableName, int i) {
		currentState.getTransitionByEvent(event).operationVariableValue = i;
		SetOperationInfo(operationVariableName);
		AddOperationType(Arrays.asList(OperationType.SET));
		return this;
	}

	public StateMachine increment(String string) {
		SetOperationInfo(string);
		AddOperationType(Arrays.asList(OperationType.INCREMENT));
		return this;
	}

	public StateMachine decrement(String string) {
		SetOperationInfo(string);
		AddOperationType(Arrays.asList(OperationType.DECREMENT));
		return this;
	}
	
	private void AddOperationType(Collection<OperationType> operationType) {
		currentState.getTransitionByEvent(event).operationTypes.addAll(operationType);
	}
	
	private void SetOperationInfo(String name) {
		currentState.getTransitionByEvent(event).operationVariableName = name;
	}

	public StateMachine ifEquals(String string, int i) {
		AddConditionInfo(string, i);
		AddConditionType(Arrays.asList(ConditionType.IFEQUALS));
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		AddConditionInfo(string, i);
		AddConditionType(Arrays.asList(ConditionType.IFGREATERTHAN));
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		AddConditionInfo(string, i);
		AddConditionType(Arrays.asList(ConditionType.IFLESSTHAN));
		return this;
	}

	private void AddConditionType(Collection<ConditionType> conditionType) {
		currentState.getTransitionByEvent(event).conditionTypes.addAll(conditionType);
	}

	private void AddConditionInfo(String name, Integer value) {
		currentState.getTransitionByEvent(event).conditionVariableName = name;
		currentState.getTransitionByEvent(event).conditionVariableValue = value;
		SetOperationInfo(name);
	}
}
