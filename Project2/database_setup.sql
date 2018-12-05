create database if not exists online_ordering_system;

use online_ordering_system;

create table category (name varchar(20), description 
varchar(250), foreign key category(name) references
category(name) on update cascade on delete restrict);

create table product (skuCode long not null, 
itemCount int, 
threshold int not null, reorderAmount int, 
title varchar(20) not null, 
description varchar(250) not null, 
cost long not null, 
category varchar(20))
engine=online_ordering_system;