(ns anglelax.positions
    (:require 
        [anglelax.db :refer :all]
        [korma.core :refer :all]))

(defn get-positions []
    (select Position))
