package hr.fer.simulation.networkcomponents;

import hr.fer.simulation.computers.Computer;

public class Connection {

	private Computer computer1; 
	private Computer computer2; 
	private ConnectionType type;
	
	public Connection(Computer computer1, Computer computer2, ConnectionType type) {
		super();
		this.computer1 = computer1;
		this.computer2 = computer2;
		this.type = type;
	} 	
}
