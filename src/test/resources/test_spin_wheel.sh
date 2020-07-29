#!/bin/bash
#this represent the base url
api=http://localhost:8080

#this function is meant to validate the HTTP STATUS CODE
validate(){
if [[ $1 == *"error"* ]]
then 
	echo "$1"
	exit 1
fi
}

response=$(curl -s -X POST $api/players)
validate "$response"
echo Succesulf add new player: "$response"
response=$(curl -s -X POST $api/players)
validate "$response"
echo Succesulf add new player: "$response"
response=$(curl -s -X POST $api/players)
validate "$response"
echo Succesulf add new player: "$response"
response=$(curl -s -X POST $api/players)
validate "$response"
echo Succesulf add new player: "$response"
response=$(curl -s -d '{"playerId":1}' -H "Content-Type: application/json"  -X POST $api/rooms)
validate "$response"

echo Succesulf add new room: "$response"
response=$(curl -s -d '{"playerId":2}' -H "Content-Type: application/json"  -X POST $api/rooms)
validate "$response"
echo Succesulf add new room: "$response"

response=$(curl -s -X POST $api/players/4/rooms/2/registrations)
validate "$response"
echo Succesulf join player 4 into room 2
response=$(curl -s -X POST $api/players/3/rooms/2/registrations)
validate "$response"
echo Succesulf join player 3 into room 2

response=$(curl -s -d '{ "betType":"row","betAmount":20,"playerId":3,"betTypeValue":2}' -H "Content-Type: application/json"  -X POST $api/players/3/rooms/2/bets)
validate "$response"
echo Succesulf bet was added: '{ "betType":"row","betAmount":20,"playerId":3,"betTypeValue":2}'

response=$(curl -s -X POST $api/players/2/rooms/2/spins)
validate "$response"
echo The lucky number is: "$response"