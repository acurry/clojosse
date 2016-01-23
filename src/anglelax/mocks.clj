(ns anglelax.mocks
    (:require [clojure.data.json :as json]
              [clojurewerkz.scrypt.core :as sc]))

(defn- encrypt [p]
    (sc/encrypt (str p) 16384 8 1))

(def users (atom {
    "harbaugh" {
        "username" "harbaugh"
        "password" (encrypt "harbaugh")
        "role" ::coach
        "id" "5000"
        "teamId" "1000"
    } "tomlin" {
        "username" "tomlin"
        "password" (encrypt "tomlin")
        "role" ::coach
        "id" "5001"
        "teamId" "1003"
    }}))

(def teams (atom
    { "1000" {
        "city" "Baltimore"
        "name" "Ravens"
        "id" "1000"
        "coachId" "5000"
    } "1001" {
        "city" "Washington"
        "name" "Redskins"
        "id" "1001"
    } "1002" {
        "city" "Philadelphia"
        "name" "Eagles"
        "id" "1002"
    } "1003" {
        "city" "Pittsburgh"
        "name" "Steelers"
        "id" "1003"
        "coachId" "5001"
    } "1004" {
        "city" "New York"
        "name" "Giants"
        "id" "1004"
    }}))

(defn get-teams []
    (vals @teams))

(defn get-team [id]
    (@teams id))

(defn update-team [id attrs]
    (let [new-attrs (merge (get-team id) attrs)]
        (swap! teams assoc id new-attrs)
        new-attrs))

(def players (atom
     { "4000" {
        "firstName" "Andrew",
        "lastName" "Curry",
        "emailAddress" "andrew.d.curry@gmail.com",
        "phoneNumber" "4101112222",
        "dob" "1983-10-12T22:38:33.892Z",
        "position" "Attack",
        "teamId" "1000",
        "number" "31"
        "id" "4000"
     } "4001" {
        "firstName" "Mike",
        "lastName" "Braun",
        "emailAddress" "mike.braun@braun.com",
        "phoneNumber" "4104445555",
        "dob" "1995-10-12T22:38:33.892Z",
        "position" "Midfield",
        "teamId" "1000",
        "number" "22"
        "id" "4001"
     } "4002" {
        "firstName" "Derek",
        "lastName" "Passen",
        "emailAddress" "dpass@dpass.com",
        "phoneNumber" "4102223333",
        "dob" "1993-10-12T22:38:33.892Z",
        "position" "Defense",
        "teamId" "1004",
        "number" "46"
        "id" "4002"
     } "4003" {
        "firstName" "Eli",
        "lastName" "Manning",
        "emailAddress" "eli@manning.com",
        "phoneNumber" "4108889999",
        "dob" "1995-11-12T22:38:33.892Z",
        "position" "Attack",
        "teamId" "1004",
        "number" "8"
        "id" "4003"
     } "4004" {
        "firstName" "Matt",
        "lastName" "Kirkley",
        "emailAddress" "matt.kirkley@kirkley.com",
        "phoneNumber" "4103334444",
        "dob" "1990-10-12T22:38:33.892Z",
        "position" "Goalie",
        "teamId" "1001",
        "number" "71"
        "id" "4004"
    } "4005" {
        "firstName" "Robert",
        "lastName" "Griffin III",
        "emailAddress" "robert@griffin.org",
        "phoneNumber" "4107778888",
        "dob" "1983-05-12T22:38:33.892Z",
        "position" "Defense",
        "teamId" "1001",
        "number" "19"
        "id" "4005"
    } "4006" {
        "firstName" "Hiro",
        "lastName" "Nakamura",
        "emailAddress" "hiro@heroes.com",
        "phoneNumber" "4105556666",
        "dob" "1995-08-12T22:38:33.892Z",
        "position" "Midfield",
        "teamId" "1003",
        "number" "67"
        "id" "4006"
    } "4007" {
        "firstName" "Ben",
        "lastName" "Rotorooter",
        "emailAddress" "ben@steelers.com",
        "phoneNumber" "4101233210",
        "dob" "1984-06-12T22:38:33.892Z",
        "position" "Attack",
        "teamId" "1003",
        "number" "13"
        "id" "4007"
    } "4008" {
        "firstName" "Michael",
        "lastName" "Vick",
        "emailAddress" "michael@vick.com",
        "phoneNumber" "4106667777",
        "dob" "1987-04-12T22:38:33.892Z",
        "position" "Attack",
        "teamId" "1002",
        "number" "82"
        "id" "4008"
    } "4009" {
        "firstName" "DeSean",
        "lastName" "Jackson",
        "emailAddress" "desean@jackson.com",
        "phoneNumber" "4101919191",
        "dob" "1999-12-12T22:38:33.892Z",
        "position" "Defense",
        "teamId" "1002",
        "number" "75"
        "id" "4009"
    }}))

(defn get-players []
    (vals @players))

(defn- find-players-by [k v] 
    (filter 
        #(= (get % k) v) 
        (get-players)))

(defn get-players-by-coach [coach-user]
    (let [team-id (get coach-user "teamId")]
        (find-players-by "teamId" team-id)))

(defn get-public-players []
    (map 
        (fn [player] 
            {"firstName" (get player "firstName") 
             "lastName" (get player "lastName") 
             "number" (get player "number") 
             "position" (get player "position")
             "teamId" (get player "teamId")}) (get-players)))

