-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files

create database brewery;
use brewery;

source beers.sql
source breweries.sql
source categories.sql
source geocodes.sql
source styles.sql

-- When adding this database to Railway, comment this off as we are using Railway root user
grant all privileges on brewery.* to 'abcde'@'%';
flush privileges;