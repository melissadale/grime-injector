SETLOCAL EnableDelayedExpansion
cd ../Results

FOR /D /r %%G in ("*") do (
@echo decompiling
%~dp0/../Lib/jad -o "%%G/*.class"
@echo decompiled

@echo renaming
ren *.jad *.java
@echo renamed

@echo moving...
move *.java "%%G/"
@echo moved...
)

exit

::SET /P RESULT=[Delete .class files (y/n)]
::IF %RESULT% == yes do (
::    FOR /D /r %%G in ("*") do (
::    cd "%%G/"
::    del *.class
::    )
::)