FROM maven:3.8.5-openjdk-18

COPY . /

RUN mvn clean install

RUN java -jar ./target/auc-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:mysql://localhost:3306/auction_demo
