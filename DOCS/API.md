# API

* * *
* * *

### [USER](../Backend/src/main/java/org/api/user/UserResources.java)

`http://25.67.249.190:8080/Chess/chess/user`
* GET => vrati uzivatele podle idecka

`http://25.67.249.190:8080/Chess/chess/user/authentication`
* GET => vrati prihlaseneho uzivatele
* POST => prihlasi uzivatele (vyzaduje username a passowrd)
* DELETE => odhlasi uzivatele

`http://25.67.249.190:8080/Chess/chess/user/authentication/new`
* POST => vytvori uzivatele (vyzaduje username a password)

`http://25.67.249.190:8080/Chess/chess/user/delete`
* DELETE => smaze uzivatele (vyzaduje username, password)

`http://25.67.249.190:8080/Chess/chess/user/passsword`
* PUT => zmeni heslo (vyzaduje username a nove password)

`http://25.67.249.190:8080/Chess/chess/user/username`
* PUT => zmeni jmeno (vyzaduje nove username a heslo)

`http://25.67.249.190:8080/Chess/chess/user/challenge`
* GET => vrati list vsech pending challengi (tech pro tebe)
* POST => vytvori novou challenge (vyzaduje id druheho hrace)

`http://25.67.249.190:8080/Chess/chess/user/challenge/{id}`
* PUT => prijme challeng s danym id

* * *
* * *

### [LIDLBOARD](../Backend/src/main/java/org/api/leaderboard/LeaderBoardResource.java)

`http://25.67.249.190:8080/Chess/chess/lidlboard/top-50`
* GET => vrati top 50 uzivatelu podle score
#

* * *
* * *

### [GAME](../Backend/src/main/java/org/api/game/GameResources.java)

