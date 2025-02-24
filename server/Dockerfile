FROM maven:3.8.5-openjdk-18

COPY . /

RUN mvn clean install

ARG DATASOURCE_URL=/
ARG DATASOURCE_PASSWORD=
ARG QRTZ_DATASOURCE_URL=$DATASOURCE_URL
ARG QRTZ_DATASOURCE_PASSWORD=$DATASOURCE_PASSWORD

RUN echo java -jar ./target/auc-0.0.1-SNAPSHOT.jar --spring.datasource.url=$DATASOURCE_URL --org.quartz.dataSource.quartzDataSource.URL=$QRTZ_DATASOURCE_URL --spring.datasource.password=$DATASOURCE_PASSWORD --org.quartz.dataSource.quartzDataSource.password=$QRTZ_DATASOURCE_PASSWORD > /startup.sh

RUN chmod +x /startup.sh

ENTRYPOINT /startup.sh
