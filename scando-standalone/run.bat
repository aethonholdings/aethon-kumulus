echo off

cls

echo ---------------------------------------------------------------------------------
echo Executing ScanDo Application
echo ---------------------------------------------------------------------------------
echo.

java -javaagent:lib\object-size-fetcher-1.0.jar -Xms2048m -Xmx2048m -jar ScannerApp.jar