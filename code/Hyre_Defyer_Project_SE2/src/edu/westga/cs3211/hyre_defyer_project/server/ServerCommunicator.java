package edu.westga.cs3211.hyre_defyer_project.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.json.JSONObject;
import java.net.Socket;

public class ServerCommunicator {

	private static int port = Constants.PORT;
	/**
	 * Sends a request to the server and returns the servers response.
	 * 
	 * @param request the request
	 */
	public static String sendRequestToServer(JSONObject request) {
		try (Socket clientSocket = new Socket(Constants.IP_ADDRESS, port);
			     DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			     DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream())) {

	            String jsonMessage = request.toString();
	            byte[] jsonBytes = jsonMessage.getBytes("UTF-8");

	            dataOutputStream.writeInt(jsonBytes.length);
	            dataOutputStream.write(jsonBytes);
	            dataOutputStream.flush();
	            
	            int messageLength = dataInputStream.readInt();
	            byte[] responseBytes = new byte[messageLength];
	            dataInputStream.readFully(responseBytes);

	            return new String(responseBytes, "UTF-8");
	        } catch (IOException e) {
	            System.err.println("Failed to connect to the server.");
	            e.printStackTrace();
	        }
		return "ERROR";
	}
	
	/**
	 * Changes the port to force exception for 100% coverage
	 * 
	 * @param newPort the new port
	 */
	public static void changePortForCoverage(int newPort) {
		port = newPort;
	}
}