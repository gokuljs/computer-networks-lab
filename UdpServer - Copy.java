import java.net.*;
import java.util.Scanner;
public class UdpServer
{
	public static void main(String[] args){
		
		try{
			
			DatagramSocket datagramSocket = new DatagramSocket();
			InetAddress ip = InetAddress.getByName("localhost");
			
			int port = 1224;
			Scanner scanner=new Scanner(System.in);
			String message = scanner.nextLine();
			
			while(true)
			{
				DatagramPacket datapacket = new DatagramPacket(message.getBytes(),message.length(),ip,port);			
				if(!message.equals("quit"))
					datagramSocket.send(datapacket);
				else		
				{ 
					break;
				}
				message = scanner.nextLine();
			}   
			datagramSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}