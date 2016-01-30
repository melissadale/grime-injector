# grime-injector
This is a project that models design pattern growth on java projects. I would be extremely excited/honored if you are interested in using this program. Please let me know how/why you're using it - and as there is a solid chance you might need help getting it up and running on your own machine - I more than welcome an email: melissa.r.dale@gmail.com 

# Details of how this project functions
At a high level, this takes in a java project, modifies the bytecode to model 6 types of design pattern grime growths, 
decompiles back to a Java project, and creates a SonarQube properties file so that Technical Debt scores may be calculated 
on the modified Java projects.

In depth explanation and example can be found here: www.cs.montana.edu/~mdale/grime-injector


# How to Set Up
* Download project and import into Eclipse
* Add Javassist to build path
 * Right Click on javassist.jar in the Lib
 * Build Path -> Configure Build Path
 * Under "Libraries" tab, click add JARs and add the javassist jar

* Download and Setup SonarQube and SonarQube Runner
 * Follow these directions: http://docs.sonarqube.org/display/SONAR/Setup+and+Upgrade

# Analyze a project
* Create a java package called "analyze_this"
* Paste the project you wish to model the grime growth into the "analyze_this" package
* Run the GUI class
* Start SonarQube Server
* Run sonar_drilldown.bat (in the Lib folder)
* Navigate to localhost:9000
