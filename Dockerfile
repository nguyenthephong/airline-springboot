# the first stage of our build will use a maven 3.8.1 parent image
FROM maven:3.8.1-adoptopenjdk-11 AS MAVEN_BUILD

# copy only the artifacts we need from the first stage and discard the rest
COPY target/airline-0.0.1-SNAPSHOT.jar /

# set the startup command to execute the jar
CMD ["java", "-jar", "/airline-0.0.1-SNAPSHOT.jar"]
