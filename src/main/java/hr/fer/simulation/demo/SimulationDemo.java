package hr.fer.simulation.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import hr.fer.simulation.computers.Computer;
import hr.fer.simulation.networkcomponents.ConnectionType;
import hr.fer.simulation.networkcomponents.DHCPServer;
import hr.fer.simulation.networkcomponents.Firewall;
import hr.fer.simulation.networkcomponents.Subnetwork;
import hr.fer.simulation.simulationstate.SimulationState;
import hr.fer.simulation.software.OperatingSystem;
import hr.fer.simulation.worm.NotPetya;

public class SimulationDemo {

	private static DHCPServer dhcp; 
	
	public static void main(String[] args) {
		dhcp = new DHCPServer();
		
		loadNetworkFromJSON(); 
		configureFirewallFromJSON(); 
		/*
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
		
		Subnetwork zagrebRegional = new Subnetwork("Zagreb regional branch", "192.168.54.50");
		Subnetwork splitRegional = new Subnetwork("Split regional branch", "192.168.54.100");
		Subnetwork rijekaRegional = new Subnetwork("Rijeka regional branch", "192.168.54.150");
		
		counter = 0; 
		for (int i = 60; i <= 69; i++) {
			zagrebRegional.addComputer(new Computer("Zagreb Bank counter " + String.valueOf(++counter),
					"192.168.54." + String.valueOf(i),
					new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		}
		zagrebRegional.addComputer(new Computer("Zagreb Bank officer Workstation", "192.168.54.51", new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		zagrebRegional.addComputer(new Computer("Zagreb Regional admin workstation", "192.168.54.52",
									new OperatingSystem("Windows 10 v1703")));
		
		counter = 0; 
		for (int i = 110; i <= 119; i++) {
			splitRegional.addComputer(new Computer("Split Bank counter " + String.valueOf(++counter),
					"192.168.54." + String.valueOf(i),
					new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		}
		splitRegional.addComputer(new Computer("Split Bank officer Workstation", "192.168.54.101", new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		splitRegional.addComputer(new Computer("Split Regional admin workstation", "192.168.54.102",
									new OperatingSystem("Windows 10 v1703")));
		
		counter = 0; 
		for (int i = 160; i <= 169; i++) {
			rijekaRegional.addComputer(new Computer("Rijeka Bank counter " + String.valueOf(++counter),
					"192.168.54." + String.valueOf(i),
					new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		}
		rijekaRegional.addComputer(new Computer("Rijeka Bank officer Workstation", "192.168.54.151", new OperatingSystem("Windows 7 Service Pack 1 6.1")));
		rijekaRegional.addComputer(new Computer("Rijeka Regional admin workstation", "192.168.54.152",
									new OperatingSystem("Windows 10 v1703")));
		
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
		
		dhcp.addSubnetwork(splitRegional);
		dhcp.addSubnetwork(zagrebRegional);
		dhcp.addSubnetwork(rijekaRegional);
		dhcp.addSubnetwork(localNetwork);
		dhcp.addSubnetwork(datacenter);
		dhcp.addSubnetwork(dmzNetwork);
		*//*
		//DODANO
		Firewall.addAllowedConnection(splitRegional.getPcByIp("192.168.54.102"), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.RDP);
		Firewall.addAllowedConnection(zagrebRegional.getPcByIp("192.168.54.52"), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.RDP);
		Firewall.addAllowedConnection(rijekaRegional.getPcByIp("192.168.54.152"), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.RDP); 
		
		//DODANO
		Firewall.addAllowedConnection(splitRegional.getComputers(), datacenter.getPcByIp("192.168.52.102"), ConnectionType.HTTP);
		Firewall.addAllowedConnection(zagrebRegional.getComputers(), datacenter.getPcByIp("192.168.52.102"), ConnectionType.HTTP);
		Firewall.addAllowedConnection(rijekaRegional.getComputers(), datacenter.getPcByIp("192.168.52.102"), ConnectionType.HTTP);
		
		//DODANO
		Firewall.addAllowedConnection(splitRegional.getComputers(), datacenter.getPcByIp("192.168.52.103"), ConnectionType.SMB);
		Firewall.addAllowedConnection(zagrebRegional.getComputers(), datacenter.getPcByIp("192.168.52.103"), ConnectionType.SMB);
		Firewall.addAllowedConnection(rijekaRegional.getComputers(), datacenter.getPcByIp("192.168.52.103"), ConnectionType.SMB);
		
		//DODANO
		Firewall.addAllowedConnection(datacenter.getComputers(), splitRegional.getPcByIp("192.168.54.102"),  ConnectionType.RDP);
		Firewall.addAllowedConnection(datacenter.getComputers(), zagrebRegional.getPcByIp("192.168.54.52"),  ConnectionType.RDP);
		Firewall.addAllowedConnection(datacenter.getComputers(), rijekaRegional.getPcByIp("192.168.54.152"),  ConnectionType.RDP);
		
		//DODANO
		Firewall.addAllowedConnection(datacenter.getComputers(), splitRegional.getPcByIp("192.168.54.102"),  ConnectionType.RPC);
		Firewall.addAllowedConnection(datacenter.getComputers(), zagrebRegional.getPcByIp("192.168.54.52"),  ConnectionType.RPC);
		Firewall.addAllowedConnection(datacenter.getComputers(), rijekaRegional.getPcByIp("192.168.54.152"),  ConnectionType.RPC);
		
		//DODANO
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SFTP);
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SSH);
		Firewall.addAllowedConnection(adminWorkstations, dmzNetwork.getComputers(), ConnectionType.SMB);
		
		//DODANO
		Firewall.addAllowedConnection(adminWorkstations, localNetwork.getComputers(), ConnectionType.RDP);
		
		//DODANO
		Firewall.addAllowedConnection(adminWorkstations, datacenter.getComputers(), ConnectionType.RDP);
		Firewall.addAllowedConnection(adminWorkstations, datacenter.getComputers(), ConnectionType.RPC);
		
		//DODANO
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.102"), ConnectionType.HTTPS); // mail server
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.HTTPS); // public web server
		Firewall.addAllowedConnection(localNetwork.getComputers(), dmzNetwork.getPcByIp("203.0.113.103"), ConnectionType.HTTPS); // DNS
		
		
		//DODANO
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.101"), ConnectionType.SMB);
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.102"), ConnectionType.SMB);
		Firewall.addAllowedConnection(datacenter.getPcByIp("192.168.52.101"), dmzNetwork.getPcByIp("203.0.113.103"), ConnectionType.SMB);
		
		//DODANO
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.104"), ConnectionType.LDAP);
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.102"), ConnectionType.HTTPS);
		Firewall.addAllowedConnection(localNetwork.getComputers(), datacenter.getPcByIp("192.168.52.103"), ConnectionType.SMB);
		
		Firewall.addAllowedConnection(dmzNetwork.getComputers(), datacenter.getPcByIp("192.168.52.104"), ConnectionType.HTTPS);
		
		//DODANO
		Firewall.addAllowedConnection(dmzNetwork.getPcByIp("203.0.113.101"), datacenter.getPcByIp("192.168.52.103"), ConnectionType.HTTPS);
		*/
		System.out.print("Please provide IP address from where the worm will start its spread: ");
		
		Scanner sc = new Scanner(System.in);
		String str = new String(); 
		
		Computer startingPoint = null; 
		while (startingPoint == null) {
			str = sc.nextLine();
			if ((startingPoint = dhcp.getPcByIp(str)) == null) {
				System.out.println("Network does not contain PC with given IP address, please provide an existing IP address.");
			}
		}
		
		
		System.out.println("Do you want to simulate all computers being shut down after IDS detects an intrusion? (y/n)");
		String turnOff = sc.nextLine();
		
		System.out.println("Do you want to allow the worm to spread to remote shares? (y/n)");
		String remoteShares = sc.nextLine();
		
		System.out.println("Do you want to allow the worm to spread using EternalBlue exploit? (y/n)");
		String eternalBlue = sc.nextLine();	
		
		NotPetya notPetya = new NotPetya(startingPoint, remoteShares.equals("y") ? true : false, eternalBlue.equals("y") ? true : false);
		Thread thread = new Thread(notPetya); 
		
		sc.close();
		
		thread.start();	
		
		SimulationState state = new SimulationState(dhcp, turnOff.equals("y") ? true : false); 
		Thread thread2 = new Thread(state); 
		thread2.start();
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
	}
	
