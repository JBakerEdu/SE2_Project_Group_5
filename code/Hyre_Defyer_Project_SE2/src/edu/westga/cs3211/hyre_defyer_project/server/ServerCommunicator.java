package edu.westga.cs3211.hyre_defyer_project.server;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import org.json.JSONObject;

public class ServerCommunicator {

	/**
	 * Sends a request to the server and returns the servers response.
	 * 
	 * @param request the request
	 */	
	public static String sendRequestToServer(JSONObject request) {
		Context context = ZMQ.context(1);
		
        Socket socket = context.socket(ZMQ.REQ);
        
		try {
			String address = "tcp://" + Constants.IP_ADDRESS + ":" + Constants.PORT;
	        socket.connect(address);
	
	    	String jsonMessage = request.toString();
	
	        socket.send(jsonMessage.getBytes(ZMQ.CHARSET), 0);
	
	        byte[] reply = socket.recv(0);
	        return new String(reply, ZMQ.CHARSET);
		} catch (Exception e) {
	        System.err.println(e.getMessage());
	        e.printStackTrace();
	    } finally {
	    	socket.close();
	        context.term();
	    }
		return "ERROR";
	}
}