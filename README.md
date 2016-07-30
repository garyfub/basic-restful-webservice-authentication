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