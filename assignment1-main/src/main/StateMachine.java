package main;

import java.util.HashMap;
import java.util.Map;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine {
	private Map<String, State> states = new HashMap<>();
	private State currentState;
	private State initialState;
	private String event;

	public Machine build() {
		return new Machine(states.values(), initialState);
	}

	public StateMachine state(String stateName) {
//		if (states.containsKey(stateName)) {
//			currentState = states.get(stateName);
//		} else {
			currentState = new State(stateName);
			states.put(stateName, currentState);
//		}

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
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine set(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine increment(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine decrement(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifEquals(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifLessThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
