import unittest

from src.server import constants
from src.server.server_resource_handler import ServerResourceHandler
from src.model.message import Message
from src.model.freelancer import Freelancer

class TestServerResourceHandler(unittest.TestCase):
    def setUp(self):
        self.serverResourceHandler = ServerResourceHandler()

    def test_create_account(self):
        self.assertTrue(self.serverResourceHandler.createAccount("username", "password"))

    def test_login_success(self):
        self.serverResourceHandler.createAccount("username", "password")
        user = self.serverResourceHandler.login("username", "password")
        self.assertIsNotNone(user)
        self.assertEqual(user, {
            constants.REQ_USERNAME: "username",
            constants.REQ_BIO: ""
        })
        
    def test_duplicate_accounts(self):
        self.assertTrue(self.serverResourceHandler.createAccount("username", "password"))
        self.assertFalse(self.serverResourceHandler.createAccount("username", "password"))
    
    def test_send_message(self):
        self.serverResourceHandler.createAccount("sender", "password")
        self.serverResourceHandler.createAccount("receiver", "password")
        
        sender = self.serverResourceHandler.login("sender", "password")
        receiver = self.serverResourceHandler.login("receiver", "password")
        
        message = Message("Hello", sender, receiver)
        message2 = Message("Hi", receiver, sender)
        
        self.serverResourceHandler.sendMessage(message)
        self.serverResourceHandler.sendMessage(message2)
        
        messageLog = self.serverResourceHandler.getMessagesBetween(sender, receiver)
        
        self.assertEqual(messageLog, [message, message2])
        
    def test_add_freelancer(self):
        self.freelancer = Freelancer("New", "Freelancer")
        self.serverResourceHandler.addFreelancerToRoster(self.freelancer)
        self.assertTrue(self.freelancer in self.serverResourceHandler.getFreelancers())

if __name__ == "__main__":
    unittest.main()