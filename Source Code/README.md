1.
Create a mysql db called "aic". 
Current settings: username and password are both "root".

2.
Run the create_relational_db.sql on your mysql.

3.
Install MongoDb and create the data folder: it is a folder structure ending with "..\data\db" + Start MongoDb (see COMMANDS)
Install Neo4J and leave the default username and password (neo4j, root) + Start Neo4J (see COMMANDS)

4.
Download tomcat 8, integrate it into eclipse with java 7, or 8.

5.
Import project as maven project into eclipse, deploy and run on tomcat 8.

6.
http://localhost:8080/aic15/ in browser
admin, admin are login credentials

COMMANDS:
Run MongoDB: mongod.exe --C:\pathToDataFolder\data\db
Run NEO4J: Use UI manager to start