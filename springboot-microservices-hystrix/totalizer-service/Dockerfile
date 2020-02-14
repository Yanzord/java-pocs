FROM openjdk:8
WORKDIR /
COPY /build/libs/*.war totalizer-service.war
EXPOSE 8080
CMD ["java", "-jar", "totalizer-service.war"]