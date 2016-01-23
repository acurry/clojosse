#!/bin/sh
# chmod u+x restful_test.sh

# Script to test RESTful routes in anglelax.

SERVER="localhost"
PORT="3000"
CONTEXT=""
URL="http://$SERVER:$PORT$CONTEXT"

echo "anglelax RESTful routes test\n"

echo "testing /teams\n"
echo "GET $URL/teams"
curl -X GET "$URL/teams"
# [{"city":"Washington","name":"Redskins","id":"1001"},{"city":"Philadelphia","name":"Eagles","id":"1002"},{"city":"Pittsburgh","name":"Steelers","id":"1003"},{"city":"New York","name":"Giants","id":"1004"},{"city":"Baltimore","name":"Ravens","id":"1000"}]
echo "\n"

# echo "PUT /teams/1000"
# curl -X PUT -H "Content-Type: application/json" -d '{"attrs": {"city": "Catonsville"}}' http://$SERVER:$PORT/$CONTEXTteams/1000
# # {"city":"Catonsville","name":"Ravens","id":"1000"}
# echo "\n"

# echo "GET /teams/1000"
# curl -X GET http://$SERVER:$PORT/$CONTEXTteams/1000
# # {"city":"Catonsville","name":"Ravens","id":"1000"}
# echo "\n\n"



# echo "testing /locations\n"
# echo "GET /locations"
# curl -X GET http://$SERVER:$PORT/$CONTEXTlocations
# # [{"poc":"eli@anglelax.com","name":"MetLife Stadium","address":{"street":"MetLife Stadium Dr.","city":"East Rutherford","zipCode":"07073","state":"NJ"},"homeTeamId":"1004","id":"3003"},{"poc":"michael@anglelax.com","name":"Lincoln Financial Field","address":{"street":"Lincoln Financial Field Way","city":"Philadelphia","zipCode":"19147","state":"PA"},"homeTeamId":"1002","id":"3004"},{"poc":"andy@anglelax.com","name":"M&T Bank Stadium","address":{"street":"Russell St.","city":"Baltimore","zipCode":"21230","state":"MD"},"homeTeamId":"1000","id":"3000"},{"poc":"matt@anglelax.com","name":"Fedex Field","address":{"street":"Fedex Way","city":"Landover","zipCode":"20785","state":"MD"},"homeTeamId":"1001","id":"3001"},{"poc":"ben@anglelax.com","name":"Heinz Field","address":{"street":"Art Rooney Ave.","city":"Pittsburgh","zipCode":"15212","state":"PA"},"homeTeamId":"1003","id":"3002"}]
# echo "\n"

# echo "PUT /locations/3000"
# curl -X PUT -H "Content-Type: application/json" -d '{"attrs": {"name": "Ravens House"}}' http://$SERVER:$PORT/$CONTEXTlocations/3000
# # {"poc":"andy@anglelax.com","name":"Ravens House","address":{"street":"Russell St.","city":"Baltimore","zipCode":"21230","state":"MD"},"homeTeamId":"1000","id":"3000"}
# echo "\n"

# echo "GET /locations/1000"
# curl -X GET http://$SERVER:$PORT/$CONTEXTlocations/3000
# # {"poc":"andy@anglelax.com","name":"Ravens House","address":{"street":"Russell St.","city":"Baltimore","zipCode":"21230","state":"MD"},"homeTeamId":"1000","id":"3000"}
# echo "\n\n"



