## API

### [USER](../Backend/src/main/java/org/api/user/UserResources.java)

`http://25.67.249.190:8080/Chess/chess/user`
* GET => vrati uzivatele podle idecka

`http://25.67.249.190:8080/Chess/chess/user/authentication`
* GET => vrati prihlaseneho uzivatele
* POST => prihlasi uzivatele (vyzaduje username a passowrd)
* DELETE => odhlasi uzivatele

`http://25.67.249.190:8080/Chess/chess/user/settings`
* DELETE => smaze uzivatele (vyzaduje username, password)
* PUT => zmeni heslo (vyzaduje username a nove password)

### [LEADERBOARD](../Backend/src/main/java/org/api/leaderboard/LeaderBoardResource.java)


### [GAME](../Backend/src/main/java/org/api/game/GameResources.java)

