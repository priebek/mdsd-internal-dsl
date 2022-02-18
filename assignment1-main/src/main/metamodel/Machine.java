// Machine.java
package main.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {
	private List<State> states = new ArrayList<State>();
	private State initialState;
	public Map<String, Integer> integers = new HashMap<>();

	public Machine(Collection<State> states, State initialState, Map<String, Integer> integers) {
		super();
		this.states.addAll(states);
		this.initialState = initialState;
		this.integers = integers;
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		for (State state : states) {
			if (state.getName().equals(string))
				return state;
		}
		return null;
	}

	public int numberOfIntegers() {
		return integers.size();
	}

	public boolean hasInteger(String string) {
		return integers.containsKey(string);
	}

}
