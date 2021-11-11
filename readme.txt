Java version : 11
JDK : 16

Since ready data is created at the beginning of the project, it is sufficient to start the project once.

!!! This is how api versioning is added to headers for all apis -> API-VERSION=1 !!!

****TEAMS CONTROLLER****
GET MAPPING
To see the all teams content endpoint -> localhost:8080/teams

GET MAPPING
To see the spesific team content endpoint -> localhost:8080/teams/{id}
example : "localhost:8080/teams/2c9e80817d0f6f29017d0f6f34160008"

POST MAPPING
To add a team to the teams content endpoint -> localhost:8080/teams
with body -> example :
{
"name" : "Fenerbahçe"
}
if result success return location inside of Headers.Location : "http://localhost:8080/teams/{newTeamId}"

PUT MAPPING
To update a team content endpoint -> localhost:8080/teams/{id}
with body-> example :
{
"name" : "Galatasaray"
}

DELETE MAPPING
Too delete the spesific team endpoint -> localhost:/teams/{id}
example : "localhost:8080/teams/2c9e80817d0f6f29017d0f6f34160008"

* Because of the one-to-many link, you cannot delete that team if it has players in it, you have to delete the players first.  *


****FOOTBALLERS CONTROLLER****
GET MAPPING
To see the all footballers endpoint -> localhost:8080/footballers

GET MAPPING
To see the spesific footballer endpoint -> localhost:8080/footballers/{id}
example : "localhost:8080/footballers/2c9e80817d0f6f29017d0f6f34160008"

POST MAPPING
To add a footballer endpoint -> localhost:8080/footballers
with body -> example :
{
    "firstName": "SampleFirstName",
    "lastName": "SampleLastName",
    "age": 22,
    "nationality": 12,
    "position": 2,
    "teamId": "2c9e80817d0f6f29017d0f6f34120006"
}
if result success return location inside of Headers.Location : "http://localhost:8080/footballers/{newFootballerId}"

PUT MAPPING
To update a footballer end point ->localhost:8080/footballers/{id}
with body -> example :
{
    "age": 22,
    "position": 2,
    "teamId": "2c9e80817d0f6f29017d0f6f34120006"
}

DELETE MAPPING
To delete a footballer endpoint -> localhost:8080/footballers/{id}






