## API

### User Management

`http://25.67.249.190:8080/Chess/chess/user`
* GET => vrati prihlaseneho uzivatele, nebo null
* POST => prihlasi uzivatele (vyzaduje username a passowrd)

`http://25.67.249.190:8080/Chess/chess/user/settings`
* POST => vytvori uzivatele (vyzaduje username a password)
* DELETE => smaze uzivatele (username, password)
* PUT => zmeni heslo (vyzaduje username a nove password)

`http://25.67.249.190:8080/Chess/chess/users?id=1`
* GET => vrati uzivatele podle idecka