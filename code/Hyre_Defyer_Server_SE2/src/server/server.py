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

def main(protocol, ipAddress, port):
    serverRequestHandler = ServerRequestHandler()
    
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("{0}://{1}:{2}".format(protocol, ipAddress, port))
    
    while True:
        log("waiting for request...")
        json_message = socket.recv_string()
        log(json_message)
        request = json.loads(json_message)
        log("Received request: {0}".format(request))
        if(request == "exit"):
            return
        elif(constants.REQ_TYPE not in request):
            response = {constants.SUCCESS_CODE: constants.REP_FAIL, constants.REP_ERROR_DESCRIPTION: "unsupported request type"}
            json_response = json.dumps(response)
            socket.send_string(json_response)
        else:
            response = serverRequestHandler.handleRequest(request)
            json_response = json.dumps(response)
            socket.send_string(json_response)

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)