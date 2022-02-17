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
		for (Transition transition : transitions) {
			if (transition.getEvent().equals(eventName))
				return transition;
		}
		return null;
	}
	
}
