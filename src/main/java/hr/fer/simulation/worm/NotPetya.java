package hr.fer.simulation.worm;

import java.util.List;

import hr.fer.simulation.computers.Computer;

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
		
		for (Computer computer : remoteAccessComputers) { //drukcije iterirati, proci kroz sva racunala do kojih firewall ne blokira promet
			if (!computer.getInfectedStatus() && !computer.getOperatingSystem().getSmbVulnerabilityPatched()) {
				NotPetya notPetya = new NotPetya(computer); 
				notPetya.run();
			}
		}
		
		Mimikatz mimikatz = new Mimikatz(infectedComputer);
		String credentials = mimikatz.stealCredentials(); 
		
		for (Computer computer : remoteAccessComputers) {
			if (!computer.getInfectedStatus()) {
				NotPetya notPetya = new NotPetya(computer); 
				notPetya.run();
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
