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
			Integer operationVariableValue = machine.integers.get(operationVariableName);

			if (currentTransition.conditionTypes.size() > 0) {
				switch (currentTransition.conditionTypes.get(0)) {
				case DECREMENT:
					machine.integers.put(operationVariableName, operationVariableValue - 1);
					break;
				case INCREMENT:
					machine.integers.put(operationVariableName, operationVariableValue + 1);
					break;
				case SET:
					machine.integers.put(operationVariableName, currentTransition.operationVariableValue);
					currentState = currentTransition.getTarget();
					break;
				case IFEQUALS:
					if (currentTransition.conditionVariableValue.equals(operationVariableValue)) {
						currentState = currentTransition.getTarget();
					}
					break;
				case IFLESSTHAN:
					if (currentTransition.conditionVariableValue < operationVariableValue) {
						currentState = currentTransition.getTarget();
					}
					break;
				case IFGREATERTHAN:
					if (currentTransition.conditionVariableValue > operationVariableValue) {
						currentState = currentTransition.getTarget();
					}
					break;
				default:
					currentState = currentTransition.getTarget();
					break;
				}
			} else {
				currentState = currentTransition.getTarget();
			}
		}

	}

	public int getInteger(String string) {
		return machine.integers.get(string);
	}

}
