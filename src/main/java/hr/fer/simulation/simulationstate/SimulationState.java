package hr.fer.simulation.simulationstate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.demo.SimulationVisualisation;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class SimulationState implements Runnable { 
	
	private DHCPServer dhcp; 
	private List<String> infectedComputers;
	private List<Computer> infectedPcs; 
	private List<String> encryptedComputers;
	private List<Computer> encryptedPcs; 
	private boolean turnOff; 
	private SimulationVisualisation visualisation;
	
	public SimulationState(DHCPServer dhcp) {
		this.dhcp = dhcp; 
	}
	
	public SimulationState(DHCPServer dhcp, boolean turnOff, SimulationVisualisation visualisation) {
		this.dhcp = dhcp; 
		this.turnOff = turnOff; 
		this.visualisation = visualisation;
	}

	@Override
	public void run() {
		System.out.println("Simulation has started.\n" + "-".repeat(50));
		
		infectedComputers = new ArrayList<>();
		infectedPcs = new ArrayList<>();
		encryptedComputers = new ArrayList<>();
		encryptedPcs = new ArrayList<>();
		
		
		Map<String, Subnetwork> allSubnetworks = dhcp.getAllSubnetworks();
		
		int totalComputersInNetwork = 0;
		for (Subnetwork subnetwork : allSubnetworks.values()) {
			totalComputersInNetwork += subnetwork.getComputers().size();
		}
		
		int timer = 0; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		boolean idsDetected = false; 
		boolean turnedOff = false; 
		while (timer != 75) {
			if (turnedOff) break; 
			for (Subnetwork subnetwork : allSubnetworks.values()) {
				for (Computer c : subnetwork.getComputers()) {
					if (c.getInfectedStatus() && !infectedComputers.contains(c.getIpAddress())) {					  
						System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The worm has spread to " + c + " from " + (c.getInfectedFrom() == null ? "user input." : c.getInfectedFrom()));
						infectedComputers.add(c.getIpAddress()); 
						infectedPcs.add(c); 
						visualisation.updateVisualisation(infectedPcs);
						timer = 0; 
					} else if (c.getDataEncrypted() && !encryptedComputers.contains(c.getIpAddress())) {
						System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The data on computer " + c + " is encrypted!");
						encryptedComputers.add(c.getIpAddress());
						encryptedPcs.add(c);
						visualisation.updateVisualisationEncrypted(encryptedPcs);
						if (c.getContainsCriticalData()) {
							System.out.println("[" + dtf.format(LocalDateTime.now()) + "] The critical data stored on computer " + c + " has been lost!");
						}
						timer = 0; 
					}
				}
				
				
				if (!idsDetected && (double) infectedComputers.size() / DHCPServer.getAllComputersInNetwork().size() > 0.3) {
					System.out.println("[" + dtf.format(LocalDateTime.now()) + 
						"] IDS has detected unusual amounts of traffic, an intrusion may have happened!"); 
					idsDetected = true;
					if (turnOff) {
						System.out.println("[" + dtf.format(LocalDateTime.now()) + 
						"] Turning off all computers in the network to prevent the spread...");
						 turnedOff = true; 
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
		System.out.println("The worm has spread to " + infectedComputers.size() +
							" computers out of "+ totalComputersInNetwork + " computers in the network.");
	}
	
	
}
