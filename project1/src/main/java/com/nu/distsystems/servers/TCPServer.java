package com.nu.distsystems.servers;

import com.nu.distsystems.utils.MyLogger;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {
  private Socket socket = null;
  private ServerSocket server = null;
  private DataInputStream dataInputStream = null;
  private Map<String,String> dataMap;
  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public TCPServer(int portNumber) {
    dataMap = new HashMap<String, String>();
    try {
      MyLogger.setUp("tcp-server-");
    } catch (IOException ex) {
      System.out.println("Unable to initialize the logger!" + ex.getMessage());
    }
    LOGGER.setLevel(Level.INFO);
    try {
      server = new ServerSocket(portNumber);
      LOGGER.info("Server started at port: " + portNumber);
      LOGGER.info("Waiting for client... ");
      socket = server.accept();
      LOGGER.info("Client accepted!");
      processInput();
      socket.close();
    } catch (IOException ex) {
      LOGGER.severe("ERROR creating server socket!" + ex.getMessage());
    }
  }

  private void processInput() {
    int i;
    // take Input from the client socket
    try {
      dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

      String line = "";
      StringBuilder output = new StringBuilder();
      while (true) {
        try {
          line = dataInputStream.readUTF();
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
          LOGGER.severe("ERROR in reading client's input!");
        }
      }
    } catch (IOException ex) {
      LOGGER.severe("ERROR creating DataInputStream!" + ex.getMessage());
    }
  }

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Usage: java Server <port_number>");
      System.exit(1);
    }
    System.out.println("Welcome to the servers!\n The servers \n"
            + "\t1) reverse all the characters, and \n"
            + "\t2) reverse the capitalization of the strings ('network' would now become "
            + "'KROWTEN').");
    TCPServer server = new TCPServer(Integer.parseInt(args[0]));
  }
}
