'''
    Stores information about a message.
    
    @author: alecx
    Version: Spring 2025
'''

class Message:
    
    def __init__(self, message, sender, receiver):
        '''
        Create a new message with the provided information.
        
        @precondition none
        @postcondition getMessage() == message && getSender() == sender && getReceiver() == receiver
        
        @param message: the message
        @param sender: the sender of the message
        @param receiver: the receiver of the message
        
        '''
        self._message = message
        self._sender = sender
        self._receiver = receiver

    def getMessage(self):
        '''
        Gets the message.

        @precondition none
        @postcondition none
        
        @return the message
        
        '''
        return self._message

    def getSender(self):
        '''
        Gets the sender of the message.

        @precondition none
        @postcondition none
        
        @return the sender
        '''
        return self._sender

    def getReceiver(self):
        '''
        Gets the receiver of the message.

        @precondition none
        @postcondition none
        
        @return the receiver
        '''
        return self._receiver
    
    def __eq__(self, other):
        if isinstance(other, Message):
            return (self._sender == other.getSender() and
                    self._receiver == other.getReceiver() and
                    self._message == other.getMessage())
        return False