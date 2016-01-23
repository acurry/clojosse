(ns anglelax.games
    (:use 
        [clj-time.local :only [local-now]]
        (korma core db))
    (:require [clj-time.core :as ct]
              [clj-time.format :as cf]
              [anglelax.db :refer :all]
              [anglelax.teams :refer :all]))

(defn- prune [game]
    (dissoc
            (assoc
                game
                :DateTime
                (make-dt (:Date game) (:Time game)))
            :Date :Time))

(defn get-games []
    (map
        prune
        (select Game
            (with Team)
            (with Location))))

(defn get-game-by-id [id]
    (let [game (first (select Game
            (with Team)
            (with Location)
            (where (= :Game_ID id))))]
        (prune game)))
