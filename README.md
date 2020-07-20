# RouletteGame
===================

This project is a REST application,  was build using SpringBoot , Hibernate, Maven and Oracle.

Rules
-----
 - new players are credited 100 currency;
 - players can join or create a room;
 - a room can have a maximum of four players;
 - if a creator leaves the room then the room is closed and all players are removed;
 - players currencies should move with them if they move from one room to another;
 - any player in a room can place bets;
 - if a player leave a room all unplaced bets will be removed;
 - only the room creator can spin the wheel;

API
---

### Create a player ###

    method                : POST
    url                   : /players
    example response body : {"id": "<id of the player>"}
    
### Create a room ###

    method                : POST
    url                   : /rooms
    example request body  : {"playerId": "<id of player creating the room>"}
    example response body : {"id": "<id of the room>"}

The client will provide the id of the player creating the room.

### Join a room ###

    method                : POST
    url                   : /players/{playerId}/rooms/{roomId}/registrations
    example response body : {"success": <true or false>}

The client will provide the id of the player and id of the room where will be registered.

### Leave a room ###

    method                : DELETE
    url                   : /players/{playerId}/rooms/{roomId}
    example response body : <empty>

The client will provide the id of the player and id of the room where will be unregistered.

### Make a bet ###

    method                : POST
    url                   : /players/{playerId}/rooms/{roomId}/bets
    example request body  : {"betType": "<describes the bet type e.g. row, column, redorblack, oddoreven, number>", "betAmount": <amount the player is betting>, "number": <any number 0-36>}
    example response body : <empty>

The client will provide the id of the player and id of the room and the details for bet.

### Spin the wheel ###

    method                : PUT
    url                   : /players/{playerId}/rooms/{roomId}/spins
    example response body : <empty>

The client will provide the id of the player and id of the room in order to make the spin.

### Get the Room state ###

    method                : GET
    url                   : /rooms/{roomId}
    example response body : //:TODO
