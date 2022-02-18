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

			if (currentTransition.conditionTypes.size() == 0) {
				RunOperation(currentTransition, operationVariableName, operationVariableValue);
				currentState = currentTransition.getTarget();
				return;
			}

			for (ConditionType condition : currentTransition.conditionTypes) {
				switch (condition) {
				case IFEQUALS:
					if (currentTransition.conditionVariableValue.equals(operationVariableValue)) {
						RunOperation(currentTransition, operationVariableName, operationVariableValue);
					}
					break;
				case IFLESSTHAN:
					if (operationVariableValue < currentTransition.conditionVariableValue) {
						RunOperation(currentTransition, operationVariableName, operationVariableValue);
					}
					break;
				case IFGREATERTHAN:
					if (operationVariableValue > currentTransition.conditionVariableValue) {
						RunOperation(currentTransition, operationVariableName, operationVariableValue);
					}
					break;
				default:
					break;
				}
			}

		}
	}

	private void RunOperation(Transition currentTransition, String operationVariableName,
			Integer operationVariableValue) {
		if (currentTransition.operationTypes.size() > 0) {
			switch (currentTransition.operationTypes.get(0)) {
			case DECREMENT:
				machine.integers.put(operationVariableName, operationVariableValue - 1);
				break;
			case INCREMENT:
				machine.integers.put(operationVariableName, operationVariableValue + 1);
				break;
			case SET:
				machine.integers.put(operationVariableName, currentTransition.operationVariableValue);
				break;
			default:
				break;
			}
		}
		currentState = currentTransition.getTarget();

	}

	public int getInteger(String string) {
		return machine.integers.get(string);
	}

}
