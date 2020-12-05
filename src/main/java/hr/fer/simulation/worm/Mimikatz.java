package hr.fer.simulation.worm;

import hr.fer.simulation.computers.Computer;

public class Mimikatz {

	private Computer computer; 
	
	public Mimikatz(Computer computer) {
		this.computer = computer; 
	}
	
	public String stealCredentials() {
		return computer.getCredentials();
	}
}
