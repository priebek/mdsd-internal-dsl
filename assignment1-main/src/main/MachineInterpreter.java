package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
	private Machine machine;
	private State currentState;

	public void run(Machine m) {
		this.machine = m;
		currentState = machine.getInitialState();
	}

	public State getCurrentState() {
		return currentState;
	}

	public void processEvent(String string) {
		if (currentState.getTransitionByEvent(string) != null) {
			Transition currentTransition = currentState.getTransitionByEvent(string);
			String operationVariableName = currentTransition.operationVariableName;

			currentState = currentTransition.getTarget();

			switch (currentTransition.conditionTypes.get(0)) {
			case DECREMENT:
				machine.integers.put(operationVariableName, machine.integers.get(operationVariableName) - 1);
				break;
			case INCREMENT:
				machine.integers.put(operationVariableName, machine.integers.get(operationVariableName) + 1);
				break;
			default:
				// code block
			}
		}

	}

	public int getInteger(String string) {
		return machine.integers.get(string);
	}

}
