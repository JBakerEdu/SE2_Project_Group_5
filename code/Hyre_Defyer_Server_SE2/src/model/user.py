'''
Created on Mar 10, 2025

@author: alecx
'''

class User:
    def __init__(self, userName, password):
        '''
            Creates a new user with the provided username and password
        
            @precondition none
            @postcondition getUserName() == userName && getPassword() == password
            
            @param userName: the username
            @param password: the password
        '''
        self._userName = userName
        self._password = password
        self._bio = ""
        self.messagableUsers = []

    def getUserName(self):
        ''' 
            Returns the name of the user
        
            @precondition none
            @postcondition none
            
            @return the username
        '''
        return self._userName
    
    def getPassword(self):
        ''' 
            Returns the password of the user
        
            @precondition none
            @postcondition none
            
            @return the password
        '''
        return self._password
    
    def getBio(self):
        ''' 
            Returns the bio of the user
        
            @precondition none
            @postcondition none
            
            @return the bio
        '''
        return self._bio
    
    def setBio(self, newBio):
        ''' 
            Sets the bio of the user
        
            @precondition none
            @postcondition the bio is set to the new bio
            
            @param newBio: the new bio
        ''' 
        self._bio = newBio
        
    def addMessagableUser(self, user):
        ''' 
            Adds a user to the list of users that this user can message
        
            @precondition none
            @postcondition the user is added to the list of users that this user can message
            
            @param user: the user to add
        '''
        self.messagableUsers.append(user)
        
    def removeMessagableUser(self, user):
        ''' 
            Removes a user from the list of users that this user can message
        
            @precondition none
            @postcondition the user is removed from the list of users that this user can message
            
            @param user: the user to remove
        '''
        self.messagableUsers.remove(user)