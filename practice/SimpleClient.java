package practice;// SimpleClient.java: A simple client program
import java.net.*;
import java.io.*;
public class SimpleClient {
	public static void main(String[] args) throws IOException {
		// Open connection to a server, at port 1254
		Socket s1 = new Socket("localhost", 1254);

		// Get an input file hanfle from ther socket and read the input
		InputStream s1In = s1.getInputStream();
		DataInputStream dis =  new DataInputStream(s1In);
		String st = new String (dis.readUTF());
		System.out.println(st);

		// When done, close the connection and exit
		dis.close();
		s1In.close();
		s1.close();
	}
}