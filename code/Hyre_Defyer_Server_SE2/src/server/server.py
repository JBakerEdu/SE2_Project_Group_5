'''
Created on Mar 10, 2025

@author: alecx
'''
import socket
import json
from src.server import constants
from src.server.server_request_handler import ServerRequestHandler

def log(message):
    print("SERVER::{0}".format(message))

def main():
    serverRequestHandler = ServerRequestHandler()
    HOST = constants.IP_ADDRESS
    PORT = int(constants.PORT)

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen(1)

    while True:
        log("⌛ Waiting for client...")
        client_socket, addr = server_socket.accept()
        log(f"🔗 Client connected from {addr}")

        try:
            message_length = int.from_bytes(client_socket.recv(4), byteorder="big")
            log(f"📏 Expected message size: {message_length} bytes")

            json_message = client_socket.recv(message_length).decode("utf-8")
            log(f"📩 Received raw data: {json_message}")

            request = json.loads(json_message)
            log(f"✅ Parsed request: {request}")
            
            response = serverRequestHandler.handleRequest(request)
            json_response = json.dumps(response)
            log(f"📤 Sending response: {json_response}")
            
            message_bytes = json_response.encode("utf-8")
            client_socket.send(len(message_bytes).to_bytes(4, byteorder="big"))
            client_socket.send(message_bytes)
            client_socket.flush()

        except json.JSONDecodeError:
            log("❌ Invalid JSON received")
        finally:
            client_socket.close()
            log("🔻 Client disconnected")

if (__name__ == "__main__"):
    main()