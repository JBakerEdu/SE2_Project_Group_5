'''
Created on Mar 10, 2025

@author: alecx
'''
from src.model.user import User
from src.server import constants

class ServerResourceHandler:
    def __init__(self):
        self._users = {}
    
    def createAccount(self, userName, password):
        if userName in self._users:
            return False
        
        self._users[userName] = User(userName, password)
        return True
    
    def login(self, userName, password):
        user = self._users.get(userName)
        if user and user.getPassword() == password:
            return {
                constants.REQ_USERNAME: user.getUserName(),
                constants.REQ_BIO: user.getBio()
            }
        return None