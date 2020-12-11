package hr.fer.simulation.simulationstate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class SimulationState implements Runnable { 
	
	private DHCPServer dhcp; 
	private List<String> infectedComputers;
	private List<String> encryptedComputers;
	
	public SimulationState(DHCPServer dhcp) {
		this.dhcp = dhcp; 
	}

	@Override
	public void run() {
		System.out.println("Simulation has started.\n" + "-".repeat(50));
		
		infectedComputers = new ArrayList<>();
		encryptedComputers = new ArrayList<>();
		
		Map<String, Subnetwork> allSubnetworks = dhcp.getAllSubnetworks();
		int timer = 0; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		while (timer != 50) {
			for (Subnetwork subnetwork : allSubnetworks.values()) {
				for (Computer c : subnetwork.getComputers()) {
					if (c.getInfectedStatus() && !infectedComputers.contains(c.getIpAddress())) {					  
						System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The worm has spread to " + c + " from " + (c.getInfectedFrom() == null ? "user input." : c.getInfectedFrom()));
						infectedComputers.add(c.getIpAddress()); 
						
						timer = 0; 
					} else if (c.getDataEncrypted() && !encryptedComputers.contains(c.getIpAddress())) {
						System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The data on computer " + c + " is encrypted!");
						encryptedComputers.add(c.getIpAddress());
						if (c.getContainsCriticalData()) {
							System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The critical data stored on computer " + c + " has been lost!");
						}
						timer = 0; 
					}
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timer++;
		}
		
		System.out.println("-".repeat(50) + "\nThe worm has stopped spreading.");
	}
	
	
}
