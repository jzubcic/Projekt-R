package hr.fer.simulation.networkcomponents;

import hr.fer.simulation.computers.Computer;

public class Connection {

	private Computer computer1; 
	private Computer computer2; 
	private ConnectionType type;
	
	public Connection(Computer computer1, Computer computer2, ConnectionType type) {
		this.computer1 = computer1;
		this.computer2 = computer2;
		this.type = type;
	}

	public Computer getComputer1() {
		return computer1; 
	}
	
	public Computer getComputer2() {
		return computer2;
	}

	public ConnectionType getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computer1 == null) ? 0 : computer1.hashCode());
		result = prime * result + ((computer2 == null) ? 0 : computer2.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (computer1 == null) {
			if (other.computer1 != null)
				return false;
		} else if (!computer1.equals(other.computer1))
			return false;
		if (computer2 == null) {
			if (other.computer2 != null)
				return false;
		} else if (!computer2.equals(other.computer2))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	
	
}
