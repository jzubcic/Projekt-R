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
	private boolean containsCriticalData = false; 
	
	
	
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
	
	public void setContainsCriticalData(boolean criticalData) {
		containsCriticalData = criticalData; 
	}
	
	public boolean getContainsCriticalData() {
		return containsCriticalData; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (containsCriticalData ? 1231 : 1237);
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + (dataEncrypted ? 1231 : 1237);
		result = prime * result + ((infectedFrom == null) ? 0 : infectedFrom.hashCode());
		result = prime * result + (infectedStatus ? 1231 : 1237);
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((remoteAccessComputers == null) ? 0 : remoteAccessComputers.hashCode());
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
		Computer other = (Computer) obj;
		if (containsCriticalData != other.containsCriticalData)
			return false;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (dataEncrypted != other.dataEncrypted)
			return false;
		if (infectedFrom == null) {
			if (other.infectedFrom != null)
				return false;
		} else if (!infectedFrom.equals(other.infectedFrom))
			return false;
		if (infectedStatus != other.infectedStatus)
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operatingSystem == null) {
			if (other.operatingSystem != null)
				return false;
		} else if (!operatingSystem.equals(other.operatingSystem))
			return false;
		if (remoteAccessComputers == null) {
			if (other.remoteAccessComputers != null)
				return false;
		} else if (!remoteAccessComputers.equals(other.remoteAccessComputers))
			return false;
		return true;
	}

	
	
}
