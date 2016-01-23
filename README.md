# anglelax

This is a Clojure web app with a RESTful backend and AngularJS front-end.

# FINAL PROJECT CODE DROP - due by 6 Dec 2013 2359hrs

## Build using Ant

There's a build.xml at the root of the project with two targets: `bundle` and `server`. These just leverage
the local `lein` script to run the corresponding Leiningen commands.

To build the WAR using ant:

	$ ant bundle

To run the app locally on port 3000:

	$ ant server

## Build using  Leiningen

Anglelax uses [Leiningen](http://leiningen.org/), a tool for managing Clojure applications. It's very similar to Apache Maven and in fact uses many of Maven's same commands and structure.

### Installing Leiningen

Leiningen is used on the command line with a shell script. I've included it in the root of this directory, simply named, `lein`.

1. Copy the file `lein` to a directory somewhere on your `PATH`, for example, `~/bin`.

    `cp lein.sh ~/bin/lein`

2. If not already on your `PATH`, append the directory to `PATH`.

	`export PATH=$PATH:~/bin`

3. Make `lein` executable.

	`chmod u+x ~/bin/lein`.

That's it! Now you can run or build the application.

## Running the app using lein

To start a web server for the application, run:

    lein ring server

Your browser should automatically open to http://localhost:3000.

## Build a Tomcat-deployable WAR

1. Run the command below to build a WAR:

	lein ring uberwar anglelax.war

2. Copy the war to the webapps directory in a Tomcat installation.
3. Go to http://tomcat-url/anglelax/ 

## Ensure there is a trailing slash at the end of the URL!

By default, Tomcat does not redirect a request to the root of a context (such as localhost:8080/anglelax) to another URL. Without the trailing slash, the app won't load correctly. There is a way to set up a redirect but it requires a change to Tomcat's configuration.

## If using Tomcat version prior to 7.0.35...

You'll see the following message in the Tomcat logs when deploying the anglelax app. The app still works correctly. This was a known bug in Tomcat 7 prior to build 7.0.35. In all versions of Tomcat following 7.0.35, the bug no longer occurs.

	2013-11-08 13:11:52,743 [main] ERROR org.apache.catalina.startup.ContextConfig- Unable to process JNDI URL [jndi:/localhost/anglelax/WEB-INF/classes/ring/util/servlet/proxy%24javax] for annotations
	java.io.FileNotFoundException: jndi:/localhost/anglelax/WEB-INF/classes/ring/util/servlet/proxy%24javax
	at org.apache.naming.resources.DirContextURLConnection.list(DirContextURLConnection.java:463)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJndi(ContextConfig.java:1901)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJndi(ContextConfig.java:1905)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJndi(ContextConfig.java:1905)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJndi(ContextConfig.java:1905)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsJndi(ContextConfig.java:1905)
	at org.apache.catalina.startup.ContextConfig.processAnnotationsUrl(ContextConfig.java:1828)
	at org.apache.catalina.startup.ContextConfig.webConfig(ContextConfig.java:1295)
        ....
