# SimpleChat
This is a simple java based chat for messagng among many users


This project consist of some parts that shown below.
ChatServer - java socket based chat server which handles all client connections and attaches them to new ClientHandlers. We will use java ServerSocket for this project.
ClientHandler - is created immediately after accepting new client connection and client attaches to this handler. This handler works on separate thread and handles messages from clients and send messages to them.
ClientHandlerRegistry - this registry holdes all created client handlers for further use.
RegistryHolder - holds ClientHandlerRegistry instance for accessing from all components of app.
Client - is client model. Has two field. id and name.
RequestObject - consist of two parts. Header and Body. Header holds receiverId, Body holds message.
ResponseObject - consist of two parts. Header and Body. Header holds senderId, Body holds message.



When ChatServer accept new client connection it immediately creates new ClientHandler instance and attaches client socket and client objects to it. After thar ChatServer starts ClientHandler in separate thread. 
ClientHandler works on separated thread and handles requests from client and sends message to clients if there is some.

In project Main class starts ChatServer. TestClient attempt to connect to server. If connection succeed then gets input and output streams from newly created socket. Listens input stream in separated thread and writes to output stream from the console input.
You have to send message in RequestObject - equivalent json format. 
RequestObject : {"header":{	"receiverId":"1"},"body":{"message":"HELLO MEM!"}}
ResponseObject : {"header":{	"senderId":"0"},"body":{"message":"HELLO MEM!"}}

