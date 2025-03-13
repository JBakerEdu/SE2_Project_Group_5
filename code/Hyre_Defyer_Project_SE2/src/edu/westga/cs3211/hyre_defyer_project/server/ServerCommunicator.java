package edu.westga.cs3211.hyre_defyer_project.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerCommunicator {
	
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 5555;

	/**
	 * Main entry point of client.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		try (Socket clientSocket = new Socket(HOST, PORT);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

            System.out.println("Session started...");
            // TODO Figure out how to send JSON objects, The python server only excepts JSON objects,
            //  so here in java we need to serialize our objects but we need a dependency for that
            
            /*JSONObject request = new JSONObject();
            request.put("message", "Test");
            request.put("type", "testMessage");

            // Send the JSON string
            objectOutputStream.writeObject(request.toString());
            objectOutputStream.flush();
            */

            System.out.println("\tObject was sent!\nSession disconnected.");
        } catch (IOException e) {
            System.err.println("Error: Unable to communicate with the server.");
            e.printStackTrace();
        }
	}
}