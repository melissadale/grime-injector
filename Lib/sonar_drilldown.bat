@echo off

for /D /r %%G in (*.*) do (
	if exist %%G\sonar-project.properties ( 
		cd %%G\
		call C:\sonarqube-6.1\bin\sonar-runner.bat
	)	
	)
