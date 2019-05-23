package com.nu.distsystems.clients;

import com.nu.distsystems.utils.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {
  private Socket socket;
  private DataInputStream dataInputStream;
  private DataOutputStream dataOutputStream;
  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public TCPClient(String address, int portNumber) {
    try {
      MyLogger.setUp("tcp-client-");
    } catch (IOException ex) {
      System.out.println("Unable to initialize the logger!" + ex.getMessage());
    }
    LOGGER.setLevel(Level.INFO);
    try {
      socket = new Socket(address, portNumber);
      LOGGER.info("Connected to the server at... " + address + ":" + portNumber);
      sendData();
      socket.close();
    } catch (IOException ex) {
      LOGGER.severe("Unable to create/close Client socket! Address: " + address
              + " port: " + portNumber + " " + ex.getMessage());
    }
  }

  private void sendData() {
    try {
      dataInputStream = new DataInputStream(System.in);
      dataOutputStream = new DataOutputStream(socket.getOutputStream());
      // string to read message from input
      String line = "";

      // keep reading until "\q" is input
      while (!line.equals("\\q")) {
        try {
          line = dataInputStream.readLine();
          dataOutputStream.writeUTF(line);
        } catch (IOException ex) {
          LOGGER.severe("Received unsolicited response acknowledging unknown PUT/GET/DELETE "
                  + "with an invalid KEY!");
        }
      }
    } catch (IOException ex) {
      LOGGER.severe("Unable to create DataOutputStream object! " + ex.getMessage());
    }
  }

  public static void main(String[] args) {
//    if (args.length < 2) {
//      System.out.println("Usage: java Client <address> <port_number>");
//      System.exit(1);
//    }
    System.out.println("Welcome to the client program!\nKindly enter the strings you want to send "
            + "to the server to be processed.\nEnter '\\q' to exit!");
    TCPClient client = new TCPClient(args[0], Integer.parseInt(args[1]));
  }
}
