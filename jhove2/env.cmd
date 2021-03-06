@ECHO OFF
REM JHOVE2 -- Next-generation architecture for format-aware characterization.
REM Copyright 2009-2010 by The Regents of the University of California, Ithaka
REM Harbors, Inc., and The Board of Trustees of the Leland Stanford Junior
REM University.  All rights reserved.
REM
REM This file is called by all the other .cmd files in order to set JAVA,
REM JHOVE2_HOME, and CP. Edit this file if you need to modify the settings for
REM these variables or define additional variables, such as JAVA_HOME. For more
REM information, refer to the JHOVE2 User's Guide at http://jhove2.org.

REM If JAVA_HOME is not set, use java.exe in execution path
if "%JAVA_HOME%" == "" (
   set JAVA=java
) else (
   set JAVA="%JAVA_HOME%\bin\java"
)

REM JHOVE2_HOME must point to home directory of JHOVE2 install.
SET JHOVE2_HOME=%~dp0

REM CP must contain a semicolon-separated list of JARs used by JHOVE2.
SET CP=%JHOVE2_HOME%\config\droid;%JHOVE2_HOME%\config

FOR /R %JHOVE2_HOME%/lib %%a in (*.jar) DO CALL :AddToPath %%a
REM ECHO %CP%
GOTO :EOF

:AddToPath
SET CP=%CP%;%1
GOTO :EOF
