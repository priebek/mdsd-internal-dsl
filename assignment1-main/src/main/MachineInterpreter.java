package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.Transition.ConditionType;

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
				for (ConditionType condition : currentTransition.conditionTypes) {
					switch (condition) {
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
						if (operationVariableValue < currentTransition.conditionVariableValue) {
							currentState = currentTransition.getTarget();
						}
						break;
					case IFGREATERTHAN:
						if (operationVariableValue > currentTransition.conditionVariableValue) {
							currentState = currentTransition.getTarget();
						}
						break;
					default:
						currentState = currentTransition.getTarget();
						break;
					}
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
