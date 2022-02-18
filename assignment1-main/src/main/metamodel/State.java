// State.java
package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	private String name;
	private List<Transition> transitions = new ArrayList<>();

	public State(String stateName) {
		this.name = stateName;
	}

	public Object getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String eventName) {
		return transitions.stream().filter(x -> x.getEvent().equals(eventName)).findFirst().orElseGet(() -> null);
	}

	public List<Transition> getAllTransitionByEvent(String eventName) {
		return transitions.stream().filter(x -> x.getEvent().equals(eventName)).toList();
	}

	public Transition getLastTransitionByEvent(String eventName) {
		List<Transition> transitions = getAllTransitionByEvent(eventName);
		return transitions.get(transitions.size() - 1);
	}

}
