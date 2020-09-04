FROM openjdk:8-slim

COPY graphql.jar graphql.jar

CMD java -jar -Dspring.profiles.active=${PROFILE} graphql.jar
