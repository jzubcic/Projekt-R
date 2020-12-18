package hr.fer.simulation.software;

public class OperatingSystem {

	private String name; 
	private boolean smbVulnerabilityPatched; 
	
	public OperatingSystem(String name) {
		this.name = name; 
		if (name.equals("Windows Server 2012 R2 6.3") || 
				name.equals("Windows 7 Service Pack 1 6.1")) { //Operating systems which do not have the SMB vulnerability patched
			smbVulnerabilityPatched = false; 
		} else {
			smbVulnerabilityPatched = true;
		}
	}
	
	public boolean getSmbVulnerabilityPatched() {
		return smbVulnerabilityPatched;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (smbVulnerabilityPatched ? 1231 : 1237);
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
		OperatingSystem other = (OperatingSystem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (smbVulnerabilityPatched != other.smbVulnerabilityPatched)
			return false;
		return true;
	}
	
	
}
