KitAlumniApp-Server
===================

Developer Instructions
----------------------

### Getting Started
1. Checkout the source
2. Import a Maven Project into Eclipse

### Configuration
1. Put your API key for Google Cloud Messaging (GCM) in `src\main\resources\api.key`
2. Change database credentials in `src\main\java\META-INF\persistence.xml`

### Build Instructions

#### Build and test
Start the server with an embedded tomcat instance using
```
mvn tomcat7:run-war -Dmaven.test.skip=true
```

Keep the embedded tomcat process running. We need it for integration tests.

To run unit and integration tests via Maven, issue this command 
```
mvn test
```

#### Build without test
To build the server's .war file, run
```
mvn package -Dmaven.test.skip=true
```