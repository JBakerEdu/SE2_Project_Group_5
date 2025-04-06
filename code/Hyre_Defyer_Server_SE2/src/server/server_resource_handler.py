'''
Created on Mar 10, 2025

@author: alecx and Kate Anglin
'''
from src.model.user import User
from src.server import constants
from src.model.message import Message
from src.model.freelanceer_roster import FreelancerRoster
from src.model.freelancer import Freelancer

class ServerResourceHandler:
    def __init__(self):
        '''
            Stores and manages all of the resources for the server
        '''
        self._users = {}
        self._categories = set()
        self._godMessageLog: list[list[Message]] = []
        self.freelancers = FreelancerRoster()
        self.createAccount("admin", "1234567")
        self._populateUsers()
        
    def _populateUsers(self):
        freelancers_data = [
            ("Alice", "Experienced Accountant", "BUSINESS_AND_FINANCE", ["Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"]),
            ("Bob", "Investment Consultant", "BUSINESS_AND_FINANCE", ["Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"]),
            ("Charlie", "Financial Analyst", "BUSINESS_AND_FINANCE", ["Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"]),
            ("Dana", "Graphic Designer", "DESIGN_AND_CREATIVE", ["Photoshop", "Illustrator", "Branding", "UI/UX", "Typography"]),
            ("Eli", "Illustrator", "DESIGN_AND_CREATIVE", ["Digital Art", "Comics", "Concept Art", "Vector Graphics", "Sketching"]),
            ("Fay", "Animator", "DESIGN_AND_CREATIVE", ["2D Animation", "3D Animation", "After Effects", "Storyboarding", "Motion Graphics"]),
            ("George", "Java Developer", "DEVELOPMENT_AND_IT", ["Java", "Spring", "SQL", "Git", "REST APIs"]),
            ("Hannah", "Web Developer", "DEVELOPMENT_AND_IT", ["HTML", "CSS", "JavaScript", "React", "Node.js"]),
            ("Ian", "Data Scientist", "DEVELOPMENT_AND_IT", ["Python", "Machine Learning", "Pandas", "NumPy", "Deep Learning"]),
            ("Jack", "Mechanical Engineer", "ENGINEERING_AND_SCIENCE", ["SolidWorks", "AutoCAD", "Finite Element Analysis", "Thermodynamics", "CAD Design"]),
            ("Karen", "Biomedical Engineer", "ENGINEERING_AND_SCIENCE", ["Medical Devices", "Biomaterials", "3D Printing", "Regulatory Compliance", "Clinical Trials"]),
            ("Leo", "Aerospace Engineer", "ENGINEERING_AND_SCIENCE", ["Aerodynamics", "Propulsion", "Satellite Systems", "Orbital Mechanics", "Composites"]),
            ("Mia", "Marketing Specialist", "MARKETING_AND_SALES", ["SEO", "Social Media", "Content Marketing", "Google Ads", "Email Campaigns"]),
            ("Noah", "Sales Consultant", "MARKETING_AND_SALES", ["B2B Sales", "CRM", "Negotiation", "Cold Calling", "Sales Funnels"]),
            ("Olivia", "Digital Advertiser", "MARKETING_AND_SALES", ["Facebook Ads", "PPC", "Conversion Optimization", "Market Research", "Copywriting"]),
            ("Paul", "Music Producer", "MUSIC_AND_AUDIO", ["Mixing", "Mastering", "Ableton Live", "Logic Pro", "Music Composition"]),
            ("Quinn", "Sound Designer", "MUSIC_AND_AUDIO", ["Foley", "Game Audio", "Synthesizers", "Film Scoring", "Podcast Editing"]),
            ("Ryan", "Voice Actor", "MUSIC_AND_AUDIO", ["Narration", "Character Voices", "Commercials", "E-Learning", "Dubbing"]),
            ("Sarah", "Carpenter", "TRADES_AND_SKILLED_LABOR", ["Woodworking", "Cabinet Making", "Blueprint Reading", "Furniture Design", "Framing"]),
            ("Tom", "Plumber", "TRADES_AND_SKILLED_LABOR", ["Pipe Fitting", "Drain Cleaning", "Fixture Installation", "Water Heaters", "Soldering"]),
            ("Uma", "Electrician", "TRADES_AND_SKILLED_LABOR", ["Wiring", "Troubleshooting", "Panel Upgrades", "Lighting Design", "Circuitry"]),
            ("Victor", "Copywriter", "WRITING_AND_TRANSLATION", ["SEO Writing", "Blogging", "Technical Writing", "Ad Copy", "Editing"]),
            ("Wendy", "Translator", "WRITING_AND_TRANSLATION", ["Spanish", "French", "German", "Mandarin", "Localization"]),
            ("Xander", "Fiction Writer", "WRITING_AND_TRANSLATION", ["Creative Writing", "Screenwriting", "Storytelling", "Character Development", "Editing"]),
        ]

        for username, bio, category, skills in freelancers_data:
            self.createAccount(username, "password")
            user = self.getUser(username)
            user.setBio(bio)
            freelancer = Freelancer(username, "password")
            freelancer.setBio(bio)
            freelancer._skills = set(skills)
            freelancer._categories.add(category)
            self.addFreelancerToRoster(freelancer)
    
    def createAccount(self, userName, password):
        '''
            Creates a new account with the provided username and password
            
            @precondition none
            @postcondition User with the provided username and password is created
            
            @param userName: the username
            @param password: the password
            
            @return True if the account was created, False otherwise
        '''
        if userName in self._users:
            return False
        
        self._users[userName] = User(userName, password)
        self.addUserToDMList(userName, "admin")
        return True
    
    def login(self, userName, password):
        '''
            Logs a user in with the provided username and password
            
            @precondition none
            @postcondition none
            
            @param userName: the username
            @param password: the password
            
            @return the user information if the login was successful, None otherwise
        '''
        user = self._users.get(userName)
        if user and user.getPassword() == password:
            return {
                constants.REQ_USERNAME: user.getUserName(),
                constants.REQ_BIO: user.getBio()
            }
        return None
    
    def getMessagesBetween(self, sender, receiver):
        '''
            Retrieves the message log between two users.
        
            @precondition none
            @postcondition none
            
            @param sender: The sender user
            @param receiver: The receiver user
            
            @return: The list of messages between the two users
        '''
        for messageLog in self._godMessageLog:
            if (messageLog and 
                ((messageLog[0].getSender() == sender and messageLog[0].getReceiver() == receiver) or
                 (messageLog[0].getSender() == receiver and messageLog[0].getReceiver() == sender))):
                return messageLog

        return []
    
    def sendMessage(self, message):
        '''
        Sends a message between users.
    
        @precondition none
        @postcondition The message is sent between the users
        
        @param message: The message to be sent
        '''
        
        sender = message.getSender()
        receiver = message.getReceiver()
        
        messageLog = self.getMessagesBetween(sender, receiver)
        
        if not messageLog:
            messageLog = [message]
            
            self._godMessageLog.append(messageLog)
        else:
            messageLog.append(message)
    
    def addUserToDMList(self, user, otherUser):
        '''
            Adds a users to their own DM list
            
            @precondition none
            @postcondition otherUser is added to user's DM list and user is added to otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user1 = self.getUser(user)
        user2 = self.getUser(otherUser)
        user1.addMessageableUser(otherUser)
        user2.addMessageableUser(user)
        
    def getUser(self, userName):
        '''
            Retrieves a user by their username
            
            @precondition none
            @postcondition none
            
            @param userName: the username
            
            @return the user
        '''
        return self._users.get(userName)
    
    def deleteUserFromServer(self, userName):
        '''
            Remove's a user from the server
            
            @precondition: none
            @postcondition: none
            
            @param userName: the users name
        '''
        del self._users[userName]
        
    def removeUserFromDMList(self, user, otherUser):
        '''
            Removes a user from another user's DM list
            
            @precondition none
            @postcondition otherUser is removed from user's DM list and user is removed from otherUser's DM list
            
            @param user: the user
            @param otherUser: the other user
        '''
        user1 = self.getUser(user)
        user2 = self.getUser(otherUser)
        user1.removeMessageableUser(otherUser)
        user2.removeMessageableUser(user)
        
    def addFreelancerToRoster(self, freelancer):
        '''
            Adds the freelancer to the roster
            
            @precondition freelancer != null && username cannot already exist
            @postcondition freelancer is added and categories is updated if new category
            
            @param freelancer: the freelancer being added to the roster
        '''
        if freelancer.getCategories():
            for category in freelancer.getCategories():
                if category not in self.getCategories():
                    self._categories.add(category)
        return self.freelancers.add_freelancer(freelancer)
        
    def getFreelancers(self):
        '''
            gets the freelancers 
            
            @precondition none
            @postcondition none 
        '''
        return self.freelancers.freelancers
    
    def removeFreelancerFromRoster(self, freelancer):
        '''
            Removes the freelancer from the rooster
            
            @precondition freelancer != null && freelancer.Class == Freelancer
            @postcondition freelancer is removed
            
            @param freelancer: The Freelancer object to remove.
            @raises ValueError: If freelancer is not in the roster.
        '''
        
        result = self.freelancers.remove_freelancer(freelancer)
        
        for category in freelancer.getCategories():
            still_exists = any(
                category in other.getCategories()
                for other in self.freelancers.freelancers
                )

            if not still_exists:
                self._categories.discard(category)

        return result
    
    def getCategories(self):
        '''
            Gets the categories
            
            @precondition none
            @postcondition none
        '''
        
        return self._categories
        