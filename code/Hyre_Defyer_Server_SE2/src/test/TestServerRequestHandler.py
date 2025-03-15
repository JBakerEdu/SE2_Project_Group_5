import unittest
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler

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
        
        self.assertEqual(response[constants.REP_MESSAGES][0].getMessage(), "message")
        self.assertEqual(response[constants.REP_MESSAGES][1].getMessage(), "message2")
        self.assertEqual(response[constants.REP_MESSAGES][0].getSender(), "username")
        self.assertEqual(response[constants.REP_MESSAGES][1].getSender(), "username2")
        self.assertEqual(response[constants.REP_MESSAGES][0].getReceiver(), "username2")
        self.assertEqual(response[constants.REP_MESSAGES][1].getReceiver(), "username")

if __name__ == "__main__":
    unittest.main()