import unittest

from src.server import constants
from src.server.server_resource_handler import ServerResourceHandler
from src.model.message import Message
from src.model.freelancer import Freelancer

class TestServerResourceHandler(unittest.TestCase):
    def setUp(self):
        self.serverResourceHandler = ServerResourceHandler()

    def test_setBio(self):
        self.serverResourceHandler.setUserBio("Alice", "Bio")
        self.assertEqual("Bio", self.serverResourceHandler.getUser("Alice").getBio())

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
    def test_failLogin(self):
        self.serverResourceHandler.createAccount("username", "password")
        user = self.serverResourceHandler.login("username", "wrongpassword")
        self.assertIsNone(user)
        
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
        
    def test_messagableUsersSystem(self):
        self.serverResourceHandler.createAccount("username", "password")
        self.serverResourceHandler.createAccount("friend", "password")
       
        self.serverResourceHandler.addUserToDMList("username", "friend")
        user = self.serverResourceHandler.getUser("username")
        self.assertIn("friend", user.getMessageableUsers())
        self.serverResourceHandler.removeUserFromDMList("username", "friend")
        self.assertNotIn("friend", user.getMessageableUsers())
    
    def test_removeUserFromDMList(self):
        self.serverResourceHandler.createAccount("dummy", "pass")
        self.serverResourceHandler.createAccount("dummy2", "pass")
        self.serverResourceHandler.addUserToDMList("dummy", "dummy2")
        
        user1 = self.serverResourceHandler.getUser("dummy")
        self.serverResourceHandler.removeUserFromDMList("dummy", "dummy2")
        
        self.assertNotIn("dummy2", user1.getMessageableUsers())

    def test_removeUserFromServer(self):
        self.serverResourceHandler.createAccount("dummy account", "pass")
        self.serverResourceHandler.deleteUserFromServer("dummy account")
        self.assertIsNone(self.serverResourceHandler.getUser("dummy account"))
                
    def test_add_freelancer(self):
        self.freelancer = Freelancer("New", "Freelancer")
        self.serverResourceHandler.addFreelancerToRoster(self.freelancer)
        self.assertTrue(self.freelancer in self.serverResourceHandler.getFreelancers())
        
    def test_remove_freelancer(self):
        self.freelancer = Freelancer("New", "Freelancer")
        self.serverResourceHandler.addFreelancerToRoster(self.freelancer)
        self.assertTrue(self.freelancer in self.serverResourceHandler.getFreelancers())
        self.serverResourceHandler.removeFreelancerFromRoster(self.freelancer)
        self.assertFalse(self.freelancer in self.serverResourceHandler.getFreelancers())
        
    def test_remove_freelancer_not_found(self):
        self.freelancer = Freelancer("New", "Freelancer")
        self.assertFalse(self.freelancer in self.serverResourceHandler.getFreelancers())
        with self.assertRaises(ValueError) as context:
            self.serverResourceHandler.removeFreelancerFromRoster(self.freelancer)
        self.assertEqual(str(context.exception), "Freelancer not found in roster.")
        
    def test_rate_freelancer_success(self):
        self.serverResourceHandler.createAccount("Free", "Freelancer")
        self.serverResourceHandler.addFreelancerToRoster(Freelancer("Free", "Freelancer"))
        
        self.assertIn(Freelancer("Free", "Freelancer"), self.serverResourceHandler.getFreelancers())
        self.assertEqual(3, self.serverResourceHandler.rateFreelancer("dummy1", "Free", 3))
        
    def test_rate_freelancer_fail(self):
        self.serverResourceHandler.createAccount("New", "Freelancer")
        self.freelancer = Freelancer("New", "Freelancer")
        
        self.assertEquals(None, self.serverResourceHandler.rateFreelancer("dummy2", "New", 3))
        
    def test_rate_freelancer_multiple_times(self):
        self.assertTrue(self.serverResourceHandler.createAccount("Dummyyy", "Freelancer"))
        self.serverResourceHandler.addFreelancerToRoster(Freelancer("Dummyyy", "Freelancer"))
        
        self.freelancer = Freelancer("Dummyyy", "Freelancer")
        
        self.assertEquals(5, self.serverResourceHandler.rateFreelancer("1", "Dummyyy", 5))
        self.assertEquals(3, self.serverResourceHandler.rateFreelancer("2", "Dummyyy", 1))
        self.assertAlmostEquals(2.3, self.serverResourceHandler.rateFreelancer("3", "Dummyyy", 1), places=1)
        self.assertEquals(2, self.serverResourceHandler.rateFreelancer("4", "Dummyyy", 1))
        self.assertAlmostEquals(1.8, self.serverResourceHandler.rateFreelancer("5", "Dummyyy", 1), places=1)
        
    def test_user_rates_freelancer_multiple_times(self):
        self.assertTrue(self.serverResourceHandler.createAccount("Dummyyy", "Freelancer"))
        self.serverResourceHandler.addFreelancerToRoster(Freelancer("Dummyyy", "Freelancer"))
        
        self.freelancer = Freelancer("Dummyyy", "Freelancer")
        
        self.assertEquals(5, self.serverResourceHandler.rateFreelancer("user", "Dummyyy", 5))
        self.assertEquals(3, self.serverResourceHandler.rateFreelancer("user", "Dummyyy", 3))
        
    def test_get_freelancer_rating(self):
        self.assertTrue(self.serverResourceHandler.createAccount("Dummyyyy", "Freelancer"))
        self.serverResourceHandler.addFreelancerToRoster(Freelancer("Dummyyyy", "Freelancer"))
        
        self.assertEqual(0, self.serverResourceHandler.getFreelancerRating("Dummyyyy"))
        
        
    def test_remove_all_from_category(self):
        self.freelancer = Freelancer("HELLO", "password")
        self.freelancer.addCategory("NEW")
        categories = self.serverResourceHandler.getCategories()
        size = len(categories)
        self.serverResourceHandler.addFreelancerToRoster(self.freelancer)
        afterCat = self.serverResourceHandler.getCategories()
        
        self.assertTrue(size < len(afterCat))
        self.serverResourceHandler.removeFreelancerFromRoster(self.freelancer)
        afterRemove = self.serverResourceHandler.getCategories()
        self.assertTrue(size == len(afterRemove))
    
    def test_delete_full_message_log(self):
        self.serverResourceHandler.createAccount("username", "password")
        self.serverResourceHandler.createAccount("friend", "password")
        
        sender = self.serverResourceHandler.getUser("username")
        receiver = self.serverResourceHandler.getUser("friend")
        
        message = Message("Hello", sender, receiver)
        message2 = Message("Hi", receiver, sender)
        
        self.serverResourceHandler.addUserToDMList("username", "friend")
        
        self.serverResourceHandler.sendMessage(message)
        self.serverResourceHandler.sendMessage(message2)
        
        self.serverResourceHandler.removeUserFromDMList("username", "friend")
        self.serverResourceHandler.removeUserFromDMList("friend", "username")
        
        messageLog = self.serverResourceHandler.getMessagesBetween(sender, receiver)
        
        self.assertEqual(messageLog, [])

if __name__ == "__main__":
    unittest.main()