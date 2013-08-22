drop database if exists winecritic;
create database winecritic character set utf8;
use winecritic;

grant all on winecritic.* to 'liquibase'@'localhost' identified by 'liquibase';
grant select,update,insert,delete on winecritic.* to 'dev'@'localhost' identified by 'dev';
