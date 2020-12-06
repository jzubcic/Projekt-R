package hr.fer.simulation.worm;

import java.util.List;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.Firewall;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class NotPetya extends Thread {

	private Computer infectedComputer; 

	
	public NotPetya(Computer infectedComputer) {
		this.infectedComputer = infectedComputer;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setInfectedStatus(true);
		
		List<Computer> remoteAccessComputers = infectedComputer.getRemoteAccessComputer();
		
		for (Computer computer : remoteAccessComputers) { //get all Computers that the firewall allows traffic to 
			if (!computer.getInfectedStatus() && !computer.getOperatingSystem().getSmbVulnerabilityPatched()) {
				NotPetya notPetya = new NotPetya(computer); 
				notPetya.run();
			}
		}
		
		Mimikatz mimikatz = new Mimikatz(infectedComputer);
		String credentials = mimikatz.stealCredentials(); 
		
		for (Subnetwork subnetwork : Firewall.getAllReachableSubnetworks(infectedComputer)) {
			for (Computer computer : subnetwork.getComputers()) {
				if (!computer.getInfectedStatus()) {
					NotPetya notPetya = new NotPetya(computer); 
					notPetya.run();
				}
			}
		}
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setDataEncrypted(true);
		
	}

}
