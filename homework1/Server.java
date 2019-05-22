package homework1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private Socket socket = null;
  private ServerSocket server = null;
  private DataInputStream dIn = null;

  public Server(int portNumber) {
    try {
      server = new ServerSocket(portNumber);
      System.out.println("Server started at port: " + portNumber);

      System.out.println("Waiting for client... ");
      socket = server.accept();
      System.out.println("Client accepted!");
      processInput();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void processInput() {
    int i;
    // take Input from the client socket
    try {
      dIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

      String line = "";
      StringBuilder output = new StringBuilder();
      while (true) {
        try {
          line = dIn.readUTF();
          if (line.equals("\\q")) {
            break;
          }
          for (i = line.length() - 1; i >= 0; --i) {
            char ch = line.charAt(i);
            if (Character.isUpperCase(ch)) {
              output.append(Character.toLowerCase(ch));
            } else {
              output.append(Character.toUpperCase(ch));
            }
          }
          System.out.println(output.toString());
          output.setLength(0);
        } catch (IOException ex) {
          System.out.println(ex);
        }
      }
      dIn.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Usage: java Server <port_number>");
      System.exit(1);
    }
    System.out.println("Welcome to the server!\n The server \n"
            + "\t1) reverse all the characters, and \n"
            + "\t2) reverse the capitalization of the strings ('network' would now become "
            + "'KROWTEN').");
    Server server = new Server(Integer.parseInt(args[0]));
  }
}
