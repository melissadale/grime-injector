@echo off
set arg1=%1
echo Hello Beautiful, let's see if we can give this a go

for /D /r %%G in (*.*) do (
	if exist %%G\sonar-project.properties ( 
		cd %%G\
		call %arg1%
	)	
	)

pause