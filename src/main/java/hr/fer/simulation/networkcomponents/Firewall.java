package hr.fer.simulation.networkcomponents;

import java.util.ArrayList;
import java.util.List;

import hr.fer.simulation.computers.Computer;

public class Firewall {
	 
	private static List<Connection> allowedComputerConnections = new ArrayList<>(); 
	
	public Firewall() {
		
	}
	
	
	
	
	public static boolean isTrafficAllowed(Computer c1, Computer c2, ConnectionType type) {
		if (allowedComputerConnections.contains(new Connection(c1, c2, type))) return true; 
		return false; 
	}
	
	public static List<Computer> getAllReachableComputersMultiple (Computer computer, ConnectionType ... types) {
		List<Computer> reachableComputers = new ArrayList<>();
		
		for (ConnectionType type : types) {
			reachableComputers.addAll(getAllReachableComputers(computer, type));
		}
		return reachableComputers;
	}
	
	public static List<Computer> getAllReachableComputers (Computer computer, ConnectionType type) {
		List<Computer> reachableComputers = new ArrayList<>();
		
		for (Connection connection : allowedComputerConnections) {
			if (connection.getComputer1().equals(computer) && connection.getType().equals(type)) {
				reachableComputers.add(connection.getComputer2());
			}
		}
		
		return reachableComputers;
	}
	
	public static void addAllowedConnection(Computer computer1, Computer computer2, ConnectionType type) {
		allowedComputerConnections.add(new Connection(computer1, computer2, type)); 
	}
	
	public static void addAllowedConnection(List<Computer> computers1, List<Computer> computers2, ConnectionType type) {
		for (Computer c1 : computers1) {
			for (Computer c2 : computers2) {
				allowedComputerConnections.add(new Connection(c1, c2, type)); 
			}
		}
	}
	
	public static void addAllowedConnection(List<Computer> computers, Computer computer, ConnectionType type) {
		for (Computer c : computers) {
			allowedComputerConnections.add(new Connection(c, computer, type)); 
		}
	}
}
