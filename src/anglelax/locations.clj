(ns anglelax.locations
    (:use 
        (korma core db))
    (:require [anglelax.db :refer :all]))

(defn get-locations []
    (select Location
        (with Contact)))

(defn get-location-by-id [id]
    (first 
        (select Location
            (with Contact)
            (where (= :Location_ID id)))))
