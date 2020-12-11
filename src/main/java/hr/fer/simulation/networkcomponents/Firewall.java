package hr.fer.simulation.networkcomponents;

import java.util.ArrayList;
import java.util.List;

import hr.fer.simulation.computers.Computer;

public class Firewall {
	
	private List<PairOfSubnetworks> allowedConnections = new ArrayList<>(); 
	private List<Connection> allowedComputerConnections = new ArrayList<>(); 
	
	class PairOfSubnetworks {
		
		Subnetwork subnetwork1; 
		Subnetwork subnetwork2; 
		
		public PairOfSubnetworks(Subnetwork subnetwork1, Subnetwork subnetwork2) {
			this.subnetwork1 = subnetwork1; 
			this.subnetwork2 = subnetwork2;
		}
		
		public boolean isEqual(Subnetwork subnetwork1, Subnetwork subnetwork2) {
			if ((subnetwork1.equals(this.subnetwork1) && subnetwork2.equals(this.subnetwork2)) ||
					(subnetwork2.equals(this.subnetwork1) && subnetwork1.equals(this.subnetwork2))) {
				return true;
			} else {
				return false; 
			}
		}
		
		public Subnetwork getPair(Subnetwork subnetwork) {
			if (subnetwork.equals(subnetwork1)) return subnetwork2;
			if (subnetwork.equals(subnetwork2)) return subnetwork1;
			return null; 
		}
			
	}
	
	public boolean isTrafficAllowed(Subnetwork subnetwork1, Subnetwork subnetwork2) {
		if (subnetwork1.equals(subnetwork2)) {
			return true; 
		}
		
		for (PairOfSubnetworks pair : allowedConnections) {
			if (pair.isEqual(subnetwork1, subnetwork2)) {
				return true;
			}				
		}
		
		return false; 
	}
	
	public static List<Subnetwork> getAllReachableSubnetworks(Computer computer) {
		//TODO
		return null;
	}
	
}
