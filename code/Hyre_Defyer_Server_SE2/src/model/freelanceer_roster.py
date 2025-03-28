'''
Created on Mar 26, 2025

@author: Kate Anglin
'''
from typing import List
from src.model.freelancer import Freelancer
from src.server import constants
 
class FreelancerRoster:
    """
        Maintains a list of freelancers and allows searching based on category or skills.
    """
    def __init__(self):
        """
            Initializes an empty FreelancerRoster.
            
            @precondition none
            @postcondition none
        """
        self.freelancers = []

    def add_freelancer(self, freelancer: Freelancer):
        """
            Adds a freelancer to the roster.
            
            @precondition freelancer != null && freelancer.Class == Freelancer
            @postcondition freelancer added to roster

            @param freelancer: The Freelancer object to add.
            @raises ValueError: If freelancer is None.
        """
        if not freelancer:
            raise ValueError("Freelancer cannot be null.")
        if any(f.getUserName() == freelancer.getUserName() for f in self.freelancers):
            raise ValueError(f"Username '{freelancer.getUserName()}' is already taken.")
        self.freelancers.append(freelancer)

    def get_freelancers_by_category(self, category: str) -> List[Freelancer]:
        """
            Retrieves freelancers that belong to a specified category.
            
            @precondition category != null
            @postcondition none

            @param category: The category to filter by.
            @return: A list of freelancers matching the category.
            @raises ValueError: If category is null.
        """
        if not category:
            raise ValueError("Category cannot be null.")
        return [f for f in self.freelancers if f.containsCategory(category)]

    def get_freelancers_by_skill(self, skill: str) -> List[Freelancer]:
        """
            Retrieves freelancers that contain to a specified skill.
            
            @precondition skill != null
            @postcondition none

            @param skill: The skill to search for.
            @return: A list of freelancers containing the skill.
            @raises ValueError: If skill is null.
        """
        if not skill:
            raise ValueError("Skill cannot be null.")
        return [f for f in self.freelancers if f.contains_skill(skill)]
    
    def remove_freelancer(self, freelancer: Freelancer):
        """
            Removes a freelancer from the roster.
            
            @precondition freelancer != null && freelancer.Class == Freelancer
            @postcondition freelancer removed from roster

            @param freelancer: The Freelancer object to remove.
            @raises ValueError: If freelancer is not in the roster.
        """
        if freelancer not in self.freelancers:
            raise ValueError("Freelancer not found in roster.")
        self.freelancers.remove(freelancer)
        
    def contains(self, username):
        """
            Checks if a freelancer with the given username exists in the roster.

            @param username: The username to check.
            @return: True if a freelancer with the username exists, otherwise False.
            @raises ValueError: If username is None.
        """
        if username is None:
            raise ValueError("Username cannot be null.")
        
        return any(f.getUserName() == username for f in self.freelancers)

    def __repr__(self):
        """
            Returns a string representation of the FreelancerRoster.
            
            @precondition none
            @postcondition none
        """
        return f"FreelancerRoster(freelancers={self.freelancers})"