(defn get-player [id]
    (@players id))

(defn update-player [id attrs]
    (let [new-attrs (merge (get-player id) attrs)]
        (swap! players assoc id new-attrs)
        new-attrs))

(defn find-players-on-team [team-id]
    (find-players-by "teamId" team-id))

(def games (atom 
    { "2000" {
        "homeTeamId" "1000",
        "awayTeamId" "1002",
        "dateTime" "2014-10-12T22:38:33.892Z",
        "id" "2000"
    } "2001" {
        "homeTeamId" "1002",
        "awayTeamId" "1001",
        "dateTime" "2014-10-11T22:38:33.892Z",
        "id" "2001"
    } "2002" {
        "homeTeamId" "1001",
        "awayTeamId" "1003",
        "dateTime" "2014-10-10T22:38:33.892Z",
        "id" "2002"
    } "2003" {
        "homeTeamId" "1003",
        "awayTeamId" "1004",
        "dateTime" "2014-10-09T22:38:33.892Z",
        "id" "2003"
    } "2004" {
        "homeTeamId" "1004",
        "awayTeamId" "1000",
        "dateTime" "2014-10-08T22:38:33.892Z",
        "id" "2004"
    } "2005" {
        "homeTeamId" "1000",
        "awayTeamId" "1003",
        "dateTime" "2013-10-12T22:38:33.892Z",
        "homeTeamScore" 21,
        "awayTeamScore" 0,
        "id" "2005"
    } "2006" {
        "homeTeamId" "1003",
        "awayTeamId" "1002",
        "dateTime" "2013-10-11T22:38:33.892Z",
        "homeTeamScore" 14,
        "awayTeamScore" 21,
        "id" "2006"
    } "2007" {
        "homeTeamId" "1002",
        "awayTeamId" "1004",
        "dateTime" "2013-10-10T22:38:33.892Z",
        "homeTeamScore" 7,
        "awayTeamScore" 0,
        "id" "2007"
    } "2008" {
        "homeTeamId" "1004",
        "awayTeamId" "1001",
        "dateTime" "2013-10-09T22:38:33.892Z",
        "homeTeamScore" 3,
        "awayTeamScore" 34,
        "id" "2008"
    } "2009" {
        "homeTeamId" "1001",
        "awayTeamId" "1000",
        "dateTime" "2013-10-08T22:38:33.892Z",
        "homeTeamScore" 52,
        "awayTeamScore" 47,
        "id" "2009"
    }}))

(defn get-games []
    (vals @games))

(defn get-game [id]
    (@games id))

(defn update-game [id attrs]
    (let [new-attrs (merge (get-game id) attrs)]
        (swap! games assoc id new-attrs)
        new-attrs))

(defn- find-games-by [k v] 
    (filter 
        #(= (get % k) v) 
        (get-games)))

(defn has-team? [game team]
    (or (= (get game "homeTeamId") team)
        (= (get game "awayTeamId") team)))

(defn- find-all-games-by-time [before-or-after?]
    (filter
        #(before-or-after? (cf/parse (cf/formatters :date-time) (get % "dateTime")) (local-now))
        (get-games)))

(defn find-games-with-team [team-id]
    (filter #(has-team? % team-id) (get-games)))

(defn find-all-past-games []
    (find-all-games-by-time ct/before?))

(defn find-all-future-games []
    (find-all-games-by-time ct/after?))

(defn find-all-past-games-with-team [team-id]
    (filter
        #(has-team? % team-id)
        (find-all-past-games)))

(defn find-all-future-games-with-team [team-id]
    (filter
        #(has-team? % team-id)
        (find-all-future-games)))

(def locations (atom 
    { "3000"
        {"id" "3000"
         "name" "M&T Bank Stadium", 
        "street" "Russell St.",
        "city" "Baltimore",
        "zipCode" "21230",
        "poc" "andy@anglelax.com",
        "homeTeamId" "1000" }
     "3001" 
        {"id" "3001"
         "name" "Fedex Field",
         "street" "Fedex Way",
         "city" "Landover",
         "zipCode" "20785",
         "poc" "matt@anglelax.com",
         "homeTeamId" "1001" }
     "3002"
        {"id" "3002"
         "name" "Heinz Field",
        "street" "Art Rooney Ave.",
        "city" "Pittsburgh",
        "zipCode" "15212",
        "poc" "ben@anglelax.com",
        "homeTeamId" "1003"}
     "3003" 
        {"id" "3003"
         "name" "MetLife Stadium",
        "street" "MetLife Stadium Dr.",
        "city" "East Rutherford",
        "zipCode" "07073",
        "poc" "eli@anglelax.com",
        "homeTeamId" "1004"}
     "3004" 
        {"id" "3004"
         "name" "Lincoln Financial Field",
        "street" "Lincoln Financial Field Way",
        "city" "Philadelphia",
        "zipCode" "19147",
        "poc" "michael@anglelax.com",
        "homeTeamId" "1002"}}))

(defn get-locations []
    (vals @locations))

(defn get-location [id]
    (@locations id))

(defn update-location [id attrs]
    (let [new-attrs (merge (get-location id) attrs)]
        (swap! locations assoc id new-attrs)
        new-attrs))

(defn- find-loc-by [k v] 
    (filter 
        #(= (get % k) v) 
        (get-locations)))

(defn find-location-by-home-team [team-id]
    (first
        (find-loc-by "homeTeamId" team-id)))