	private static void loadNetworkFromJSON() {
		/*System.out.print("Please enter path to file containing description of network: ");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();*/
		
		String jsonString = null; 
		try {
			jsonString = new Scanner(new File("network_config.txt")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject(jsonString); 
		JSONArray jsonArray = json.getJSONArray("subnetworks"); 
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);
			Subnetwork subnetwork = new Subnetwork(jsonObj.getString("subnetworkName"));
			createComputers(((JSONObject) jsonArray.get(i)).getJSONArray("computers"), subnetwork);
		}
	}
	
	private static void createComputers(JSONArray arrayOfComputers, Subnetwork subnetwork) {
		for (int i = 0; i < arrayOfComputers.length(); i++) {
			JSONObject computer = ((JSONObject)arrayOfComputers.get(i));
			String name = computer.getString("name"); 
			String ipAddress = computer.getString("ipAddress"); 
			String operatingSystem = computer.getString("operatingSystem");
			
			if (name.contains("-")) {
				createMultipleComputers(name, ipAddress, operatingSystem, subnetwork); 
			} else {
				subnetwork.addComputer(new Computer(name, ipAddress, new OperatingSystem(operatingSystem)));
			}
		}
		dhcp.addSubnetwork(subnetwork);
	}
	

	
	private static void createMultipleComputers(String name, String ipAddress, String operatingSystem, Subnetwork subnetwork) {
		String nameRange = name.substring(name.lastIndexOf(" ") + 1); 
		int nameStart = Integer.parseInt(nameRange.split("-")[0]);
		int nameEnd = Integer.parseInt(nameRange.split("-")[1]);
		String baseName = name.substring(0, name.lastIndexOf(" ") + 1);
		
		String ipRange = ipAddress.substring(ipAddress.lastIndexOf(".") + 1); 
		int ipStart = Integer.parseInt(ipRange.split("-")[0]); 
 
		String baseIp = ipAddress.substring(0, ipAddress.lastIndexOf(".") + 1);
		
		for (int i = nameStart; i <= nameEnd; i++) {
			subnetwork.addComputer(new Computer(baseName + i, baseIp + ipStart++, new OperatingSystem(operatingSystem)));
		}
		
	}
	