# echo "testing /players\n"
# echo "GET /players"
# curl -X GET http://$SERVER:$PORT/$CONTEXTplayers
# # [{"lastName":"Kirkley","position":"Goalie","number":"71","dob":"1990-10-12T22:38:33.892Z","phoneNumber":"4103334444","teamId":"1001","emailAddress":"matt.kirkley@kirkley.com","id":"4004","firstName":"Matt"},{"lastName":"Griffin III","position":"Defense","number":"19","dob":"1983-05-12T22:38:33.892Z","phoneNumber":"4107778888","teamId":"1001","emailAddress":"robert@griffin.org","id":"4005","firstName":"Robert"},{"lastName":"Nakamura","position":"Midfield","number":"67","dob":"1995-08-12T22:38:33.892Z","phoneNumber":"4105556666","teamId":"1003","emailAddress":"hiro@heroes.com","id":"4006","firstName":"Hiro"},{"lastName":"Rotorooter","position":"Attack","number":"13","dob":"1984-06-12T22:38:33.892Z","phoneNumber":"4101233210","teamId":"1003","emailAddress":"ben@steelers.com","id":"4007","firstName":"Ben"},{"lastName":"Vick","position":"Attack","number":"82","dob":"1987-04-12T22:38:33.892Z","phoneNumber":"4106667777","teamId":"1002","emailAddress":"michael@vick.com","id":"4008","firstName":"Michael"},{"lastName":"Jackson","position":"Defense","number":"75","dob":"1999-12-12T22:38:33.892Z","phoneNumber":"4101919191","teamId":"1002","emailAddress":"desean@jackson.com","id":"4009","firstName":"DeSean"},{"lastName":"Curry","position":"Attack","number":"31","dob":"1983-10-12T22:38:33.892Z","phoneNumber":"4101112222","teamId":"1000","emailAddress":"andrew.d.curry@gmail.com","id":"4000","firstName":"Andrew"},{"lastName":"Braun","position":"Midfield","number":"22","dob":"1995-10-12T22:38:33.892Z","phoneNumber":"4104445555","teamId":"1000","emailAddress":"mike.braun@braun.com","id":"4001","firstName":"Mike"},{"lastName":"Passen","position":"Defense","number":"46","dob":"1993-10-12T22:38:33.892Z","phoneNumber":"4102223333","teamId":"1004","emailAddress":"dpass@dpass.com","id":"4002","firstName":"Derek"},{"lastName":"Manning","position":"Attack","number":"8","dob":"1995-11-12T22:38:33.892Z","phoneNumber":"4108889999","teamId":"1004","emailAddress":"eli@manning.com","id":"4003","firstName":"Eli"}]
# echo "\n"

# echo "PUT /players/4000"
# curl -X PUT -H "Content-Type: application/json" -d '{"attrs": {"firstName": "Andy", "position": "Defense"}}' http://$SERVER:$PORT/$CONTEXTplayers/4000
# # {"lastName":"Curry","position":"Defense","number":"31","dob":"1983-10-12T22:38:33.892Z","phoneNumber":"4101112222","teamId":"1000","emailAddress":"andrew.d.curry@gmail.com","id":"4000","firstName":"Andy"}
# echo "\n"

# echo "GET /players/4000"
# curl -X GET http://$SERVER:$PORT/$CONTEXTplayers/4000
# # {"lastName":"Curry","position":"Defense","number":"31","dob":"1983-10-12T22:38:33.892Z","phoneNumber":"4101112222","teamId":"1000","emailAddress":"andrew.d.curry@gmail.com","id":"4000","firstName":"Andy"}
# echo "\n\n"



# echo "testing /games\n"
# echo "GET /games"
# curl -X GET http://$SERVER:$PORT/$CONTEXTgames
# # [{"awayTeamId":"1003","homeTeamId":"1001","id":"2002","dateTime":"2014-10-10T22:38:33.892Z"},{"awayTeamId":"1004","homeTeamId":"1003","id":"2003","dateTime":"2014-10-09T22:38:33.892Z"},{"awayTeamId":"1000","homeTeamId":"1004","id":"2004","dateTime":"2014-10-08T22:38:33.892Z"},{"awayTeamId":"1003","awayTeamScore":0,"homeTeamScore":21,"homeTeamId":"1000","id":"2005","dateTime":"2013-10-12T22:38:33.892Z"},{"awayTeamId":"1002","awayTeamScore":21,"homeTeamScore":14,"homeTeamId":"1003","id":"2006","dateTime":"2013-10-11T22:38:33.892Z"},{"awayTeamId":"1004","awayTeamScore":0,"homeTeamScore":7,"homeTeamId":"1002","id":"2007","dateTime":"2013-10-10T22:38:33.892Z"},{"awayTeamId":"1001","awayTeamScore":34,"homeTeamScore":3,"homeTeamId":"1004","id":"2008","dateTime":"2013-10-09T22:38:33.892Z"},{"awayTeamId":"1000","awayTeamScore":47,"homeTeamScore":52,"homeTeamId":"1001","id":"2009","dateTime":"2013-10-08T22:38:33.892Z"},{"awayTeamId":"1002","homeTeamId":"1000","id":"2000","dateTime":"2014-10-12T22:38:33.892Z"},{"awayTeamId":"1001","homeTeamId":"1002","id":"2001","dateTime":"2014-10-11T22:38:33.892Z"}]
# echo "\n"

# echo "PUT /games/2000"
# curl -X PUT -H "Content-Type: application/json" -d '{"attrs": {"homeTeamId": "1004"}}' http://$SERVER:$PORT/$CONTEXTgames/2000
# # {"awayTeamId":"1002","homeTeamId":"1004","id":"2000","dateTime":"2014-10-12T22:38:33.892Z"}
# echo "\n"

# echo "GET /games/2000"
# curl -X GET http://$SERVER:$PORT/$CONTEXTgames/2000
# # {"awayTeamId":"1002","homeTeamId":"1004","id":"2000","dateTime":"2014-10-12T22:38:33.892Z"}
# echo "\n\n"

