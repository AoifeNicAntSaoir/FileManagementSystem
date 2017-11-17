# FileManagementSystem
Simple File Management System allowing a user to login, logout, register, upload or download a file using Datagram Sockets in a Java console. Distributed Computing Project 4th year

- Stores users details in text file in format *myUsername, myPassword* in DistributedComputingFileMgmtSystem\users\Usert.txt
- Creates users own folder for each user - user/*myUsername* in DistributedComputingFileMgmtSystem\users



## Implementation of my own protocol -Server Codes
- 1 - Login
- 2 - Logout 
- 3 - Upload 
- 4 - Download 
- 5 - Quit (Client)
- 6 - Register
- 500 -  Login success 
- 501 - Login unsuccessful
- 600 - Register success  
- 601 - Register unsuccessful
-  700 - Logout successful 
- 701 - Logout unsuccessful 
-  800 - Upload success  
- 801 - Upload unsuccessful 
- 900 Download success
- 901 Download unsuccessful
- 00 Server Error
   
----------
## Installation ##

 1. javac *.java
 2. java Server.java
 3. java Client.java


