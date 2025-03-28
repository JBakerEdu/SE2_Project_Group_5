import unittest
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler
from src.model.message import Message
from src.model.user import User
from src.model.freelancer import Freelancer

class TestServerRequestHandler(unittest.TestCase):
    def setUp(self):
        self.serverRequestHandler = ServerRequestHandler()

    def test_create_account(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)


    def test_login_success(self):
        create_request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(create_request)
        
        login_request = {
            constants.REQ_TYPE: constants.REQ_LOGIN,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(login_request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        self.assertEqual(response[constants.REQ_USERNAME], "username")
        self.assertEqual(response[constants.REQ_BIO], "")
        
    def test_duplicate_accounts(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
    
    def test_send_message(self):
        create_request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(create_request)
        
        create_request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username2",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(create_request)
        
        message_request = {
            constants.REQ_TYPE: constants.REQ_SEND_MESSAGE,
            constants.REQ_TEXT: "message",
            constants.REQ_RECEIVER: "username2",
            constants.REQ_SENDER: "username"
        }
        response = self.serverRequestHandler.handleRequest(message_request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
        message_request = {
            constants.REQ_TYPE: constants.REQ_SEND_MESSAGE,
            constants.REQ_TEXT: "message2",
            constants.REQ_RECEIVER: "username",
            constants.REQ_SENDER: "username2"
        }
        response = self.serverRequestHandler.handleRequest(message_request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
        message_get_request = {
            constants.REQ_TYPE: constants.REQ_GET_MESSAGES,
            constants.REQ_SENDER: "username",
            constants.REQ_RECEIVER: "username2"
        }
        
        response = self.serverRequestHandler.handleRequest(message_get_request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        self.assertEqual(len(response[constants.REP_MESSAGES]), 2)
        
        message_dicts = response[constants.REP_MESSAGES]
        
        messages = [
            Message(msg["text"], User(msg["sender"], ""), User(msg["receiver"], ""))
            for msg in message_dicts
        ]

        self.assertEqual(messages[0].getMessage(), "message")
        self.assertEqual(messages[0].getSender().getUserName(), "username")
        self.assertEqual(messages[0].getReceiver().getUserName(), "username2")
        self.assertEqual(messages[1].getMessage(), "message2")
        self.assertEqual(messages[1].getSender().getUserName(), "username2")
        self.assertEqual(messages[1].getReceiver().getUserName(), "username")
        
    def test_getFreelancers_empty_list(self):
        request = {
            constants.REQ_TYPE: constants.REQ_GET_FREELANCERS,
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS,
            constants.REP_FREELANCERS: []
        }
        self.assertEqual(response, expected_response)
        
    def test_addFreelancer(self):
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_FREELANCER,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATAGORIES: []
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS
        }
        self.assertEqual(response, expected_response)
        
    def test_getFreelancersNotEmpty(self):
        freelancer = Freelancer("user", "pass");
        self.serverRequestHandler._serverResourceHandler.addFreelancerToRoster(freelancer)
        
        request = {
            constants.REQ_TYPE: constants.REQ_GET_FREELANCERS,
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS,
            constants.REP_FREELANCERS: [freelancer]
        }
        self.assertEqual(response, expected_response)
    
    def test_getMultipleFreelancers(self):
        freelancer1 = Freelancer("user1", "pass1")
        freelancer2 = Freelancer("user2", "pass2")
        freelancer3 = Freelancer("user3", "pass3")
    
        self.serverRequestHandler._serverResourceHandler.addFreelancerToRoster(freelancer1)
        self.serverRequestHandler._serverResourceHandler.addFreelancerToRoster(freelancer2)
        self.serverRequestHandler._serverResourceHandler.addFreelancerToRoster(freelancer3)
        request = {
            constants.REQ_TYPE: constants.REQ_GET_FREELANCERS,
        }
        response = self.serverRequestHandler.handleRequest(request)
    
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS,
            constants.REP_FREELANCERS: [freelancer1, freelancer2, freelancer3]
        }
        self.assertEqual(response, expected_response)

if __name__ == "__main__":
    unittest.main()