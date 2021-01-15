package hr.fer.simulation.networkcomponents;

import java.util.ArrayList;
import java.util.List;

import hr.fer.simulation.computers.Computer;

public class Subnetwork {

	private String name; 
	private List<Computer> computers = new ArrayList<>();
	private String ipAddress; //IP address of subnetwork in format XXX.XXX.XX.0
	
	public Subnetwork(String name, String ipAddress, List<Computer> computers) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.computers = computers;
	}
	
	public Subnetwork(String name) {
		this.name = name; 
	}

	public Subnetwork(String name, String ipAddress) {
		this.name = name;
		this.ipAddress = ipAddress;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public boolean containsComputer(Computer computer) {
		for (Computer c : computers) {
			if (c.equals(computer)) {
				return true; 
			}
		}
		return false; 
	}
	
}
