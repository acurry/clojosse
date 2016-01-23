(ns anglelax.teams
    (:use [korma.core])
    (:require [anglelax.db :refer :all]))

(def ^:private base
    (->
        (select* Team)))

(defn get-teams []
    (-> base
        (select)))

(defn get-team-by-id [id]
    (first 
        (-> base
            (where (= :Team_ID id))
            (select))))

(defn get-team-by-coach [coach-id]
    (first
        (->
            base
            (where (= :Coach coach-id))
            (select))))

(defn update-team [attrs]
    (let [team (select-keys attrs [:Team_name])
          id (:Team_ID attrs)]
        (update Team
            (set-fields team)
            (where {:Team_ID id}))))
