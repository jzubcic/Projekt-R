package hr.fer.simulation.computers;

import java.util.List;

import hr.fer.simulation.software.OperatingSystem;

public class Computer {

	private String name; 
	private String ipAddress; 
	private OperatingSystem operatingSystem; 
	private boolean infectedStatus; 
	private String credentials; 
	private List<Computer> remoteAccessComputers;
	private Computer infectedFrom; 
	private boolean dataEncrypted = false; 
	
	
	
	public Computer(String name, String ipAddress, OperatingSystem operatingSystem) {
		super();
		this.name = name;
		this.ipAddress = ipAddress;
		this.operatingSystem = operatingSystem;
	}

	public void setName(String name) {
		this.name = name; 
	}
	
	public String getName() {
		return name; 
	}
	public String getCredentials() {
		return credentials; 
	}
	
	public boolean getInfectedStatus() {
		return infectedStatus; 
	}
	
	public void setInfectedStatus(boolean status) {
		infectedStatus = status; 
	}
	
	public void setDataEncrypted (boolean encrypted) {
		dataEncrypted = encrypted; 
	}
	
	public OperatingSystem getOperatingSystem() {
		return operatingSystem; 
	}
	
	public String getIpAddress() {
		return ipAddress; 
	}
	
	public List<Computer> getRemoteAccessComputer() {
		return remoteAccessComputers;
	}
	
	public boolean getDataEncrypted() {
		return dataEncrypted; 
	}
	
	public String toString() {
		return name + " " + ipAddress; 
	}
	
	public void setInfectedFrom(Computer computer) {
		infectedFrom = computer;
	}
	
	public Computer getInfectedFrom() {
		return infectedFrom; 
	}
}
