FROM maven:3.8.2-openjdk-16-slim
RUN mkdir job4j_forum
WORKDIR job4j_forum
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD ["java", "-jar", "target/forum-0.0.1.jar"]