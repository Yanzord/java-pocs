FROM openjdk:8
WORKDIR /
COPY /build/libs/*.war github-service.war
EXPOSE 8080
CMD ["java", "-jar", "github-service.war"]