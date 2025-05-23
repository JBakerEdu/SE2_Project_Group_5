'''
Created on Mar 10, 2025

@author: alecx and Kate Anglin
'''
from src.server import constants
from src.model.message import Message
from src.model.freelancer import Freelancer
from src.server.server_resource_handler import ServerResourceHandler

class ServerRequestHandler:

    def __init__(self):

        self._serverResourceHandler = ServerResourceHandler()
        
    def _setUserBio(self, request):
        '''
            Sets the bio of the User
        '''
        response = {}
        bio = request.get(constants.REQ_BIO)
        username = request.get(constants.REQ_USERNAME)
        
        if self._serverResourceHandler.setUserBio(username, bio):
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        else:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = "User does not exist"
        
        return response   
            
    def _sendMessage(self, request):
        '''
            Sends a message between two users
        '''
        response = {}
        messageText = request.get(constants.REQ_TEXT)
        senderName = request.get(constants.REQ_SENDER)
        receiverName = request.get(constants.REQ_RECEIVER)
        sender = self._serverResourceHandler.getUser(senderName)
        receiver = self._serverResourceHandler.getUser(receiverName)
        
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
        senderName = request.get(constants.REQ_SENDER)
        receiverName = request.get(constants.REQ_RECEIVER)
        sender = self._serverResourceHandler.getUser(senderName)
        receiver = self._serverResourceHandler.getUser(receiverName)
        
        messages = self._serverResourceHandler.getMessagesBetween(sender, receiver)
               
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        formatted_messages = [msg.to_dict() for msg in messages]
        response[constants.REP_MESSAGES] = formatted_messages
        
        return response
        
    def _getMessageableUsers(self, request):
        '''
            Returns all users that can be messaged
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        user = self._serverResourceHandler.getUser(userName)
        
        users = user.getMessageableUsers()
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        response[constants.REP_USERS] = users
        
        return response

    def _login(self, request):
        '''
            Returns the user details if username and password are correct.
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
    
    def _addMessageableUser(self, request):
        '''
            Adds a user to the list of users that can be messaged
        '''
        response = {}
        sender = request.get(constants.REQ_SENDER)
        receiver = request.get(constants.REQ_RECEIVER)
        
        self._serverResourceHandler.addUserToDMList(sender, receiver)
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        return response
    

    def _removeMessageableUser(self, request):
        '''
            Deletes a user from the list of users that can be messaged
        '''
        current_user = request.get(constants.REQ_SENDER)
        other_user = request.get(constants.REQ_RECEIVER)
        
        self._serverResourceHandler.removeUserFromDMList(current_user, other_user)
        
        response = {}
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        return response
        

    def _getFreelancers(self):
        '''
            Returns all freelancers
        '''
    
        response = {}
        
        freelancers = self._serverResourceHandler.getFreelancers()
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        response[constants.REP_FREELANCERS] = [freelancer.to_dict() for freelancer in freelancers]
        
        return response
    
    def _addFreelancer(self, request):
        '''
            adds a freelancer
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        password = request.get(constants.REQ_PASSWORD)
        bio = request.get(constants.REQ_BIO)
        skills = request.get(constants.REQ_SKILLS)
        categories = request.get(constants.REQ_CATEGORIES)
        
        freelancer = Freelancer(userName,password)
        freelancer.setBio(bio)
        freelancer._skills = set(skills) if isinstance(skills, list) else {skills}
        freelancer._categories = set(categories) if isinstance(categories, list) else {categories}
        
        self._serverResourceHandler.addFreelancerToRoster(freelancer)
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        return response
    
    def _removeFreelancer(self, request):
        '''
            removes a freelancer
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        password = request.get(constants.REQ_PASSWORD)
        bio = request.get(constants.REQ_BIO)
        skills = request.get(constants.REQ_SKILLS)
        categories = request.get(constants.REQ_CATEGORIES)
        
        freelancer = Freelancer(userName,password)
        freelancer.setBio(bio)
        freelancer._skills = set(skills) if isinstance(skills, list) else {skills}
        freelancer._categories = set(categories) if isinstance(categories, list) else {categories}
        
        try:
            self._serverResourceHandler.removeFreelancerFromRoster(freelancer)
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        except ValueError as e:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = str(e)

        return response
    
    def _rateFreelancer(self, request):
        response = {}
        user = request.get(constants.REQ_SENDER)
        freelancer = request.get(constants.REQ_RECEIVER)
        rating = request.get(constants.REQ_RATING)
        
        result = self._serverResourceHandler.rateFreelancer(user, freelancer, rating)
        
        if result is False:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
        else:
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS 

        return response
    
    def _getFreelancerRating(self, request):
        response = {}
        freelancer = request.get(constants.REQ_USERNAME)
        
        result = self._serverResourceHandler.getFreelancerRating(freelancer)
        if result == 0:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
        else:
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
            response[constants.REP_RATING] = result
            
        return response
    
    def _getCategories(self, request):
        '''
            gets the categories
        '''
        response = {}
        categories = self._serverResourceHandler.getCategories()
        response[constants.REP_CATEGORIES] = list(categories)
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        
        return response
        
    def _deleteUserFromServer(self, request):
        '''
        Deletes a user from the server
        '''
        response = {}
        userName = request.get(constants.REQ_USERNAME)
        
        try:
            self._serverResourceHandler.deleteUserFromServer(userName)
            response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        except KeyError as e:
            response[constants.SUCCESS_CODE] = constants.REP_FAIL
            response[constants.REP_ERROR_DESCRIPTION] = str(e)
        return response
    
    def handleRequest(self, request):
        '''
            Handles and distributes requests and returns with their appropriate responses.
            
            Supported request types:
                send message
                get messages
                create account
                login
                get messageable users
                add messageable user
                get freelancers
                add freelancer
                get categories
                delete user from server
                rate freelancer
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
            
        elif req_type == constants.REQ_GET_MESSAGEABLE_USERS:
            response = self._getMessageableUsers(request)
            
        elif req_type == constants.REQ_ADD_MESSAGEABLE_USER:
            response = self._addMessageableUser(request)
            
        elif req_type == constants.REQ_DELETE_CHAT:
            response = self._removeMessageableUser(request)

        elif req_type == constants.REQ_GET_FREELANCERS:
            response = self._getFreelancers()
        
        elif req_type == constants.REQ_ADD_FREELANCER:
            response = self._addFreelancer(request)
            
        elif req_type == constants.REQ_REMOVE_FREELANCER:
            response = self._removeFreelancer(request)
            
        elif req_type == constants.REQ_RATE_FREELANCER:
            response = self._rateFreelancer(request)
            
        elif req_type == constants.REQ_GET_RATING:
            response = self._getFreelancerRating(request)
        
        elif req_type == constants.REQ_GET_CATEGORIES:
            response = self._getCategories(request);
            
        elif req_type == constants.REQ_DELETE_USER_FROM_SERVER:
            response = self._deleteUserFromServer(request)
        
        elif req_type == constants.REQ_SET_USER_BIO:
            response = self._setUserBio(request)    
        return response
        