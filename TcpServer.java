
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
		public static void main(String args[]) throws Exception {
			
			// establishing the connection with the server
			ServerSocket serverSocket=new ServerSocket(4555);
			
			// binding with port: 4555
			Socket socket=serverSocket.accept();
			
			// reading the file name from client
			DataInputStream din=new DataInputStream(socket.getInputStream());
			String fileName=din.readUTF();
			
			// reading file contents
			BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
			
			String content;
			
			// keeping output stream ready to send the contents
			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			
			 // reading line-by-line from file
			while((content=bufferedReader.readLine())!=null&&content.length()!=0){
	
				// sending each line to client
				dout.writeUTF(content);	
		
			}
			//closing resources
			bufferedReader.close();
			serverSocket.close();
			
	}
}

