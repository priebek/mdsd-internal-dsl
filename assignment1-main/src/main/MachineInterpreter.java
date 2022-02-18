package main;

import main.metamodel.Machine;
import main.metamodel.State;

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
		// TODO Auto-generated method stub
	}

	public int getInteger(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

}
