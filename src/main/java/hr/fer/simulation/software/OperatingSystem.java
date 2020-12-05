package hr.fer.simulation.software;

public class OperatingSystem {

	private String name; 
	private boolean smbVulnerabilityPatched; 
	
	public OperatingSystem(String name) {
		this.name = name; 
		if (name.equals("Windows Server 2012 R2 6.3") || 
				name.equals("Windows 7 Service Pack 1 6.1")) { //OS-ovi koji nemaju patchanu SMB ranjivost
			smbVulnerabilityPatched = false; 
		} else {
			smbVulnerabilityPatched = true;
		}
	}
	
	public boolean getSmbVulnerabilityPatched() {
		return smbVulnerabilityPatched;
	}
	
}