	private static void configureFirewallFromJSON() {
		/*System.out.print("Please enter path to file containing description of network: ");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();*/
		
		String jsonString = null; 
		try {
			jsonString = new Scanner(new File("firewall_config.txt")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject(jsonString); 
		JSONArray jsonArray = json.getJSONArray("allowedConnections");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);
			System.out.println(jsonObj.toString());
			addAllowedConnection(jsonObj); 
		}
	}
	
	private static void addAllowedConnection(JSONObject jsonObj) {
		String ipAddress1 = jsonObj.getString("ipAddress1"); 
		String ipAddress2 = jsonObj.getString("ipAddress2"); 
		List<String> ipAddresses1 = new ArrayList<>(); 
		List<String> ipAddresses2 = new ArrayList<>(); 
		
		ipAddresses1 = fillListOfIps(ipAddress1);
		ipAddresses2 = fillListOfIps(ipAddress2); 
		
		for (int i = 0; i < ipAddresses1.size(); i++) {
			for (int j = 0; j < ipAddresses2.size(); j++) {
				Firewall.addAllowedConnection(dhcp.getPcByIp(ipAddresses1.get(i)),
											  dhcp.getPcByIp(ipAddresses2.get(j)), 
											  ConnectionType.valueOf(jsonObj.getString("connectionType")));
			}
		}
	}
	
	private static List<String> fillListOfIps(String ipAddress) {
		List<String> ipAddresses = new ArrayList<>(); 
		if (ipAddress.contains("-")) { 
			String ipRange = ipAddress.substring(ipAddress.lastIndexOf(".") + 1); 
			int ipStart = Integer.parseInt(ipRange.split("-")[0]); 
			int ipEnd = Integer.parseInt(ipRange.split("-")[1]); 
			
			for (int i = ipStart; i <= ipEnd; i++) {
				ipAddresses.add(ipAddress.substring(0, ipAddress.lastIndexOf(".") + 1)+i);
			}
		} else if (ipAddress.matches("[a-zA-Z]+")) {
			System.out.println("Usao sam");
			Subnetwork subnetwork = dhcp.getSubnetworkByName(ipAddress); 
			for (Computer c : subnetwork.getComputers()) {
				ipAddresses.add(c.getIpAddress());
			}
		} else {
			ipAddresses.add(ipAddress); 
		}
		System.out.println("Adrese su:");
		for (String address : ipAddresses) {
			System.out.println(address);
		}
		return ipAddresses;
	}
}
