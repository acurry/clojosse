(ns anglelax.routes
  (:use [compojure.core]
        [ring.middleware.json :only [wrap-json-params wrap-json-body]]
        [ring.middleware.basic-authentication :only [wrap-basic-authentication]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.java.io :as io :only [resource]]
            [clostache.parser :as clostache]
            [clojure.data.json :as json]
            [anglelax [teams :as teams]
                      [players :as players]
                      [locations :as locations]
                      [games :as games]
                      [users :as users]
                      [db :as db]
                      [positions :as pos]]))

(defn init []
  (when true
    (db/prod)))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/write-str data)})

(defn read-template [template-name]
  (slurp (io/resource
    (str "public/" template-name ".clostache"))))

(defn render-template [template-file params]
  (clostache/render 
    (read-template template-file) 
    params))

(defn wrap-log-req [app]
  (fn [req]
    (do
      (when (= (:request-method req) :put)
        (clojure.pprint/pprint req))
      (app req))))

(defn without-auth [app]
  (fn [req]
    (app req)))

(defroutes team-routes
  (GET "/" []
    (json-response (teams/get-teams)))
  (GET "/:id" [id]
    (json-response (teams/get-team-by-id id))))

(defroutes player-routes 
  (GET "/getPublicData" []
    (json-response (players/get-public-players)))
  (GET "/onPublicTeam/:team" [team]
    (json-response (players/get-public-players-on-team team)))
  (GET "/:id" [id] 
    (json-response (players/get-public-player id))))

(defroutes location-routes
  (GET "/" [] 
    (json-response (locations/get-locations)))
  (GET "/:id" [id] 
    (json-response (locations/get-location-by-id id))))

(defroutes game-routes
  (GET "/" [] 
    (json-response (games/get-games)))
  (GET "/:id" [id] 
    (json-response (games/get-game-by-id id))))

(defroutes protected-routes
  (GET "/players" req
    (json-response (users/get-players-by-coach (:basic-authentication req))))

  (GET "/myInfo" req
    (json-response (users/get-coach-by-email (:basic-authentication req))))

  (POST "/myInfo" {body :body}
    (if (players/id-and-email-linked? (:Contact_ID body) (:Email body))
      (json-response (users/update-coach-by-email body))
      (json-response {:error "Email already exists."} 409)))

  (GET "/players/playerOnTeam/:id" [id]
    (json-response (players/get-player id)))

  (PUT "/players/:id" {body :body}
    (if (players/id-and-email-linked? (:Player_ID body) (:Email body))
      (json-response (users/update-player-contact body))
      (json-response {:error "Email already exists for a different player."} 409)))

  (POST "/players" {body :body}
    (if (players/get-by-email (:Email body))
      (json-response {:error "Email already exists."} 409)
      (json-response (users/insert-player-contact body))))

  (PUT "/myTeam/:id" {body :body}
    (json-response (teams/update-team body))))

(defroutes public-routes
  (GET "/" req
    (render-template 
      "index"
      {:teams
        (json/read-str
          (json/write-str (teams/get-teams))
          :key-fn keyword)
       :context
        (req :context)}))

  (context "/teams" [] team-routes)
  (context "/players" [] player-routes)
  (context "/locations" [] location-routes)
  (context "/games" [] game-routes)
  (GET "/positions" [] 
    (json-response (pos/get-positions)))
  (route/resources "/"))

(defroutes app-routes
  (without-auth public-routes)
  (wrap-basic-authentication 
    protected-routes
    users/authenticated?
    "AngleLax - For Coaches"
    {:status 401
      :body "Access denied - You are not authorized to perform this action."})
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-json-body {:keywords? true})
    handler/site))
