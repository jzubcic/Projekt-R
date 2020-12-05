package hr.fer.simulation.networkcomponents;

import java.util.HashMap;
import java.util.Map;

public class DHCPServer {

	private static Map<String, Subnetwork> allSubnetworks = new HashMap<>(); 
	
	public DHCPServer() {
		
	}
	
	public void addSubnetwork(Subnetwork subnetwork) {
		allSubnetworks.put(subnetwork.getName(), subnetwork); 
	}
	
	public static Map<String, Subnetwork> getAllSubnetworks() {
		return allSubnetworks; 
	}
	
	public Subnetwork getSubnetworkByName(String name) {
		return allSubnetworks.get(name); 
	}
}
