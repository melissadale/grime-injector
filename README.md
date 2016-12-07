# grime-injector
This is a project that models [design pattern grime growths] (http://www.cs.montana.edu/izurieta/pubs/esem2010.pdf) on java projects. I would be extremely excited/honored if you are interested in using this program. Please let me know how/why you're using it - and as there is a solid chance you might need help getting it up and running on your own machine - I more than welcome an email: melissa.r.dale@gmail.com 

A video of the grime-injector in action can be viewed here: [Injector Movie](https://www.youtube.com/watch?v=wIiU2TJmVKs)
It is not a terribly exciting video, but it is annotated with descriptions of what is happening and when. 

# Cite this Project
If you are using this tool for your research, here's some bibtex for you:

```
% Design Pattern Grime Injector
@misc{Dale2014GrimeInjector, 
   author = {Melissa R Dale}, 
   title = {Design Pattern Grime Injector}, 
   howpublished = {\url{https://github.com/melissadale/grime-injector}} 
} 
```

# Details of how this project functions
At a high level, this takes in a java project, modifies the bytecode to model 6 types of design pattern grime growths, 
decompiles back to a Java project, and creates a SonarQube properties file so that Technical Debt scores may be calculated 
on the modified Java projects.

In depth explanation and example can be found here: www.cs.montana.edu/~mdale/grime-injector


# How to Set Up the Grime Injector to use for Yourself
* Download project and import into Eclipse
* You may have to add Javassist and jad_decompile.bat to build path
 * Right Click on javassist.jar and jad_decompile.bat in the Lib directory
 * Build Path -> Add to Build Path
 * Under "Libraries" tab, click add JARs and add the javassist jar
* Download and Setup SonarQube and SonarQube Runner
 * Follow these directions: http://docs.sonarqube.org/display/SONAR/Setup+and+Upgrade
 

# Some items of note
 * This only works for Windows - I used (heavily) batch files to launch the scripts to decompile and launch SonarQube
 * While I am confident this program performed as expected on several different personal machines, I am much less confident it will work on all machines - so once again, please let me know if you are trying to use my program: melissa.r.dale@gmail.com I will be so happy to help it work for you. 


# Analyze a project
* Paste the project you wish to model the grime growth into the "analyze_this" package
* Run the GUI class (note you will have to include relative paths to files if they are located in subdirectories) and Click Inject
* Start SonarQube Server
* Run sonar_drilldown.bat (in the Lib folder)
* Navigate to localhost:9000


#Some more items of note
* WarpDrive.java is a file started to run InjectorLogic.java (to test - because I did not understand the importance of formal unit tests at the time - and run InjectorLogic.java)
* SonarConfigalizer.java soley creates the sonar-project.properties file. 
