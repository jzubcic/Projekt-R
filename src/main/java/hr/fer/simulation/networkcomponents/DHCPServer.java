package hr.fer.simulation.networkcomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.simulation.computers.Computer;

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
	
	public static List<Computer> getAllComputersInNetwork () {
		List<Computer> list = new ArrayList<>(); 
		for (Subnetwork subnetwork : allSubnetworks.values()) {
			for (Computer c : subnetwork.getComputers()) {
				list.add(c);
			}
		}
		return list; 
	}
	
	public static Computer getPcByIp(String ip) {
		for (Subnetwork subnetwork : allSubnetworks.values()) {
			if (subnetwork.getPcByIp(ip) != null) return subnetwork.getPcByIp(ip); 
		}
		return null; 
	}
}
