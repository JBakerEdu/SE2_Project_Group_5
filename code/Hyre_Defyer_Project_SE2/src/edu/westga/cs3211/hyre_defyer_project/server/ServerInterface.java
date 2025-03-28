package edu.westga.cs3211.hyre_defyer_project.server;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;

/**
 * Acts as a temporary interactive server for the application
 * 
 * @author Alec Neal
 * @version Spring 2025
 */
public class ServerInterface {
	
	/**
	 * Gets the messages between two users
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param sender   The sender
	 * @param receiver The receiver
	 * 
	 * @return the messages between the two users, empty list if no messages
	 */
	public static ArrayList<Message> getMessagesBetween(User sender, User receiver) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_GET_MESSAGES);
		request.put(Constants.REQ_SENDER, sender.getUserName());
		request.put(Constants.REQ_RECEIVER, receiver.getUserName());
		
		String response = ServerCommunicator.sendRequestToServer(request);
		
		JSONObject jsonResponse = new JSONObject(response);
		
		ArrayList<Message> messageLog = new ArrayList<Message>();
		
		if (jsonResponse.getString(Constants.SUCCESS_CODE).equals(Constants.REP_SUCCESS)) {

            JSONArray messagesArray = jsonResponse.getJSONArray(Constants.REP_MESSAGES);
            
            for (int i = 0; i < messagesArray.length(); i++) {
                JSONObject messageObj = messagesArray.getJSONObject(i);
                
                String senderUserName = messageObj.getString(Constants.REQ_SENDER);
                String receiverUserName = messageObj.getString(Constants.REQ_RECEIVER);
                String text = messageObj.getString(Constants.REQ_TEXT);
                
                User senderUser = new User(senderUserName, "");
                User receiverUser = new User(receiverUserName, "");
                
                Message message = new Message(text, senderUser, receiverUser);
                messageLog.add(message);
            }
        }
		return messageLog;
	}
	
	/**
	 * Verify login credentials
	 * 
	 * @param userName the username
	 * @param password the password
	 * 
	 * @return the user object itself, null if the login credentials are invalid
	 */
	public static User login(String userName, String password) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_LOGIN);
		request.put(Constants.REQ_USERNAME, userName);
		request.put(Constants.REQ_PASSWORD, password);
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		if (successCode.equals(Constants.REP_SUCCESS)) {
            String bio = jsonObject.getString(Constants.REQ_BIO);
            String username = jsonObject.getString(Constants.REQ_USERNAME);
            return new User(username, bio);
		}
		return null; 
	}

	
	/**
	 * Creates an account
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param userName the username
	 * @param password the password
	 * 
	 * @return true if the account was created, false if duplicate username
     */
	public static Boolean createAccount(String userName, String password) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_CREATE_ACCOUNT);
		request.put(Constants.REQ_USERNAME, userName);
		request.put(Constants.REQ_PASSWORD, password);
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		return successCode.equals(Constants.REP_SUCCESS);
	}

	/**
	 * Sends a message
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param message  The message
	 */
	public static void sendMessage(Message message) {
		User sender = message.getSender();
		User receiver = message.getReceiver();
		JSONObject request = new JSONObject();
		
		request.put(Constants.REQ_TYPE, Constants.REQ_SEND_MESSAGE);
		request.put(Constants.REQ_SENDER, sender.getUserName());
		request.put(Constants.REQ_RECEIVER, receiver.getUserName());
		request.put(Constants.REQ_TEXT, message.getMessage());
		
		ServerCommunicator.sendRequestToServer(request);
    }
	
	public static List<User> getMessagableUsers(User user) {
		List<User> users = new ArrayList<User>();
		
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_GET_MESSAGEABLE_USERS);
		request.put(Constants.REQ_USERNAME, user.getUserName());
		
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject responseJSON = new JSONObject(response);
		String successCode = responseJSON.getString(Constants.SUCCESS_CODE);
		
		if (successCode.equals(Constants.REP_SUCCESS)) {

            JSONArray usersArray = responseJSON.getJSONArray(Constants.REP_USERS);
            
            for (int i = 0; i < usersArray.length(); i++) {
                String userName = usersArray.getString(i);
                
                User newUser = new User(userName);
                
                users.add(newUser);
            }
        }
		return users;
	}

	public static void addMessageableUser(User user1, User user2) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_ADD_MESSAGEABLE_USER);
		request.put(Constants.REQ_SENDER, user1.getUserName());
		request.put(Constants.REQ_RECEIVER, user2.getUserName());
		
		ServerCommunicator.sendRequestToServer(request);
		
	}
	
	public static FreelancerRoster getFreelancers() {
		FreelancerRoster roster = new FreelancerRoster();
		
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_GET_FREELANCERS);
		
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject responseJSON = new JSONObject(response);
		String successCode = responseJSON.getString(Constants.SUCCESS_CODE);
		
		if (successCode.equals(Constants.REP_SUCCESS)) {

            JSONArray freelancersArray = responseJSON.getJSONArray(Constants.REP_FREELANCERS);
            
            for (int i = 0; i < freelancersArray.length(); i++) {
            	JSONObject freelancerJSON = freelancersArray.getJSONObject(i);
                String userName = freelancerJSON.getString(Constants.REQ_USERNAME);
                String bio = freelancerJSON.getString(Constants.REQ_BIO);
                JSONArray skillsArray = freelancerJSON.getJSONArray(Constants.REQ_SKILLS);
                JSONArray categoriesArray = freelancerJSON.getJSONArray(Constants.REQ_CATEGORIES);
                
                List<String> skills = new ArrayList<>();
                for (int j = 0; j < skillsArray.length(); j++) {
                    skills.add(skillsArray.getString(j));
                }
                
                List<Categories> categories = new ArrayList<>();
                for (int j = 0; j < categoriesArray.length(); j++) {
                    String categoryString = categoriesArray.getString(j);
                    
                    try {
                        Categories category = Categories.valueOf(categoryString); 
                        categories.add(category);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category: " + categoryString);
                    }
                }
                
                Freelancer freelancer = new Freelancer(userName, bio, categories, skills);
                
                roster.addFreelancer(freelancer);
            }
            return roster;
        }
		
		return null;
	}
	
	public static Boolean addFreelancer(Freelancer freelancer) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_ADD_FREELANCER);
		request.put(Constants.REQ_USERNAME, freelancer.getUserName());
		request.put(Constants.REQ_PASSWORD, "");
		request.put(Constants.REQ_BIO, freelancer.getBio());
		request.put(Constants.REQ_SKILLS, freelancer.getSkills());
		request.put(Constants.REQ_CATEGORIES, freelancer.getCategory());
		
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		return successCode.equals(Constants.REP_SUCCESS);
		
	}
}
