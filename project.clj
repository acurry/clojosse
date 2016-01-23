(defproject anglelax "0.1.0-SNAPSHOT"
  :description "Lacross league manager app"
  :url "http://google.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [org.clojure/data.json "0.2.3"]
                 [ring/ring-json "0.2.0"]
                 [clj-time "0.6.0"]
                 [de.ubercode.clostache/clostache "1.3.1"]
                 [clojurewerkz/scrypt "1.0.0"]
                 [ring-basic-authentication "1.0.2"]
                 [korma "0.3.0-RC5"]
                 [mysql/mysql-connector-java "5.1.25"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler anglelax.routes/app
         :resources-path "resources/public"
         :init anglelax.routes/init}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
