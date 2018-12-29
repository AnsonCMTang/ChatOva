# ChatOva

### Android Studio

Install [Android Studio](https://developer.android.com/studio/)

### SQL

Installations:
- Install [SQL Server Management Studio (SSMS)](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-2017):
- Install [SQL Server](https://www.microsoft.com/en-gb/sql-server/sql-server-downloads) (developer or express)
  - Basic installation should work
  
Setting up:
- Once both SQL Server Management Studio (SSMS) and SQL Server are installed, run SSMS.
- Use the following settings:
  - Server type: Database Engine
  - Server name: Click on arrow -> Browse for more -> Database Engine -> Your server
  - Windows Authentication
- Open *restore_database.sql* and choose a database name. Then run the file.
- After running the file, refresh the *Databases* folder to see the new database.
 
### Visual Studio

Install [Visual Studio 2017](https://visualstudio.microsoft.com/) (can be community version): 
  - Need .NET desktop development
  - ASP.NET and web development
  - .NET Core cross-platform development

Running the backend server:
  - Click Run for IIS EXPRESS
  - The SSL setting can be removed (i.e. change from https to http) by changing sslPort to 0 in Properties -> launchSettings.json
