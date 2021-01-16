package hr.fer.simulation.worm;

import java.util.Random;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.ConnectionType;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Firewall;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class NotPetya implements Runnable {

	private Computer infectedComputer;
	private boolean remoteShareSpread; 
	private boolean eternalBlueSpread; 

	
	public NotPetya(Computer infectedComputer, boolean remoteShareSpread,
						boolean eternalBlueSpread) {
		this.infectedComputer = infectedComputer;
		this.remoteShareSpread = remoteShareSpread;
		this.eternalBlueSpread = eternalBlueSpread;
	}
	
	@Override
	public void run() {
		Random random = new Random(); 
		try {		
			Thread.sleep(random.nextInt(4000 - 500) + 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setInfectedStatus(true);
		

		Subnetwork currentSubnetwork = null; 
		for (Subnetwork subnetwork : DHCPServer.getAllSubnetworks().values()) {
			if (subnetwork.containsComputer(infectedComputer)) currentSubnetwork = subnetwork;
		}
		
		if (eternalBlueSpread) {
			for (Computer computer : currentSubnetwork.getComputers()) {
				spreadBySMB(computer); 
			}
		
			for (Computer computer : Firewall.getAllReachableComputers(infectedComputer, ConnectionType.SMB)) { //get all Computers that the firewall allows traffic to 
				spreadBySMB(computer); 
			}
		}
		
		if (remoteShareSpread) {
			Mimikatz mimikatz = new Mimikatz(infectedComputer);
			String credentials;
		
			try {
				Thread.sleep(1500);
				credentials = mimikatz.stealCredentials(); // Mimikatz needs a while to extract the credentials
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (Computer computer : Firewall.getAllReachableComputersMultiple(infectedComputer, ConnectionType.RDP,
																							 ConnectionType.SSH)) {
					if (computer != null && !computer.getInfectedStatus()) {
						try {
							Thread.sleep(random.nextInt(750 - 500) + 500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						NotPetya notPetya = new NotPetya(computer, remoteShareSpread, eternalBlueSpread); 
						Thread thread = new Thread(notPetya); 
						computer.setInfectedFrom(infectedComputer);
						thread.start();
					}			
			}
		}
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setDataEncrypted(true);
		
	}
	
	private void spreadBySMB(Computer computer) {
		if (!computer.getInfectedStatus() && !computer.getOperatingSystem().getSmbVulnerabilityPatched()) {
			NotPetya notPetya = new NotPetya(computer, remoteShareSpread, eternalBlueSpread); 
			Thread thread = new Thread(notPetya);
			computer.setInfectedFrom(infectedComputer);
			thread.start();
		}
	}

}
