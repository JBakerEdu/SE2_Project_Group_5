package edu.westga.cs3211.hyre_defyer_project.server;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;

/**
 * Acts as a temporary interactive server for the application
 * 
 * @author Alec Neal and Kate Anglin
 * @version Spring 2025
 */
public class ServerInterface {
	
	private static final String FREELANCER_TO_ADD_CAN_NOT_BE_NULL = "freelancer to add can not be null.";
	private static final String FREELANCER_TO_REMOVE_CAN_NOT_BE_NULL = "freelancer to remove can not be null.";

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
            
            for (int index = 0; index < messagesArray.length(); index++) {
                JSONObject messageObj = messagesArray.getJSONObject(index);
                
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
	
	/**
	 * Get users I can message from server
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param user the person logged in
	 * @return user you can or have messaged
	 */
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
            
            for (int index = 0; index < usersArray.length(); index++) {
                String userName = usersArray.getString(index);
                
                User newUser = new User(userName);
                
                users.add(newUser);
            }
        }
		return users;
	}

	/**
	 * Adds a user to your dm list
	 * 
	 * @param user1 user one is the user logged in 
	 * @param user2 user two is the user attempting to be messaged
	 */
	public static void addMessageableUser(User user1, User user2) {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_ADD_MESSAGEABLE_USER);
		request.put(Constants.REQ_SENDER, user1.getUserName());
		request.put(Constants.REQ_RECEIVER, user2.getUserName());
		
		ServerCommunicator.sendRequestToServer(request);
		
	}
	
	/**
	 * Gets the freelancers from the server
	 * 
	 * @precondition none
	 * @postcondition none 
	 * 
	 * @return FreelancerRoster which is the roster of freelancers 
	 */
	public static FreelancerRoster getFreelancers() {
		FreelancerRoster roster = new FreelancerRoster();
		
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_GET_FREELANCERS);
		
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject responseJSON = new JSONObject(response);
		String successCode = responseJSON.getString(Constants.SUCCESS_CODE);
		
		if (successCode.equals(Constants.REP_SUCCESS)) {

            JSONArray freelancersArray = responseJSON.getJSONArray(Constants.REP_FREELANCERS);
            
            for (int index = 0; index < freelancersArray.length(); index++) {
            	JSONObject freelancerJSON = freelancersArray.getJSONObject(index);
                String userName = freelancerJSON.getString(Constants.REQ_USERNAME);
                String bio = freelancerJSON.getString(Constants.REQ_BIO);
                JSONArray skillsArray = freelancerJSON.getJSONArray(Constants.REQ_SKILLS);
                JSONArray categoriesArray = freelancerJSON.getJSONArray(Constants.REQ_CATEGORIES);
                
                List<String> skills = new ArrayList<>();
                for (int indexJ = 0; indexJ < skillsArray.length(); indexJ++) {
                    skills.add(skillsArray.getString(indexJ));
                }
                
                List<String> categories = new ArrayList<>();
                for (int indexJ = 0; indexJ < categoriesArray.length(); indexJ++) {
                    String categoryString = categoriesArray.getString(indexJ);
                    categories.add(categoryString);
                }
                
                Freelancer freelancer = new Freelancer(userName, bio, categories, skills);
                
                roster.addFreelancer(freelancer);
            }
            return roster;
        }
		
		return null;
	}
	
	/**
	 * Adds a freelancer to the server
	 * 
	 * @precondition freelancer != null
	 * @postcondition freelancer is added to the server
	 * 
	 * @param freelancer a freelancer object to add
	 * @return boolean of true if added and false if not add to freelancers
	 */
	public static Boolean addFreelancer(Freelancer freelancer) {
		if (freelancer == null) {
			throw new IllegalArgumentException(FREELANCER_TO_ADD_CAN_NOT_BE_NULL);
		}
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
	
	/**
	 * Removes a freelancer from the server
	 * 
	 * @precondition freelancer != null
	 * @postcondition freelancer is removed from the server
	 * 
	 * @param freelancer a freelancer object to remove
	 * @return boolean of true if removed and false if not removed to freelancers
	 */
	public static Boolean removeFreelancer(Freelancer freelancer) {
		if (freelancer == null) {
			throw new IllegalArgumentException(FREELANCER_TO_REMOVE_CAN_NOT_BE_NULL);
		}
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_REMOVE_FREELANCER);
		request.put(Constants.REQ_USERNAME, freelancer.getUserName());
		request.put(Constants.REQ_PASSWORD, "");
		request.put(Constants.REQ_BIO, freelancer.getBio());
		request.put(Constants.REQ_SKILLS, freelancer.getSkills());
		request.put(Constants.REQ_CATEGORIES, freelancer.getCategory());
		
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		
		if (successCode.equals(Constants.REP_SUCCESS)) {
			return true;
		} else {
			String error = jsonObject.getString(Constants.REP_ERROR_DESCRIPTION);
			throw new IllegalArgumentException(error);
		}
	}

	/**
	 * Gets the categories from the server
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the categories
	 */
	public static List<String> getCategories() {
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_GET_CATEGORIES);

		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject responseJSON = new JSONObject(response);
		String successCode = responseJSON.getString(Constants.SUCCESS_CODE);

		if (successCode.equals(Constants.REP_SUCCESS)) {
	        JSONArray categoriesArray = responseJSON.getJSONArray(Constants.REP_CATEGORIES);

	        List<String> categoriesList = new ArrayList<>();
	        for (int index = 0; index < categoriesArray.length(); index++) {
	            String category = categoriesArray.getString(index); 
	            categoriesList.add(category.toUpperCase().replace("_", " "));
	        }
	        return categoriesList;
	    }
		return null;
	}
	
}
