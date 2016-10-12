# **Spring Rest**

## **Prerequisites**

* Java 8
* MySQL 5.7.15

## **Configuration**

### **Properties**

The properties and its default values are the following: 

```
org.spring.rest.api.example.datasource.host=127.0.0.1
org.spring.rest.api.example.datasource.port=3306
org.spring.rest.api.example.datasource.username=root
org.spring.rest.api.example.datasource.password=P@ssW0rd
org.spring.rest.api.example.fileUpload.maxFileSize:128KB
org.spring.rest.api.example.fileUpload.maxRequestSize:128KB
```

**Note.** The above properties can be overwritten. To overwrite follow the next steps:

1. Create the folder structure 
```sh
$ mkdir -p /opt/apps/spring-rest-api-example
```

2. Create the file *conf-override.properties*
```sh
$ cd /opt/apps/spring-rest-api-example
$ sudo chmod -R 777 /opt/apps/
$ touch conf-override.properties
```

3. Finally, add the properties that you want overwrite in the file created before. **Note**, if you change the password of the database, you also need to do it in the file *build.gradle* in the block *flyway*

## **Execution**

* First execute the script migration to create the database and tables. To do this, I used **[Flyway](https://flywaydb.org/)** 
```sh
$ gradle flywayMigrate -i
```

* Run the application
```sh
$ gradle bootRun
```

* Finally, [test it!](http://127.0.0.1:8080)

