(ns anglelax.players
    (:use 
        (korma core db)
        [clojure.set :only [rename-keys]])
    (:require 
        [anglelax.db :refer :all]))

(defn prune-player [player]
    (dissoc
        player
        :DOB :Email :Phone))

(defn update-player [attrs]
    (let [id (:Player_ID attrs)
          attrs (dissoc attrs :Player_ID)]
        (update Player
            (set-fields attrs)
            (where {:Player_ID id}))))

(defn insert-player [attrs]
    (insert Player
        (values attrs)))

(defn get-players []
    (select Player
        (with Contact)
        (with Position)))

(defn get-public-players []
    (map prune-player (get-players)))

(defn get-players-on-team [team-id]
    (select Player
        (with Contact)
        (with Position)
        (where {:Team team-id})))

(defn get-public-players-on-team [team-id]
    (map prune-player (get-players-on-team team-id)))

(defn get-player [id]
    (first
        (select Player
            (with Contact)
            (with Position)
            (where (= :Player_ID id)))))

(defn get-by-email [email]
    (first
        (select Contact
            (where 
                {:Email email}))))

(defn id-and-email-linked? [id email]
    (let [player (get-by-email email)]
        (or
            (nil? player)
            (= id (:Contact_ID player)))))

(defn get-public-player [id]
    (prune-player (get-player id)))
