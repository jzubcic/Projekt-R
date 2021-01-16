package hr.fer.simulation.demo;

import java.awt.Color;
import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Subnetwork;

public class SimulationVisualisation {

	private static Graph networkGraph = new SingleGraph("Network"); 
	
	public SimulationVisualisation(DHCPServer dhcp) {
		Node centralNode = networkGraph.addNode("Core router"); 
		centralNode.setAttribute("ui.label",  "Central router");
		centralNode.setAttribute("ui.size", 300);
		centralNode.setAttribute("ui.style", "fill-color: rgb(0,0,255);");
		for (Subnetwork subnetwork : dhcp.getAllSubnetworks().values()) {
			Node switchNode = networkGraph.addNode(subnetwork.getName() + " switch"); 
			switchNode.setAttribute("ui.label", switchNode.getId());
			switchNode.setAttribute("ui.style", "fill-color: rgb(255,255,0);");
			networkGraph.addEdge(subnetwork.getName(), networkGraph.getNode("Core router"), switchNode);
			
			for (Computer c : subnetwork.getComputers()) {
				Node computerNode = networkGraph.addNode(c.getName()); 
				computerNode.setAttribute("ui.label", c.getName());
				computerNode.setAttribute("ui.style", "fill-color: rgb(0,0,0);");
				networkGraph.addEdge(c.getName(), switchNode, computerNode); 
			}
		}
		
		System.setProperty("org.graphstream.ui", "swing");
		networkGraph.display();
	}
	
	public static void updateVisualisation(List<Computer> infectedComputers) {
		for (Computer c : infectedComputers) {
			if (!networkGraph.getNode(c.getName()).getAttribute("ui.style").equals("fill-color: rgb(255,0,0);"))
				networkGraph.getNode(c.getName()).setAttribute("ui.style", "fill-color: rgb(233,150,122);");
		}
	}
	
	public static void updateVisualisationEncrypted(List<Computer> encryptedComputers) {
		for (Computer c : encryptedComputers) {
			networkGraph.getNode(c.getName()).setAttribute("ui.style", "fill-color: rgb(255,0,0);");
		}
	}
	
}
