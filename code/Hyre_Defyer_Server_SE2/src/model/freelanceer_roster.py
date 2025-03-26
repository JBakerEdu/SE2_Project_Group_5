'''
Created on Mar 26, 2025

@author: Kate Anglin
'''
from typing import List
from src.model.freelancer import Freelancer
 
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
        return [f for f in self.freelancers if f.get_category() == category]

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

    def __repr__(self):
        """
            Returns a string representation of the FreelancerRoster.
            
            @precondition none
            @postcondition none
        """
        return f"FreelancerRoster(freelancers={self.freelancers})"
