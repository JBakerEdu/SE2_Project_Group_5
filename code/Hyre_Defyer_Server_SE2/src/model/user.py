'''
Created on Mar 10, 2025

@author: alecx
'''
class User:
    def __init__(self, userName, password):
        self._userName = userName
        self._password = password
        self._bio = ""

    def getUserName(self):
        return self._userName
    
    def getPassword(self):
        return self._password
    
    def getBio(self):
        return self._bio
    
    def setBio(self, newBio):
        self._bio = newBio