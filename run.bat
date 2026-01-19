@echo off
cd /d "%~dp0"
java -cp "build/classes;lib/sqlite-jdbc-simple.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/itextpdf-5.5.9.jar" cafe.management.system.CafeManagementSystem
pause
