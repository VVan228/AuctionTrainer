#!/bin/bash
set -e
mysqld start
mysql < ./auction_demo_scheduler.sql
mysqladmin -u root shutdown
