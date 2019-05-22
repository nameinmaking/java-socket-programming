package homework1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
  private Socket socket = null;
  private DataInputStream dIn = null;
  private DataOutputStream dOut = null;
  public static final String ANSI_RED = "\u001B[31m";

  public Client(String address, int portNumber) {
    try {
      socket = new Socket(address, portNumber);
      System.out.println("Connected to the server at... " + address + ":" + portNumber);
      sendData();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendData() {
    try {
      dIn = new DataInputStream(System.in);
      dOut = new DataOutputStream(socket.getOutputStream());

      // string to read message from input
      String line = "";

      // keep reading until "Over" is input
      while (!line.equals("\\q")) {
        try {
          line = dIn.readLine();
          dOut.writeUTF(line);
        } catch (IOException i) {
          System.out.println(i);
        }
      }

      dIn.close();
      dOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java Client <address> <port_number>");
      System.exit(1);
    }
    System.out.println("Welcome to the client program!\nKindly enter the strings you want to send "
            + "to the server to be processed.\nEnter '\\q' to exit!");
    Client client = new Client(args[0], Integer.parseInt(args[1]));
  }
}
