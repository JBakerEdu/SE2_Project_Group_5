'''
Created on Mar 10, 2025

@author: alecx
'''
from src.model.user import User
from src.server import constants

class ServerResourceHandler:
    def __init__(self):
        '''
            Stores and manages all of the resources for the server
        '''
        self._users = {}
    
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
    
    def addUserToDMList(self, user, otherUser):
        '''
            Adds a user to another user's DM list
            
            @precondition none
            @postcondition otherUser is added to user's DM list and user is added to otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user.addMessagableUser().append(otherUser)
        otherUser.addMessagableUser().append(user)
        
    def removeUserFromDMList(self, user, otherUser):
        '''
            Removes a user from another user's DM list
            
            @precondition none
            @postcondition otherUser is removed from user's DM list and user is removed from otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user.addMessagableUser().remove(otherUser)
        otherUser.addMessagableUser().remove(user)