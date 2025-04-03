'''
Created on Mar 26, 2025

@author: Kate Anglin
'''
import unittest

from src.model.freelancer import Freelancer
from src.model.user import User
from src.server import constants

class TestFreelancer(unittest.TestCase):
    def setUp(self):
        self.freelancer = Freelancer("Charlie", "freelancePass")
        self.user = User("Charlie", "freelancePass")
    
    def test_addCategory(self):
        self.freelancer.addCategory("Web Development")
        self.assertIn("Web Development", self.freelancer.getCategories())
    
    def test_removeCategory(self):
        self.freelancer.addCategory("Web Development")
        self.freelancer.removeCategory("Web Development")
        self.assertNotIn("Web Development", self.freelancer.getCategories())
        
    def test_contains_category(self):
        self.freelancer.addCategory("Web Development")
        self.freelancer.addCategory("Mobile Development")
        
        self.assertTrue(self.freelancer.containsCategory("Web Development"))
        self.assertTrue(self.freelancer.containsCategory("Mobile Development"))
        
        self.assertFalse(self.freelancer.containsCategory("Graphic Design"))    
    
    def test_addSkill(self):
        self.freelancer.addSkill("Python")
        self.assertIn("Python", self.freelancer.getSkills())
    
    def test_removeSkill(self):
        self.freelancer.addSkill("Python")
        self.freelancer.removeSkill("Python")
        self.assertNotIn("Python", self.freelancer.getSkills())
        
    def test_contains_skill(self):
        self.freelancer.addSkill("Python")
        self.freelancer.addSkill("JavaScript")
        
        self.assertTrue(self.freelancer.contains_skill("Python"))
        self.assertTrue(self.freelancer.contains_skill("JavaScript"))
        
        self.assertFalse(self.freelancer.contains_skill("Java"))    
    
    def test_to_dict(self):
        self.freelancer.addCategory("Web Development")
        self.freelancer.addSkill("Python")
        expected_dict = {
            constants.REQ_USERNAME: "Charlie",
            constants.REQ_BIO: "",
            constants.REQ_CATEGORIES: ["Web Development"],
            constants.REQ_SKILLS: ["Python"]
        }
        self.assertEqual(self.freelancer.to_dict(), expected_dict)
    
    def test_freelancer_equality(self):
        freelancer1 = Freelancer("Charlie", "freelancePass")
        freelancer2 = Freelancer("Charlie", "freelancePass")
    
        freelancer1.addCategory("Web Development")
        freelancer1.addSkill("Python")
    
        freelancer2.addCategory("Web Development")
        freelancer2.addSkill("Python")
    
        self.assertEqual(freelancer1, freelancer2)

        freelancer2.addSkill("Java")
        self.assertNotEqual(freelancer1, freelancer2)
    
    def test_equality_otherClass(self):
        self.assertNotEqual(self.freelancer, self.user)
        
    def test_repr(self):
        self.freelancer.addCategory("Web Development")
        self.freelancer.addSkill("Python")
        expected_repr = f"Freelancer(username=Charlie, categories={{'Web Development'}}, skills={{'Python'}})"
        self.assertEqual(repr(self.freelancer), expected_repr)
    
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()