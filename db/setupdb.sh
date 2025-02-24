#!/bin/bash
set -e
# mysql.server start
mysqld
mysql < ./auction_demo_scheduler.sql
mysqladmin -u root shutdown
