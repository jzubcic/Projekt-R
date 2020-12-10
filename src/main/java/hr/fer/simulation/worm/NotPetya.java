package hr.fer.simulation.worm;

import java.util.List;
import java.util.Random;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Firewall;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class NotPetya implements Runnable {

	private Computer infectedComputer; 

	
	public NotPetya(Computer infectedComputer) {
		this.infectedComputer = infectedComputer;
	}
	
	@Override
	public void run() {
		
		try {
			Random random = new Random(); 
			Thread.sleep(random.nextInt(4000 - 500) + 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setInfectedStatus(true);
		
		//List<Computer> remoteAccessComputers = infectedComputer.getRemoteAccessComputer();
		
		for (Computer computer : DHCPServer.getAllComputersInNetwork()) { //get all Computers that the firewall allows traffic to 
			if (!computer.getInfectedStatus() && !computer.getOperatingSystem().getSmbVulnerabilityPatched()) {
				NotPetya notPetya = new NotPetya(computer); 
				Thread thread = new Thread(notPetya);
				computer.setInfectedFrom(infectedComputer);
				thread.start();
			}
		}
		
		/*Mimikatz mimikatz = new Mimikatz(infectedComputer);
		String credentials = mimikatz.stealCredentials(); 
		
		for (Subnetwork subnetwork : Firewall.getAllReachableSubnetworks(infectedComputer)) {
			for (Computer computer : subnetwork.getComputers()) {
				if (!computer.getInfectedStatus()) {
					NotPetya notPetya = new NotPetya(computer); 
					Thread thread = new Thread(notPetya); 
					thread.start();
				}
			}
		}*/
				
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		infectedComputer.setDataEncrypted(true);
		
	}

}
