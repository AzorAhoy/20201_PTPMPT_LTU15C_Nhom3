FROM openjdk:8
COPY . ./src
WORKDIR /src
RUN javac server.java
CMD ["java", "server"]