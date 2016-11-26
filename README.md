# WhatsHapp

WhatsHapp is an all-in-one application for USC students to find non-Greek life events that are happening on and off campus. 
Users can log in, post their own events, or filter through a feed of events based on category (student-run, club affiliated, 
school-wide, etc.). Users can click attending, maybe, or not attending on each posting on the feed, which is valuable for the 
user and the host. Filter events based on location and time, or search for whatever type of event.

To run WhatsHapp on your machine:
  1. Make sure to have the latest version of the Java Development Kit (JDK 8) installed on your machine. The download can be 
  found here: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  2. Download and install the Eclipse IDE, which can be found at this link: https://eclipse.org/downloads/
    a. Windows users: download 32-bit or 64-bit based on your hardware (older computers may need 32-bit)
    b. Mac users: 64-bit is the only choice
  3. Download and install MySQL Workbench:
    a. Windows users: download at this link http://dev.mysql.com/downloads/windows/installer/, create a username and password, 
    sign in with username and password and set up connection. 
    b. Mac users: 
    Visit this link http://dev.mysql.com/downloads/mysql/ and download the .dmg file (not the .tar file). 
    After installation, go to System Properties, select MySql, and select “Start MySQLServer” to start running the server.
    Visit http://dev.mysql.com/downloads/workbench/ to download and install MySQLWorkbench
  4. Launch MySQLWorkbench
  5. Copy the contents of the .sql files from the WHDatabase.zip file and paste the contents into MySQLWorkbench, run scripts 
  to create schemata
  6. Run Eclipse within any workspace
  7. Import the WhatsHapp.zip file into Eclipse, generating a project of the same name
  8. Run WhatsHappClient for each client that wants to use the program - this is a .java file located in the default package.
  9. Run the WhatsHappServer to host a server - this is also a .java file hosted in the default package.
