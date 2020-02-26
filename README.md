# Student Info
Name: Hieu Khuong  
CIS4307 - Distributed Systems and Networks  
Spring 2020  
Homework 3: Vector Clocks and Lamport Clocks  

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

# Design  
Clock part:  
- Clock interface: provides all methods for time tracking.  
- LamportClock class: uses an int to keep track of logical time.
- VectorClock class: uses a map to keep track of series logical time attached to a certain process ID.  
- Message class: parsed from Json file retrieved from server, has a content field and a vector clock.
- MessageComparator class: given 2 messages, decides which message happens before the other.  

Android part:  
- MainActivity:
  * An Edittext to put user name in.
  * Setting button to change ip address and port number settings.
  * Join button to register user. Join button will send a register message to server, and open ChatActivity if it receives ACK message. Otherwise it will display error messages as Toasts.
- SettingActivity: 
  * Two Edittext fields to change ip address and port. Current ip address and port would be prefilled. Only change ip address and port if those values are valid. If invalid, display error message as Toasts, and keep the current ip address and port.
  * A confirm button to go back to MainActivity.
- ChatActivity: 
  * A ListView to display the retrieved messages. The ListView would use an Adapter to display the list of messages.
  * Deregister button to deregister the user. When it receives ACK message from server, switch back to MainActivity. Otherwise, display error Toasts.
  * ChatLog button to retrieve chat from server. Send a retrieve_chat_log request to server, and fetch all the packet from server until no more available. Then convert the packet into Message objects. Put the messages into an array, and sort the array in causal order. Display the message array when finish sorting.
