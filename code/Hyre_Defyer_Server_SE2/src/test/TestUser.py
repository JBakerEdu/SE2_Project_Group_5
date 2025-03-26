'''
Created on Mar 26, 2025

@author: Kate Anglin
'''
import unittest

from src.model.user import User
from src.model.freelancer import Freelancer
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler

class TestUser(unittest.TestCase):
    def setUp(self):
        self.user1 = User("Alice", "password123")
        self.user2 = User("Bob", "securePass")
        self.freelancer = Freelancer("Alice", "password123");
        self.handler = ServerRequestHandler();
    
    def test_getUserName(self):
        self.assertEqual(self.user1.getUserName(), "Alice")
        self.assertEqual(self.user2.getUserName(), "Bob")
    
    def test_getPassword(self):
        self.assertEqual(self.user1.getPassword(), "password123")
        self.assertEqual(self.user2.getPassword(), "securePass")
    
    def test_getBio(self):
        self.assertEqual(self.user1.getBio(), "")
    
    def test_setBio(self):
        self.user1.setBio("This is Alice's bio.")
        self.assertEqual(self.user1.getBio(), "This is Alice's bio.")
    
    def test_addMessageableUser(self):
        self.user1.addMessageableUser(self.user2)
        self.assertIn(self.user2, self.user1.getMessageableUsers())
        
        self.user1.addMessageableUser(self.user2)
        self.assertEqual(len(self.user1.getMessageableUsers()), 1)
    
    def test_removeMessageableUser(self):
        self.user1.addMessageableUser(self.user2)
        self.user1.removeMessageableUser(self.user2)
        self.assertNotIn(self.user2, self.user1.getMessageableUsers())
    
    def test_getMessageableUsers(self):
        self.assertEqual(self.user1.getMessageableUsers(), [])
        self.user1.addMessageableUser(self.user2)
        self.assertEqual(self.user1.getMessageableUsers(), [self.user2])
    
    def test_equality(self):
        user1_duplicate = User("Alice", "differentPass")
        self.assertEqual(self.user1, user1_duplicate)
        self.assertNotEqual(self.user1, self.user2)
        
    def test_equality_freelancer(self):
        self.assertNotEqual(self.user1, self.freelancer)
        
    def test_equality_otherClass(self):
        self.assertNotEqual(self.user1, self.handler)
        
    def test_to_dict(self):
        expected_dict = {
            constants.REQ_USERNAME: "Alice",
            constants.REQ_BIO: ""
        }
        self.assertEqual(self.user1.to_dict(), expected_dict)
     
    
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()