# basic-restful-webservice
Template for REST webservice with spring

## Tecnologies

* Java 8
* Spring Boot
* Maven 3
  * Release plugin
  * FindBugs plugin
* JUnit

## Builds

To generate local builds with maven:
```bash
 mvn clean install
``` 
To generate versioned builds for production and homologation enviroments:
```
  mvn release:clean
  mvn release:prepare
```

## Deploys

After building project, get .war file and run on your current web server:

* Tomcat
```bash
	./startup.sh
```

## Oauth Token Request

Call this curl to get your access token:

```bash
	curl -X POST clientIdPassword:secret@localhost:8080//basic-oauth/oauth/token -d grant_type=password -d username=john -d password=123 -d client_id=clientIdPassword
```

## Oauth Method Request

Call the curl with token like this to make use of the methods:

```bash
	curl -H "Authorization: Bearer $TOKEN" localhost:8080/basic-oauth/
```