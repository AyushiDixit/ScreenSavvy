# ScreenSavvy

#### Holy Commandments:
1. DO NOT COMMIT TO MAIN
2. Before doing ANYTHING with code, perform GIT PULL!!!!!

#### SETUP

##### Before you clone
1. Make sure to install git https://git-scm.com/downloads
2. Open git CMD/CLI
3. git config
3a. Set your github username: git config --global user.name "GITHUB_USERNAME"
3b. Set your github email address: git config --global user.email "MY_NAME@example.com"


##### Visual Studio Code
1. Source Control (ctrl+shift+g)
2. Clone from repository
3. https://github.com/AyshuDixit/ScreenSavvy.git
4. Accept any authorization requests
5. Choose location as htdocs(xampp) or www(wamp)
6. Would you like to open folder in workspace? Yes

###### git clone https://github.com/BreakfastDeluxe/FYP-23-S1-05

###### Selecting your branch
1. VSCODE: Left Side of Screen, Source Control
2. 3 dots next to refresh icon
3. Checkout to...
4. Choose origin/yourname

###### git PULL - To be done before you do any coding
1. Source Control (Crtl+shift+g)
2. "3 dots" > pull/push > Pull from... > origin(select this!)
3. select origin/main
4. select sync changes


System :  
IDE : visual studio code 
database : SSMS 19 (developer edition) , mysql (download both) and sql server if havent already downloaded. 
language : Python ( have to download python 3.6 and above from python official website) 

Libraries used ( will be updated as we continue to code.) 
- use 'pip install' or 'pip3 install' based on your machine. 
   1. pyodbc 
   2. flask 
   3. bcrypt
   4. re

For database : 

- Using ssms 19 developer edition. 
  1. create table UserDetails with columns : ( [username : varchar(50) ] ,[password : varbinary(max)]  ,[email : varchar(max) ] ,[phone : int ] ,[status : varchar(50) ]
