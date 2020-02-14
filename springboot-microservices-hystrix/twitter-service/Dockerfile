FROM openjdk:8

WORKDIR /

ARG CONSUMER_KEY
ARG CONSUMER_SECRET
ARG ACCESS_TOKEN
ARG ACCESS_TOKEN_SECRET

ENV CONSUMER_KEY=${CONSUMER_KEY}
ENV CONSUMER_SECRET=${CONSUMER_SECRET}
ENV ACCESS_TOKEN=${ACCESS_TOKEN}
ENV ACCESS_TOKEN_SECRET=${ACCESS_TOKEN_SECRET}

COPY /build/libs/*.war twitter-service.war

EXPOSE 8080

CMD ["java", "-jar", "twitter-service.war", "--twitter4j.consumerKey=${CONSUMER_KEY}", "--twitter4j.consumerSecret=${CONSUMER_SECRET}", "--twitter4j.accessToken=${ACCESS_TOKEN}", "--twitter4j.accessTokenSecret=${ACCESS_TOKEN_SECRET}"]