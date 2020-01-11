# Intro
Sample app to demo and play with some ci/cd stuff. This is a Java app, but almost no Java skills
will be required.

The application reads a row from a table in MySQL and outputs it into a Json message.

You can use the [docker-compose file](docker-compose.yml) I have provided to startup:
 - haproxy
 - Two application instances
 - MySQL

The application uses [Flyway DB](http://flywaydb.org) to execute SQL migrations. 
Spring Boot will make sure they get executed _at application startup_ if you put them in [the right location](src/main/resources/db/migration).

Also, have a look at the [codecov.io](.codecov.yml) setup if you're interested.,

# What you will learn

- Some docker quirks
- The limits of docker-compose
- How to execute a rolling update manually. Eg: you will know what to automate and what it means for programmers
  to work on software that is deployed continuously.

# Cheatsheet

To build docker image : `./mvnw compile jib:build`

To run app quickly for dev-purposes: `./mvnw spring-boot:run`

To start everything: 
```bash

docker-compose up -d 

````

See all processes: `docker ps`

Watch loadbalancer status: http://0.0.0.0:1936

Go to app frontend: [http://localhost]

Node health: http://localhost/actuator/health

Info: http://localhost/actuator/info

Connect to the mysql console: `mysql -u test -p -h 127.0.0.1 --port 13333 lab`. Password: `test`

# Before you begin

Execute the following in this dir:

```bash
$ cp docker-compose-starter.yml docker-compose.yml 
$ git checkout f814344
$ ./build.sh
$ docker-compose up -d
```

Now you have all of the elements running.

# The exercise

As you can see, there is a big spelling error in the table names. Such things
have a habit of proliferating throughout code-bases so they must be fixed.

Our goal:

>  Rename the `lastt_name` column to `last_name` while maintaining 100% uptime and not disturbing our imaginary users.

So, you will have to come up with the steps and execute the commands while users see the same
output. **Before you rush to the keyboard**, go through the steps below: 

 1. **On paper, draw a diagram of this deployment-setup**
 2. **What is the role of haproxy during an upgrade?**
 3. **How does haproxy know when not to send traffic to a node?**
 4. **We can not update all app-nodes at the same time. Knowing that, write down the answer to this question: What will go wrong if we just include a flyway migration and execute ALTER TABLE and upgrade nodes one by one?**
 5. **You don't have to use the haproxy admin-api in this exercise. What is the inevitable result of not disabling nodes?**
 6. Ok, now you can start coding :)
 7. **Our app does not perform any writes. What if we also had to account for writes to our `lastt_name` column? How would that change our approach?**

So, essentially achieve this (with zero downtime):

 ```sql
ALTER TABLE person CHANGE COLUMN lastt_name last_name varchar(255);
``` 

**Hint**: you will need to deploy not one, but multiple versions of the software until all traces of the typo have been removed from the code-base. Make sure you commit each step to git. 

There will be a little bit of code you need to change. You can find it in [HomeController.java](src/main/java/cidemo/HomeController.java)

Here's an [article for inspiration](https://thoughts-on-java.org/update-database-schema-without-downtime/)

