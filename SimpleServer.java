// SimpleServer.java: A simple server program
import java.net.*;
import java.io.*;
public class SimpleServer {
	public static void main(String[] args) throws IOException {
		// Register service port 1254
		ServerSocket s = new ServerSocket(1254);
		Socket s1 = s.accept(); 	// wait and accept a connection	

		// Get a communication sream associated with the socket
		OutputStream slout = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream(slout);

		// send a string
		dos.writeUTF("Hi there!");


		// close the connection but not the server
		dos.close();
		slout.close();
		s1.close();
	}
}