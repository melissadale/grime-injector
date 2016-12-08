# grime-injector
This is a project that models [design pattern grime growths] (http://www.cs.montana.edu/izurieta/pubs/esem2010.pdf) on java projects. It is created for work in my [master's thesis](https://www.cs.montana.edu/techreports/1314/Dale.pdf) and [ESEM 2014 paper](http://dl.acm.org/citation.cfm?id=2652560), and while there is a large amount of room for design improvement - I am still rather proud of this tool I created on my own. Please let me know how/why you're using it - and as there is a solid chance you might need help getting it up and running on your own machine - I more than welcome an email: melissa.r.dale@gmail.com 

At a high level, this takes in a java project, modifies the bytecode to model 6 types of design pattern grime growths, 
decompiles back to human-readable Java code, and creates a SonarQube properties file so that Technical Debt scores may be calculated 
on the modified Java projects.

In depth explanation and example can be found here: www.cs.montana.edu/~mdale/grime-injector

A video of the grime-injector in action can be viewed here: [Injector Movie](https://www.youtube.com/watch?v=wIiU2TJmVKs).
It is not a terribly exciting video, but it is annotated with descriptions of what is happening and when. 

# Current Status
2016 Dec - In preparing for the qualifier, I have revisited this project for the first time in about 2 years. Aside from fixing a few small issues, it still works (for me at least, and I'm pretty confident for you, with some help if needed). 

The current issue is SonarQube - which has been largely updated since the grime injector was created. I am working on fixing the script which automatically launches SonarQube. Right now, you should be able to run the grime injector and produce modified java classes. The SonarQube Properties file is still created, and so you may run SonarQube on the modified classes until the script is fixed.

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
* Download project and import into Eclipse
* You may have to add Javassist and jad_decompile.bat to build path
 * Right Click on javassist.jar and jad_decompile.bat in the Lib directory
 * Build Path -> Add to Build Path
 * Under "Libraries" tab, click add JARs and add the javassist jar
* Download and Setup SonarQube and SonarQube Runner
 * OUTDATED: Follow these directions: http://docs.sonarqube.org/display/SONAR/Get+Started+in+Two+Minutes

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
