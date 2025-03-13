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
         

if __name__ == "__main__":
    unittest.main()