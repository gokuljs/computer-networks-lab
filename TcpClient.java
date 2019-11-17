

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {

	public static void main(String args[])
	{
		try {
			Socket socket=new Socket("localhost",4555);

			// reading the file name from keyboard. Uses inputstream
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter the file name"); 
			String file_name=bufferedReader.readLine();

			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			
			// sending the file name to server.
			dout.writeUTF(file_name);

			DataInputStream din=new DataInputStream(socket.getInputStream());

			String content;

			do 
			{ 
				// receiving the contents from server.  Uses inputstream
				content = din.readUTF(); 
				System.out.println(content); 
				dout.flush(); 

				if(content==null)
				{
					break; 
				}
			} while(true); 

			dout.close(); 
			socket.close();
			bufferedReader.close();
		}
		catch(IOException e)
		{

		}
	}

}



