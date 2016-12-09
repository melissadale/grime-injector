# grime-injector
This is a project that models [design pattern grime growths] (http://www.cs.montana.edu/izurieta/pubs/esem2010.pdf) on java projects. It is created for work in my [master's thesis](https://www.cs.montana.edu/techreports/1314/Dale.pdf) and [ESEM 2014 paper](http://dl.acm.org/citation.cfm?id=2652560), and while there is a large amount of room for design improvement - I am still rather proud of this tool I created on my own.

At a high level, this takes in a java project, modifies the bytecode to model 6 types of design pattern grime growths, 
decompiles back to human-readable Java code, and creates a SonarQube properties file so that Technical Debt scores may be calculated 
on the modified Java projects.

In depth explanation and example can be found here: www.cs.montana.edu/~mdale/grime-injector

A video of the grime-injector in action can be viewed here: [Injector Movie](https://www.youtube.com/watch?v=wIiU2TJmVKs).
It is not a terribly exciting video, but it is annotated with descriptions of what is happening and when. 

# Current Status
2016 Dec - In preparing for the qualifier, I have revisited this project for the first time in about 2 years. Aside from fixing a few small issues, it still works (for me at least, and I'm pretty confident for you, maybe with some help if needed). To the best of my knowledge, this program is working as expected. 


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

# How to Set Up the Grime Injector to use for Yourself
1. Download this project and import into Eclipse
2. Download [SonarQube and SonarQube Scanner](http://www.sonarqube.org/downloads/)
 * You will need to know the path of StartSonar.bat in SonarQube and SonarScanner.bat in Sonar-Scanner
3.  You may have to add Javassist and jad_decompile.bat to build path / build configuration
 * Right Click on jad_decompile.bat in the Lib directory
 * Build Path -> Add to Build Path
 * Right Click on javassist.jar -> Build Path -> "Configure Build Path"
 * Under "Libraries" tab, click add JARs and add the javassist jar
4. Change the paths at the top of GUI to match SonarQube and SonarScanner (lines 39-40)
```
String sserver_path = "C:\\sonarqube-6.1\\bin\\windows-x86-64\\StartSonar.bat";
String sscanner_path = "C:\\sonar-scanner-2.8\\bin\\sonar-scanner.bat";
```

# Analyze a project
* Copy/Paste the project you wish to model the grime growth into the "analyze_this" package
* Run the GUI class (note you will have to include relative paths to files if they are located in subdirectories) and Click Inject
* Start SonarQube Server
* Run sonar_drilldown.bat (in the Lib folder)
* Navigate to localhost:9000

# Tools used in the Grime Injector
* [SonarQube](http://www.sonarqube.org/)
 * SonarQube is the tool I used to measure technical debt. It is open source and a pretty cool - however, kind of a pain to integrate into the grime injector. I will do my best to give instructions on how to use it with the grime injector on your own computer. 
* [JAD: (Java Decompiler)](http://www.javadecompilers.com/jad). This java decompiler is included in the Grime Injector download, so you do not need to worry about finding it - just making note of it. 
* [Javassist](http://jboss-javassist.github.io/javassist/)
  * This is the package which allows the bytecode to be modified.

# Some items of note
 * I used batch files to launch the scripts to decompile and launch SonarQube, so you may run into issues on non-windows machines
 * While I am confident this program performed as expected on several different personal machines, I am much less confident it will work on all machines - so once again, please let me know if you are trying to use my program: melissa.r.dale@gmail.com I will be so happy to help it work for you.  
