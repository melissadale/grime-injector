@echo off

for /D /r %%G in (*.*) do (
	if exist %%G\sonar-project.properties ( 
		cd %%G\
		call C:\sonar-runner-dist-2.3\sonar-runner-2.3\bin\sonar-runner.bat
	)	
	)
