'''
Created on Mar 26, 2025

@author: angli
'''
import unittest

from src.model.freelancer import Freelancer
from src.model.freelanceer_roster import FreelancerRoster

class TestFreelancerRoster(unittest.TestCase):
    def setUp(self):
        self.freelancer1 = Freelancer("Alice", "password123")
        self.freelancer2 = Freelancer("Bob", "password456")
        self.freelancer3 = Freelancer("Charlie", "password789")
        
        self.freelancer1.addCategory("Web Development")
        self.freelancer1.addSkill("Python")
        
        self.freelancer2.addCategory("Mobile Development")
        self.freelancer2.addSkill("Java")
        
        self.freelancer3.addCategory("Web Development")
        self.freelancer3.addSkill("JavaScript")
        
        self.roster = FreelancerRoster()

    def test_add_freelancer(self):
        self.roster.add_freelancer(self.freelancer1)
        self.roster.add_freelancer(self.freelancer2)
        
        self.assertIn(self.freelancer1, self.roster.freelancers)
        self.assertIn(self.freelancer2, self.roster.freelancers)
    
    def test_add_freelancer_invalid(self):
        with self.assertRaises(ValueError):
            self.roster.add_freelancer(None)
    
    def test_get_freelancers_by_category(self):
        self.roster.add_freelancer(self.freelancer1)
        self.roster.add_freelancer(self.freelancer2)
        self.roster.add_freelancer(self.freelancer3)
        
        web_dev_freelancers = self.roster.get_freelancers_by_category("Web Development")
        
        self.assertIn(self.freelancer1, web_dev_freelancers)
        self.assertIn(self.freelancer3, web_dev_freelancers)
        self.assertNotIn(self.freelancer2, web_dev_freelancers)
    
    def test_get_freelancers_by_category_invalid(self):
        with self.assertRaises(ValueError):
            self.roster.get_freelancers_by_category(None)
    
    def test_get_freelancers_by_skill(self):
        self.roster.add_freelancer(self.freelancer1)
        self.roster.add_freelancer(self.freelancer2)
        self.roster.add_freelancer(self.freelancer3)
        
        python_freelancers = self.roster.get_freelancers_by_skill("Python")
        
        self.assertIn(self.freelancer1, python_freelancers)
        self.assertNotIn(self.freelancer2, python_freelancers)
        self.assertNotIn(self.freelancer3, python_freelancers)
    
    def test_get_freelancers_by_skill_invalid(self):
        with self.assertRaises(ValueError):
            self.roster.get_freelancers_by_skill(None)
            
    def test_remove_freelancer(self):
        self.freelancer1 = Freelancer("Charlie", "freelancePass")
        self.freelancer2 = Freelancer("Alice", "webDevPass")
        self.roster.add_freelancer(self.freelancer1)
        self.roster.add_freelancer(self.freelancer2)
        
        self.assertIn(self.freelancer1, self.roster.freelancers)
        self.roster.remove_freelancer(self.freelancer1)
        self.assertNotIn(self.freelancer1, self.roster.freelancers)

        with self.assertRaises(ValueError):
            self.roster.remove_freelancer(self.freelancer1)
    
    def test_repr(self):
        self.roster.add_freelancer(self.freelancer1)
        self.roster.add_freelancer(self.freelancer2)
        expected_repr = f"FreelancerRoster(freelancers={[self.freelancer1, self.freelancer2]})"
        self.assertEqual(repr(self.roster), expected_repr)


if __name__ == "__main__":
    unittest.main()