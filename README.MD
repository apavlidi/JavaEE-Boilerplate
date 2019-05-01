# JavaEE-Boilerplate

An enterprise web application based on JavaEE. The project supports JSF, EJB, JPA, CDI, Hibernate, Deltaspike, JAX-RS, Java Batch Processing (JSR 352).

### Intro
The project is an enterprise web application which uses the Admin Faces (https://adminfaces.github.io/site/) in order to provide a responsive and nice template. Its also based on the MVC pattern.

### How to
First and foremost you need an application server with a data-resource configured properly, and a mysql database.
Your application server must support mysql datasources and you need to configure it with a mysql connectorJ (https://dev.mysql.com/downloads/connector/j/)
To enable debugger you have also to configure your application server by tweaking its settings, the default port is 8787 in Wildfly.

> **Note:** It is recommended to use Wildfly Version: `10.1.0.Final` and MySQL Version : `5.7.25` in order to avoid many changes on the codebase!

### Hint
The application is currently in development and its not yet fully implemented.

### Deploy the application:
`mvn clean install wildfly:deploy`

### Run Arquillian IT against Wildfly AS
The project supports running arquillian in a managed container for Widlfly AS.
`mvn clean install -Pwildfly-managed`
