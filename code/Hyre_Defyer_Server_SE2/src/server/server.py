'''
Created on Mar 10, 2025

@author: alecx
'''
import zmq
import json
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler

def log(message):
    print("SERVER::{0}".format(message))

def main():
    serverRequestHandler = ServerRequestHandler()
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    address = f"tcp://{constants.IP_ADDRESS}:{constants.PORT}"
    socket.bind(address)
    
    while True:
        try:
            log("Waiting for client...")
            
            json_message = socket.recv_string()

            request = json.loads(json_message)
            log(f"Received Request: {request}")
            
            response = serverRequestHandler.handleRequest(request)
            json_response = json.dumps(response)
            log(f"Sending response: {json_response}")
            
            socket.send(json_response.encode('utf-8'))

        except json.JSONDecodeError:
            log("Invalid JSON received")
        finally:
            log("Client disconnected")
    socket.close()

if (__name__ == "__main__"):
    main()