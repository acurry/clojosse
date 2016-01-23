(ns anglelax.users
    (:use 
        (korma core db)
        [clojure.set :only [rename-keys]])
    (:require 
        [clojurewerkz.scrypt.core :as sc]
        [anglelax.teams :refer :all]
        [anglelax.players :refer :all]
        [anglelax.db :refer :all]))

(defn update-contact [attrs]
    (let [id (:Player_ID attrs)
          attrs (dissoc attrs :Player_ID)]
        (update Contact
            (set-fields attrs)
            (where {:Contact_ID id}))))

(defn insert-contact [attrs]
    (:GENERATED_KEY
        (insert Contact
            (values attrs))))

(defn update-player-contact [attrs]
    (let [player-attrs (select-keys attrs [:Player_ID :Position :DOB])
          contact-attrs (select-keys attrs [:Player_ID :Last_name :First_name :Email :Phone])]
        (do
            (transaction
                (update-player player-attrs)
                (update-contact contact-attrs))
            (get-player (:Player_ID attrs)))))

(defn insert-player-contact [attrs]
    (let [player-attrs (select-keys attrs [:Position :DOB :Team])
          contact-attrs (assoc (select-keys attrs [:Last_name :First_name :Email :Phone]) :Role 3)
          generated-id (insert-contact contact-attrs)]
          (insert-player (assoc player-attrs :Player_ID generated-id))))

(defn get-coaches []
    (select Contact
        (with Role)
        (where {:Role.Type "coach"})))

(defn get-coach-by-email [email]
    (first
        (select Contact
            (with Role)
            (where {:Role.Type "coach"
                    :Email email}))))

(defn update-coach-by-email [attrs]
    (let [id (:Contact_ID attrs)
          attrs (select-keys attrs [:First_name :Last_name :Email :Phone])]
        (update Contact
            (set-fields attrs)
            (where {:Contact_ID id}))))

(defn authenticated? [email password]
    (if-let [user (get-coach-by-email email)]
        (if (= password (:Password user))
            email
        false)))

(defn authorized-for-player? [email player-id]
    (let [coach (get-coach-by-email email)
          coachs-team-id (:Team_ID (get-team-by-coach (:Contact_ID coach)))
          players-team-id (:Team (get-player player-id))]
        (= coachs-team-id players-team-id)))

(defn authorized-for-team? [email team-id]
    (if-let [coach (get-coach-by-email email)]
        (if-let [coachs-team-id (:Team_ID (get-team-by-coach (:Contact_ID coach)))]
            (= team-id coachs-team-id)
            false)
        false))

(defn get-players-by-coach [coach-email]
    (let [coach (get-coach-by-email coach-email)
          team (get-team-by-coach (:Contact_ID coach))
          players (get-players-on-team (:Team_ID team))]
          players))
