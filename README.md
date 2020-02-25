# OwlChat
A program for practicing using Vector Clock and UDP protocol.

# Start the server 
The server can be started from the command line. Use the following command:  
$java -jar chat_server.jar

# Procedure
- User register an user name.
- User receives an ACK from server if registration succeeds.
- The client sends a "retrieve_chat_log" message to the server.
- The server will send back multiple messages to the client. However, pay attention that these messages are sent in a random order
- The client must buffer all the received messages until no other message is received. This can be handled again using a socket timeout.
- Before displaying the messages, the client must sort the received messages using the attached vector clocks.
- User can deregister the chosen user name.

# Message Specification:
- Register: the client must first send this JSON message to the server before any other interaction can be initiated. For registration purposes, there is no need to send a timestamp.
- Deregister: the client must send a message to the server if he wants to deregister. The server keeps track of all the registered users. Again, for the deregistration, there is no need to send a timestamp.
- retrieve chat log: the client wishes to retrieve a chat log from the server, which he further has to process before displaying.
