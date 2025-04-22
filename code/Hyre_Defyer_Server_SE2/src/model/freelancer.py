'''
Created on Mar 26, 2025

@author: Kate Anglin
'''

from src.model.user import User
from src.server import constants

class Freelancer(User):
    '''
        Represents a freelancer with a username, bio, category, and a fixed number of skills.
    '''
    def __init__(self, userName, password):
        '''
            Creates a new freelancer with the provided username and password.
        
            @precondition none
            @postcondition getUserName() == userName && getPassword() == password
            
            @param userName: the username
            @param password: the password
        '''
        super().__init__(userName, password)
        self._categories = set()
        self._skills = set()
        self._rating = 0
        self._ratings = list()
    
    def addCategory(self, category):
        '''
            Adds a category to the freelancer.
            
            @precondition none
            @postcondition the category is added to the set of categories
            
            @param category: the category to add
        '''
        self._categories.add(category)
        
        print(f"📌 Added category: {category}, Current categories: {self._categories}")
    
    def removeCategory(self, category):
        '''
            Removes a category from the freelancer.
            
            @precondition none
            @postcondition the category is removed from the set of categories
            
            @param category: the category to remove
        '''
        self._categories.discard(category)
    
    def getCategories(self):
        '''
            Returns the set of categories associated with the freelancer.
            
            @precondition none
            @postcondition none
            
            @return the set of categories
        '''
        return self._categories
    
    def containsCategory(self, category: str) -> bool:
        """
            Checks if the freelancer contains the given category.
        
            @param category: The category to search for.
            @return True if the category is found, False otherwise.
        """
        return category in self.getCategories()
    
    def addSkill(self, skill):
        '''
            Adds a skill to the freelancer.
            
            @precondition none
            @postcondition the skill is added to the set of skills
            
            @param skill: the skill to add
        '''
        self._skills.add(skill)
    
    def removeSkill(self, skill):
        '''
            Removes a skill from the freelancer.
            
            @precondition none
            @postcondition the skill is removed from the set of skills
            
            @param skill: the skill to remove
        '''
        self._skills.discard(skill)
    
    def getSkills(self):
        '''
            Returns the set of skills associated with the freelancer.
            
            @precondition none
            @postcondition none
            
            @return the set of skills
        '''
        return self._skills
    
    def contains_skill(self, skill):
        '''
            Checks if the freelancer has the specified skill.
            
            @precondition skill != null
            @postcondition none
            
            @param skill: The skill to check for.
            @return: True if the freelancer has the skill, False otherwise.
        '''
        return skill in self.getSkills()
    
    def getRating(self):
        '''
            Gets the rating of the freelancer
            
            @precondition: none
            @postcondition: none
            
            @return: the rating of the freelancer
        '''
        return self._rating
    
    def rate(self, rate):
        '''
            Sets the rating of the freelancer
            
            @precondition: none
            @postcondition: self._rating = the average of their ratings
            
            @param rate: what the freelancer was rated out of 5 
        '''
        self.getRatings().append(rate)
        self._rating = sum(self.getRatings())/len(self.getRatings())
    
    def getRatings(self):
        return self._ratings
    
    def to_dict(self):
        '''
            Returns a dictionary representation of the freelancer, including username, bio, categories, and skills.
            
            @precondition none
            @postcondition none
            
            @return a dictionary representation of the freelancer
        '''
        base_dict = super().to_dict()
        base_dict.update({
            constants.REQ_CATEGORIES: list(self._categories),
            constants.REQ_SKILLS: list(self._skills)
        })
        return base_dict
    
    def __eq__(self, other):
        '''
            Returns whether two freelancers are equal to each other based on username, bio, categories, and skills.
            
            @precondition none
            @postcondition none
            
            @return wheither two freelancers are equal to each other.
        '''
        if not isinstance(other, Freelancer):
            return False
        return (self.getUserName() == other.getUserName()and self.getBio()== other.getBio() and
                self.getCategories() == other.getCategories() and self.getSkills() == other.getSkills())

    def __repr__(self):
        '''
            Returns representation of the freelancer, including username, categories, and skills.
        
            @precondition none
            @postcondition none
        
            @return a representation of the freelancer
        '''
        return f"Freelancer(username={self.getUserName()}, categories={self.getCategories()}, skills={self.getSkills()})"
    