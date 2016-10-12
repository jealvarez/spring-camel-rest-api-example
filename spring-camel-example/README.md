# **Spring Camel**

## **Prerequisites**

* Java 8
* MySQL 5.7.15
* Run spring-rest-api-example

## **Configuration**

### **Properties**

The properties and its default values are the following: 

```
org.spring.camel.example.directory.path.in:/opt/apps/spring-camel-example/input
org.spring.camel.example.output.rest.endpoint:http4://127.0.0.1:8080/authority-group/upload-file
```

**Note.** The above properties can be overwritten. To overwrite follow the next steps:

1. Create the folder structure 
```sh
$ mkdir -p /opt/apps/spring-camel-example
```

2. Create the file *conf-override.properties*
```sh
$ cd /opt/apps/spring-camel-example
$ sudo chmod -R 777 /opt/apps/
$ touch conf-override.properties
```

3. Finally, add the properties that you want overwrite in the file created before. **Note**, if you change the password of the database, you also need to do it in the file *build.gradle* in the block *flyway*

## **Execution**

* Run the application
```sh
$ gradle bootRun
```

## **Test**

1. It's neccesary run **spring-rest-api-example** before to run this one
2. Take a file from the **example-files** directory and put it in the input folder configured before. **Note.** The files that going to be processed needs to have the extension **.done**.
  * If everything was processed right then you should see a folder called **.processed** in the same directory with the next structure
  ```
    |- input folder
      |- .processed
        |- yyyyMMdd
          |- name of the file processed
  ```  
  * If something comes up then you should see a folder called **.failed** in the same directory with the next structure
  ```
    |- input folder
      |- .failed
        |- yyyyMMdd
          |- name of the file failed
  ```
3. If you want to see the routes through web interface, [test it here!](http://127.0.0.1:9090)
