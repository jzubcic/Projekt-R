package hr.fer.simulation.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.ConnectionType;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Firewall;
import hr.fer.simulation.networkcomponents.Subnetwork;
import hr.fer.simulation.simulationstate.SimulationState;
import hr.fer.simulation.software.OperatingSystem;
import hr.fer.simulation.worm.NotPetya;

public class SimulationDemo {

	public static void main(String[] args) {
		DHCPServer dhcp = new DHCPServer();
		
		Subnetwork localNetwork = new Subnetwork("LAN", "192.168.53.0");
		
		int counter = 0; 
		for (int i = 11; i <= 20; i++) {
			localNetwork.addComputer(new Computer("Accountant Workstation " + String.valueOf(++counter),
													"192.168.53." + String.valueOf(i), 
													new OperatingSystem("Windows 10 v1703")));
		}		
		
		counter = 0; 
		for (int i = 21; i <= 30; i++) {
			localNetwork.addComputer(new Computer("Manager Workstation " + String.valueOf(++counter),
													"192.168.53." + String.valueOf(i), 
													new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		}		
		
		counter = 0;
		for (int i = 31; i <= 40; i++) {
			localNetwork.addComputer(new Computer("Developer Workstation " + String.valueOf(++counter),
													"192.168.53." + String.valueOf(i), 
													new OperatingSystem("Windows 10 v1703")));
		}		
		
		counter = 0;
		List<Computer> adminWorkstations = new ArrayList<>();
		for (int i = 41; i <= 43; i++) {
			Computer adminComputer = new Computer("Admin Workstation " + String.valueOf(++counter),
					"192.168.53." + String.valueOf(i), 
					new OperatingSystem("Windows 10 v1703"));
			localNetwork.addComputer(adminComputer);
			adminWorkstations.add(adminComputer);
		}	
		
		counter = 0;
		for (int i = 46; i <= 50; i++) {
			localNetwork.addComputer(new Computer("SecOp Workstation " + String.valueOf(++counter),
													"192.168.53." + String.valueOf(i), 
													new OperatingSystem("Windows 10 v1703")));
		}
		
		Subnetwork datacenter = new Subnetwork("Datacenter", "192.168.52.0");		
		datacenter.addComputer(new Computer("Backup server", "192.168.52.101", new OperatingSystem("Windows Server 2012 R2 6.3")));
		datacenter.addComputer(new Computer("Private web server", "192.168.52.102", new OperatingSystem("Windows Server 2012 R2 6.3")));
		datacenter.addComputer(new Computer("Database server", "192.168.52.103", new OperatingSystem("Windows Server 2012 R2 6.3")));
		datacenter.getPcByIp("192.168.52.103").setContainsCriticalData(true);
		datacenter.addComputer(new Computer("Domain Controller", "192.168.52.104", new OperatingSystem("Windows Server 2012 R2 6.3")));
		datacenter.getPcByIp("192.168.52.104").setContainsCriticalData(true);
		
		Subnetwork dmzNetwork = new Subnetwork("DMZ", "203.0.113.0");
		dmzNetwork.addComputer(new Computer("Public web server", "203.0.113.101", new OperatingSystem("Red Hat Enterprise Linux v7.3")));
		dmzNetwork.addComputer(new Computer("Mail server", "203.0.113.102", new OperatingSystem("Windows Server 2012 R2 6.3")));
		dmzNetwork.addComputer(new Computer("DNS server", "203.0.113.103", new OperatingSystem("Red Hat Enterprise Linux v7.3")));
		
		dhcp.addSubnetwork(localNetwork);
		dhcp.addSubnetwork(datacenter);
		dhcp.addSubnetwork(dmzNetwork);
		
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SFTP);
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SSH);
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SMB);
		
		Firewall.addAllowedConnection(adminWorkstations, localNetwork.getComputers(), ConnectionType.RDP);
		
		Firewall.addAllowedConnection(adminWorkstations, datacenter.getComputers(), ConnectionType.RDP);
		Firewall.addAllowedConnection(adminWorkstations, datacenter.getComputers(), ConnectionType.RPC);
		
		
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.102"), ConnectionType.HTTPS); // mail server
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.HTTPS); // public web server
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.103"), ConnectionType.HTTPS); // DNS
		
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.SMB);
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.102"), ConnectionType.SMB);
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.103"), ConnectionType.SMB);
		
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.104"), ConnectionType.LDAP);
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.102"), ConnectionType.HTTPS);
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.103"), ConnectionType.SMB);
		
		Firewall.addAllowedConnection(dmzNetwork.getComputers(), datacenter.getPcByIp("192.168.52.104"), ConnectionType.HTTPS);
		
		Firewall.addAllowedConnection(dmzNetwork.getPcByIp("203.0.113.101"), datacenter.getPcByIp("192.168.52.103"), ConnectionType.HTTPS);
		
		System.out.print("Please provide IP address from where the worm will start its spread: ");
		
		Scanner sc = new Scanner(System.in);
		String start = new String(); 
		
		Computer startingPoint = null; 
		while (startingPoint == null) {
			start = sc.nextLine();
			if ((startingPoint = dhcp.getPcByIp(start)) == null) {
				System.out.println("Network does not contain PC with given IP address.");
			}
		}
		
		NotPetya notPetya = new NotPetya(startingPoint);
		//NotPetya notPetya = new NotPetya(localNetwork.getPcByIp("192.168.53.41"));
		Thread thread = new Thread(notPetya); 
		
		thread.start();	
		
		SimulationState state = new SimulationState(dhcp); 
		Thread thread2 = new Thread(state); 
		thread2.start();
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
