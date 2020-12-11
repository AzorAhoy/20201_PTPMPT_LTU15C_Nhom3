FROM openjdk:8
COPY . ./src
WORKDIR /src
RUN javac client.java
CMD ["java", "client"]