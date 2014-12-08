import java.net.InetAddress;
import java.net.UnknownHostException;


public class GetIPAddressAndHostname {
public static void main(String[] args) {
		
		try {
			
			InetAddress inetAddr = InetAddress.getLocalHost();
			
			byte[] addr = inetAddr.getAddress();

			// Convert to dot representation
			String ipAddr = "";
			for (int i = 0; i < addr.length; i++) {
				if (i > 0) {
					ipAddr += ".";
				}
				ipAddr += addr[i] & 0xFF;
			}
			
			String hostname = inetAddr.getHostName();
			
			System.out.println("IP Address: " + ipAddr);
			System.out.println("Hostname: " + hostname);
		    
		}
		catch (UnknownHostException e) {
			System.out.println("Host not found: " + e.getMessage());
		}
		
	}

}


