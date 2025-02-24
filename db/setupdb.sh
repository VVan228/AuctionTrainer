#!/bin/bash
set -e
mysql.server start
mysql < ./auction_demo_scheduler.sql
mysql.server stop
