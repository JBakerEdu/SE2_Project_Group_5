'''
Created on Mar 10, 2025

@author: alecx
'''
from src.model.user import User
from src.server import constants
from src.model.message import Message
from src.model.freelanceer_roster import FreelancerRoster

class ServerResourceHandler:
    def __init__(self):
        '''
            Stores and manages all of the resources for the server
        '''
        self._users = {}
        self._godMessageLog: list[list[Message]] = []
        self.freelancers = FreelancerRoster()
    
    def createAccount(self, userName, password):
        '''
            Creates a new account with the provided username and password
            
            @precondition none
            @postcondition User with the provided username and password is created
            
            @param userName: the username
            @param password: the password
            
            @return True if the account was created, False otherwise
        '''
        if userName in self._users:
            return False
        
        self._users[userName] = User(userName, password)
        return True
    
    def login(self, userName, password):
        '''
            Logs a user in with the provided username and password
            
            @precondition none
            @postcondition none
            
            @param userName: the username
            @param password: the password
            
            @return the user information if the login was successful, None otherwise
        '''
        user = self._users.get(userName)
        if user and user.getPassword() == password:
            return {
                constants.REQ_USERNAME: user.getUserName(),
                constants.REQ_BIO: user.getBio()
            }
        return None
    
    def getMessagesBetween(self, sender, receiver):
        '''
            Retrieves the message log between two users.
        
            @precondition none
            @postcondition none
            
            @param sender: The sender user
            @param receiver: The receiver user
            
            @return: The list of messages between the two users
        '''
        for messageLog in self._godMessageLog:
            if (messageLog and 
                ((messageLog[0].getSender() == sender and messageLog[0].getReceiver() == receiver) or
                 (messageLog[0].getSender() == receiver and messageLog[0].getReceiver() == sender))):
                return messageLog

        return []
    
    def sendMessage(self, message):
        '''
        Sends a message between users.
    
        @precondition none
        @postcondition The message is sent between the users
        
        @param message: The message to be sent
        '''
        
        sender = message.getSender()
        receiver = message.getReceiver()
        
        messageLog = self.getMessagesBetween(sender, receiver)
        
        if not messageLog:
            messageLog = [message]
            
            self._godMessageLog.append(messageLog)
        else:
            messageLog.append(message)
    
    def addUserToDMList(self, user, otherUser):
        '''
            Adds a users to their own DM list
            
            @precondition none
            @postcondition otherUser is added to user's DM list and user is added to otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user1 = self.getUser(user)
        user2 = self.getUser(otherUser)
        user1.addMessageableUser(otherUser)
        user2.addMessageableUser(user)
        
    def getUser(self, userName):
        '''
            Retrieves a user by their username
            
            @precondition none
            @postcondition none
            
            @param userName: the username
            
            @return the user
        '''
        return self._users.get(userName)
        
    def removeUserFromDMList(self, user, otherUser):
        '''
            Removes a user from another user's DM list
            
            @precondition none
            @postcondition otherUser is removed from user's DM list and user is removed from otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user1 = self.getUser(user)
        user2 = self.getUser(otherUser)
        user1.removeMessageableUser(otherUser)
        user2.removeMessageableUser(user)
        
    def addFreelancerToRoster(self, freelancer):
        '''
            Adds the freelancer to the roster
            
            @precondition freelancer != null && username cannot already exist
            @postcondition freelancer is added
            
            @param freelancer: the freelancer being added to the roster
        '''
        self.freelancers.add_freelancer(freelancer)
        
    def getFreelancers(self):
        '''
            gets the freelancers 
            
            @precondition none
            @postcondition none 
        '''
        return self.freelancers