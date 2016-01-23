(ns anglelax.db
	(:require
		[clj-time.core :as ct :only [hour minute second day month year date-time]]
		[clj-time.format :refer :all]
		[clj-time.coerce :refer :all]
		[korma.db :refer :all]
		[korma.core :refer :all]))

(defn ppr [& body] (clojure.pprint/pprint body))

(defn date-to-iso-str [dt]
	(unparse (formatters :date-time) dt))

(defn dob-to-ymd [dt]
	(unparse (formatters :date) dt))

(defn ymd-to-dob [dt]
	(parse (formatters :date) dt))

(defn make-dt [d t]
	(let [dd (from-sql-date d)
		  tt (from-sql-date t)
		  ho (ct/hour tt)
		  mi (ct/minute tt)
		  se (ct/second tt)
		  da (ct/day dd)
		  mo (ct/month dd)
		  ye (ct/year dd)
		  dt (ct/date-time ye mo da ho mi se)]
		  (date-to-iso-str dt)))

(defn prod []
	(defdb anglelax-prod
		(mysql 
			{:host "web3.apl.jhu.edu"
		 	 :port 3306
		 	 :db "project_db_fall13"
		 	 :user "projuserfall13"
		 	 :password "dbfall13"})))

(declare Team Contact Role Player Position Game Location)

(defentity Team
	(pk :Team_ID)
	(entity-fields :Team_ID :Team_name :Coach)
	(belongs-to Contact {:fk :Coach}))

(defentity Contact
	(pk :Contact_ID)
	(entity-fields :Contact_ID :Last_name :First_name :Email :Phone :Password :Role)
	(belongs-to Role {:fk :Role}))

(defentity Role
	(pk :Role_ID)
	(entity-fields :Role_ID :Type))

(defentity Player
	(pk :Player_ID)
	(entity-fields :Player_ID :DOB :Team :Position)
	(belongs-to Position {:fk :Position})
	(belongs-to Contact {:fk :Player_ID})
	(transform (fn [{dob :DOB :as v}]
		(if dob
			(assoc v :DOB (dob-to-ymd (from-sql-date dob))) v))))

(defentity Position
	(pk :Position_ID)
	(entity-fields :Position_ID :Type))

(defentity Game
	(pk :Game_ID)
	(entity-fields :Game_ID :Date :Time :Home_team :Away_team :Location :Home_score :Away_score)
	(belongs-to Team {:fk :Home_team})
	(belongs-to Team {:fk :Away_team})
	(belongs-to Location {:fk :Location}))

(defentity Location
	(pk :Location_ID)
	(entity-fields :Location_ID :Field_name :Street :City :Zip_code :Contact)
	(belongs-to Contact {:fk :Contact}))
