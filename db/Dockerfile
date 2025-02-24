FROM mysql:8.0
COPY setupdb.sh /mysql/setup.sh
COPY setupdb.sql /mysql/setup.sql
RUN /setupdb.sh
