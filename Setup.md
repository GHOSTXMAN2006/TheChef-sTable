Project Setup Guide: The Chef's Table

📝 Overview
This document outlines the steps required to set up and run "The Chef's Table" restaurant management system. The project source code and necessary files are provided in the repository.



🚨 CRITICAL KNOWN ISSUE (NetBeans Configuration Error):
Due to persistent NetBeans configuration errors, a stable standalone executable (.jar or .exe) could not be generated. 
Therefore, the application must be run directly from the source code within the Apache NetBeans IDE. All testing and execution should be performed using the IDE's internal run command.



💻 System Prerequisites
Please ensure the following software components are installed on your machine:

Component	                        Minimum  Version	                   Purpose
Java Development Kit (JDK)	      17	                                 Required for compiling and running Java applications.
Apache NetBeans IDE	              12.0	                               Required to run the project from source.
XAMPP	                            Latest stable	                       Required to host the MySQL database locally (using Apache and MySQL services).



⚙️ Configuration and Setup
Follow these steps carefully to configure the project environment.

Step 1: Obtain and Prepare Project Files
Extract the main project source code from the repository file EAD1-Repeat.7z to a suitable directory (e.g., D:\Projects\).

Launch the Apache NetBeans IDE.

Go to File → Open Project and select the extracted project folder.

Step 2: Start XAMPP and Configure Database
The application uses a MySQL database, which must be running via XAMPP.

Launch the XAMPP Control Panel.

Start the Apache and MySQL services.

Create Database: Access phpMyAdmin (usually via http://localhost/phpmyadmin) and create a new schema named ead1_repeat_db (this is the default name used by the application).

Import Data: Run the provided SQL script ead1-repeat-fix.sql against the newly created ead1_repeat_db schema. This script contains the table structure and initial data.

Step 3: Verify Database Connection Details
You must ensure the application's Java code has the correct credentials for the database.

In the NetBeans Projects window, navigate to the source code folder and open the file: DatabaseConfig.java.

Verify or Edit the following connection details to match your XAMPP/MySQL configuration (defaults are usually root without a password):

Property	        Default Value	                                                Notes
DB_URL	          jdbc:mysql://localhost:3307/ead1_repeat_db	                  Ensure the database name is ead1_repeat_db
DB_USER	          root	                                                        Change if you set a custom MySQL user.
DB_PASSWORD	      "" (empty string)	                                            Change if you set a MySQL root password.



Step 4: Clean and Build the Project
This ensures all dependencies are correctly compiled and linked internally by NetBeans.

Right-click on the project name in the "Projects" window.

Select "Clean and Build."

Verify that the "Output" window at the bottom of the IDE shows BUILD SUCCESSFUL.

📦 Project Dependencies (Required Libraries)
The necessary external library files (JARs) are already included within the project's lib folder in the repository. These dependencies enable crucial functionality like reporting and database connectivity.

Dependency	                          Version	                 Purpose/Notes
mysql-connector-j	                    9.0.0	                   JDBC Driver for MySQL database connection.
jasperreports	                        7.0.3	                   The main reporting engine.
itextpdf	                            5.5.13.4	               PDF generation library (for report exports).
jfreechart	                          1.5.6	                   Charting library (used for generating graphs).
jcommon	                              1.0.23	                 Core library for JFreeChart.
AbsoluteLayout	                      N/A	                     Swing/AWT layout helper used in the GUI.
jackson-core	                        2.20.0	                 Jackson core processing library for data binding.
jackson-annotations	                  3.0-rc5	                 Jackson annotations for data binding.
jackson-databind	                    2.20.0	                 Jackson data binding library.
jackson-dataformat-xml	              2.20.0	                 Jackson XML parser for JRXML files.
commons-logging	                      1.2	                     Apache logging utility.
commons-beanutils	                    1.11.0	                 Apache utilities for JavaBeans.
commons-collections4	                4.1	                     Apache collection data structures.
commons-digester	                    2.1	                     Apache XML-to-object mapping tool.
stax2-api	                            4.2.2	                   STAX2 XML API interface.
woodstox-core	                        7.1.1	                   High-performance XML implementation.



▶️ Running the Application
Run the application ONLY using the NetBeans IDE.

In the NetBeans IDE, ensure the project is selected.

Click the Run Project button (green play icon) on the main toolbar, or press the F6 key.

The application will start, and the Login Window should appear, confirming the setup is successful.
