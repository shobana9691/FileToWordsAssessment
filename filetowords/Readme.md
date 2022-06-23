# FileToWords

This project is a java implementation to fetch file as input and create output XML file with words and sentences provided in the input file.

# Build and Run

Maven is used to build project.


## 1. Install Java version 
+ Minimum version of Java must be 1.7
+ download and install java 1.8 JDK
+ set corresponding system environment variables and path:
+ JAVA_HOME e.g. C:\Program Files\Java\jdk1.8.0_192
+ JAVA_BIN %JAVA_HOME%\bin
+ check installation running on command line: **java -version** and **javac -version** 

## 2. Install Maven

### Maven setup

+ install maven to a local folder e.g. C:/opt/apache-maven-3.6.0-bin

+ edit user/account and system environment variables:
+ add/set account variable with name M2_HOME and corresponding value e.g. C:\opt\apache-maven-3.6.0-bin\apache-maven-3.6.0
+ add/set system variable with name MAVEN_HOME and value C:\opt\apache-maven-3.6.0-bin\apache-maven-3.6.0
+ add/set system variable with name MAVEN_BIN and value %MAVEN_HOME%\bin
+ set system variable with name path and add there c:/opt/apache-maven-3.6.0-bin;

+ check it works running on command line: **mvn -version**

###  Useful maven commands

+ to build the project:
  **mvn clean install**
+ to test the project:
  **mvn clean test**
+ to generate unit test case report:
  **mvn clean site**

## 3. Server

+ Since we use SpringBoot tomcat server will be embeded

## 4. Checkout Project

+ Checkout the project from GitHub

## 5. Run Application from Eclipse/Intellij IDE
+ Open Eclipse or Intellij IDE
+ Navigate to project folder of checked out code.
+ Navigate till pom.xml file of the folder
+ Open the project in IDE
+ Setup environment variable for LOGGER_PATH for log files
+ eg setup as LOGGER_PATH=C:\Development\Code\ to store the logs at specified location
+ Run the application from Intellij or Eclipse
+ Provide the file path correctly
+ Output files will be generated and file path will be displayed to make navigation easy

## 6. Run Application from command line

+ Navigate to project folder of checked out code.
+ Navigate till pom.xml file of the folder
+ Open cmd prompt
+ Type below command :
+ mvn spring-boot:run
+ Provide the file path correctly
+  Output files will be generated and file path will be displayed to make navigation easy


## 7. Test Reports

+ Unit test case reports can be found in surefire report.