import unittest
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler
from src.model.message import Message
from src.model.user import User
from src.model.freelancer import Freelancer

class TestServerRequestHandler(unittest.TestCase):
    def setUp(self):
        self.serverRequestHandler = ServerRequestHandler()

    def test_setBio(self):
        request = {
            constants.REQ_TYPE: constants.REQ_SET_USER_BIO,
            constants.REQ_USERNAME: "Alice",
            constants.REQ_BIO: "Bio"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
    def test_setBioForNonExistentUser(self):
        request = {
            constants.REQ_TYPE: constants.REQ_SET_USER_BIO,
            constants.REQ_USERNAME: "user doesnt exist",
            constants.REQ_BIO: "Bio"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
    
    def test_create_account(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
    def test_null_account(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: None
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
        self.assertEqual(response[constants.REP_ERROR_DESCRIPTION], "Invalid username or password")

    def test_invalid_login(self):
        request = {
            constants.REQ_TYPE: constants.REQ_LOGIN,
            constants.REQ_USERNAME: None
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
        self.assertEqual(response[constants.REP_ERROR_DESCRIPTION], "Invalid username or password")
        
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(request)
        request = {
            constants.REQ_TYPE: constants.REQ_LOGIN,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "wrongpassword"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
        self.assertEqual(response[constants.REP_ERROR_DESCRIPTION], "Invalid credentials")
        
        
    def test_messagable_users(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(request)
        
        request = {
            constants.REQ_TYPE: constants.REQ_GET_MESSAGEABLE_USERS,
            constants.REQ_USERNAME: "username"
        }
        
        response = self.serverRequestHandler.handleRequest(request)
        
        response[constants.SUCCESS_CODE] = constants.REP_SUCCESS
        self.assertEqual(response[constants.REP_USERS], ["admin"])
            
        
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
        
    def test_addFreelancer(self):
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_FREELANCER,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATEGORIES: []
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS
        }
        self.assertEqual(response, expected_response)
        
    def test_deleteFreelancer(self):
        first_request = {
            constants.REQ_TYPE: constants.REQ_ADD_FREELANCER,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATEGORIES: []
        }
        self.serverRequestHandler.handleRequest(first_request)
        
        request = {
            constants.REQ_TYPE: constants.REQ_REMOVE_FREELANCER,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATEGORIES: []
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_SUCCESS
        }
        self.assertEqual(response, expected_response)
        
    def test_deleteFreelancerNotExist(self):
        request = {
            constants.REQ_TYPE: constants.REQ_REMOVE_FREELANCER,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATEGORIES: []
        }
        response = self.serverRequestHandler.handleRequest(request)
        
        expected_response = {
            constants.SUCCESS_CODE: constants.REP_FAIL,
            constants.REP_ERROR_DESCRIPTION: "Freelancer not found in roster."
        }
        self.assertEqual(response, expected_response)
        
    def test_getFreelancersNotEmpty(self):
        freelancer = Freelancer("user", "pass");
        self.serverRequestHandler._serverResourceHandler.addFreelancerToRoster(freelancer)
        
        request = {
            constants.REQ_TYPE: constants.REQ_GET_FREELANCERS,
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertIn(freelancer.to_dict(), response[constants.REP_FREELANCERS])
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)

    def test_rate_freelancer_success(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "NewUser",
            constants.REQ_PASSWORD: "pass"
        }
        self.assertEqual(self.serverRequestHandler.handleRequest(request)[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "Freelancer",
            constants.REQ_PASSWORD: "pass"
        }
        self.assertEqual(self.serverRequestHandler.handleRequest(request)[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_FREELANCER,
            constants.REQ_USERNAME: "Freelancer",
            constants.REQ_PASSWORD: "pass",
            constants.REQ_BIO: "bio",
            constants.REQ_SKILLS: [],
            constants.REQ_CATEGORIES: []
        }
        self.assertEqual(self.serverRequestHandler.handleRequest(request)[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_MESSAGEABLE_USER,
            constants.REQ_SENDER: "NewUser",
            constants.REQ_RECEIVER: "Freelancer"
        }
        self.assertEqual(self.serverRequestHandler.handleRequest(request)[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_MESSAGEABLE_USER,
            constants.REQ_SENDER: "Freelancer",
            constants.REQ_RECEIVER: "NewUser"
        }
        self.assertEqual(self.serverRequestHandler.handleRequest(request)[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_RATE_FREELANCER,
            constants.REQ_SENDER: "NewUser",
            constants.REQ_RECEIVER: "Freelancer",
            constants.REQ_RATING: 5
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)

    def test_equalMessage(self):
        msg1 = Message("Hello", User("Alice", ""), User("Bob", ""))
        msg2 = Message("Hello", User("Alice", ""), User("Bob", ""))
        msg3 = Message("Hi", User("Alice", ""), User("Bob", ""))
        
        self.assertTrue(msg1 == msg2)
        self.assertFalse(msg1 == msg3)
        self.assertFalse("bingo" == msg3)
        
    def test_add_messageable_user(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(request)
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username2",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(request)
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_MESSAGEABLE_USER,
            constants.REQ_SENDER: "username",
            constants.REQ_RECEIVER: "username2"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
    def test_get_categories(self):
        request = {
            constants.REQ_TYPE: constants.REQ_GET_CATEGORIES
        }
        
        response = self.serverRequestHandler.handleRequest(request)
        self.assertIsNotNone(response)
    
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        self.assertTrue(len(response[constants.REP_CATEGORIES]) > 0)
        
    def test_deleteChat(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "dummy1",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "dummy3",
            constants.REQ_PASSWORD: "password"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        request = {
            constants.REQ_TYPE: constants.REQ_ADD_MESSAGEABLE_USER,
            constants.REQ_SENDER: "dummy1",
            constants.REQ_RECEIVER: "dummy3"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)
        
        request = {
            constants.REQ_TYPE: constants.REQ_DELETE_CHAT,
            constants.REQ_SENDER: "dummy1",
            constants.REQ_RECEIVER: "dummy3"
        }
        self.serverRequestHandler.handleRequest(request)

    def test_delete_user_from_server(self):
        request = {
            constants.REQ_TYPE: constants.REQ_CREATE_ACCOUNT,
            constants.REQ_USERNAME: "username",
            constants.REQ_PASSWORD: "password"
        }
        self.serverRequestHandler.handleRequest(request)
        request = {
            constants.REQ_TYPE: constants.REQ_DELETE_USER_FROM_SERVER,
            constants.REQ_USERNAME: "username"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_SUCCESS)

    def test_delete_nonexistent_user_from_server(self):
        request = {
            constants.REQ_TYPE: constants.REQ_DELETE_USER_FROM_SERVER,
            constants.REQ_USERNAME: "user not in server"
        }
        response = self.serverRequestHandler.handleRequest(request)
        self.assertEqual(response[constants.SUCCESS_CODE], constants.REP_FAIL)
        
if __name__ == "__main__":
    unittest.main()