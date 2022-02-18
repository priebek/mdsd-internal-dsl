package main;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.Transition.ConditionType;

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
		integers.put(operationVariableName, i);
		AddConditionTypeAndName(Arrays.asList(ConditionType.SET), null);
		currentState.getTransitionByEvent(event).operationVariableName = operationVariableName;
		return this;
	}

	public StateMachine increment(String string) {
		integers.put(string, integers.get(string) + 1);
		AddConditionTypeAndName(Arrays.asList(ConditionType.INCREMENT), string);
		return this;
	}

	public StateMachine decrement(String string) {
		integers.put(string, integers.get(string) - 1);
		AddConditionTypeAndName(Arrays.asList(ConditionType.DECREMENT), string);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		currentState.getTransitionByEvent(event).conditionVariableValue = i;
		AddConditionTypeAndName(Arrays.asList(ConditionType.IFEQUALS), string);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		currentState.getTransitionByEvent(event).conditionVariableValue = i;
		AddConditionTypeAndName(Arrays.asList(ConditionType.IFGREATERTHAN), string);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		currentState.getTransitionByEvent(event).conditionVariableValue = i;
		AddConditionTypeAndName(Arrays.asList(ConditionType.IFLESSTHAN), string);
		return this;
	}

	private void AddConditionTypeAndName(Collection<ConditionType> conditionType, String conditionVariableName) {
		currentState.getTransitionByEvent(event).conditionTypes.addAll(conditionType);
		
		if (conditionVariableName != null) {
			currentState.getTransitionByEvent(event).conditionVariableName = conditionVariableName;
		}
	}

}
