package UdpClient;
import java.net.*;
public class UdpClient {

	public static void main(String[] args)
	{
		try
		{
			DatagramSocket datagramSocket= new DatagramSocket(1224);                       

			byte[] buffer = new byte[100];
			String message;
			DatagramPacket packet;
			
			while(true) {
				packet  = new DatagramPacket(buffer,buffer.length);          
				datagramSocket.receive(packet);
				
				message = new String(packet.getData(), packet.getOffset(), packet.getLength());
				System.out.println(message);

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
