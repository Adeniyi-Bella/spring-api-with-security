# install maven and name it build
FROM maven:3.8.6 AS build
# define a folder app
WORKDIR /app
# copy pom to app folder
COPY pom.xml /app
# install all dependency in pom file
RUN mvn dependency:resolve
# copy everything to app folder
COPY . /app
# remove old target folder
RUN mvn clean
# create a jar file and skip all test
RUN mvn package -DskipTests

# get a copy of java
FROM openjdk:17-jdk-alpine
# copy from build just the jar file and name it app.jar
COPY --from=build /app/target/*.jar app.jar
# expose to port 8080
EXPOSE 8080
# run the command java -jar app.jar
CMD ["java", "-jar", "app.jar"]