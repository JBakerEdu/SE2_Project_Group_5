'''
Created on Mar 10, 2025

@author: alecx
'''
from src.server import constants
from src.model.message import Message
from src.server.server_resource_handler import ServerResourceHandler

class ServerRequestHandler:

    def __init__(self):

        self._serverResourceHandler = ServerResourceHandler()
            
    def _sendMessage(self, request):
        '''
            Sends a message between two users
        '''
        response = {}
        messageText = request.get(constants.REQ_TEXT)
        sender = request.get(constants.REQ_SENDER)
        receiver = request.get(constants.REQ_RECEIVER)
        
        message = Message(messageText, sender, receiver)
        self._serverResourceHandler.sendMessage(message)

        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        return response
    
    def _createAccount(self, request):
        '''
            Creates a new user account with a username and password
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        password = request.get(constants.REQ_PASSWORD)
        
        if not userName or not password:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = "Invalid username or password"
            return response
        
        if self._serverResourceHandler.createAccount(userName, password):
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        else:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = "Username already exists"
        
        return response
    
    def _getMessages(self, request):
        '''
            Returns all messages between two users
        '''
        response = {}
        sender = request.get(constants.REQ_SENDER)
        receiver = request.get(constants.REQ_RECEIVER)
        
        messages = self._serverResourceHandler.getMessagesBetween(sender, receiver)
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        response[constants.REP_MESSAGES] = messages
        return response
        

    def _login(self, request):
        '''
            Returns the user details if username and password are correct.
            TODO
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        password = request.get(constants.REQ_PASSWORD)
        
        if not userName or not password:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = "Invalid username or password"
            return response
        
        user = self._serverResourceHandler.login(userName, password)
        if user:
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
            response[constants.REQ_USERNAME] = user[constants.REQ_USERNAME]
            response[constants.REQ_BIO] = user[constants.REQ_BIO]
        else:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = "Invalid credentials"
        
        return response
    
    def handleRequest(self, request):
        '''
            Handles and distributes requests and returns with their appropriate responses.
            
            Supported request types:
                send message
                TODO: get messages
                create account
                login
                
        '''
        response = {constants.SUCCESS_CODE: constants.REP_FAIL, constants.REP_ERROR_DESCRIPTION: "unsupported request type"}
        
        req_type = request.get(constants.REQ_TYPE)

        if req_type == constants.REQ_SEND_MESSAGE:
            response = self._sendMessage(request)
            
        elif req_type == constants.REQ_CREATE_ACCOUNT:
            response = self._createAccount(request)
            
        elif req_type == constants.REQ_LOGIN:
            response = self._login(request)
            
        elif req_type == constants.REQ_GET_MESSAGES:
            response = self._getMessages(request)
        
        return response
        