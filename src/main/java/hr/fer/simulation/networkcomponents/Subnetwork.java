package hr.fer.simulation.networkcomponents;

import java.util.ArrayList;
import java.util.List;

import hr.fer.simulation.computers.Computer;

public class Subnetwork {

	private String name; 
	private List<Computer> computers = new ArrayList<>();
	
	public Subnetwork(String name, List<Computer> computers) {
		this.name = name;
		this.computers = computers;
	}

	public Subnetwork(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void addComputer(Computer c) {
		computers.add(c);
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	} 
	
	public Computer getPcByIp(String ipAddress) {
		for (Computer c : computers) {
			if (c.getIpAddress().equals(ipAddress)) {
				return c; 
			}
		}
		return null; 
	}
	
	
	
	
}
