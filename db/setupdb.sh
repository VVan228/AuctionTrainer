#!/bin/bash
set -e
# mysql.server start
mysqld --initialize
mysqld
mysql -u root auction_demo_scheduler < ./auction_demo_scheduler.sql
mysqladmin -u root shutdown
