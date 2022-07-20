drop table Flat;

create table Flat
(
    id  int auto_increment primary key,
    district_of_city varchar(100)  not null,
    adress         varchar(100)  unique not null,
    area_of_flat double not null ,
    count_of_rooms   int     not null,
    price          int          not null,
    used  boolean DEFAULT (false) not null
);


insert into Flat(district_of_city, adress, area_of_flat, count_of_rooms, price) VALUES ('Distr_1', 'Bayractar str 12 app 20', 54.5,  3, 42000);
insert into Flat(district_of_city, adress, area_of_flat, count_of_rooms, price) VALUES ('Distr_2', 'Himars str 21 app 15', 43.8,  2, 33800);
insert into Flat(district_of_city, adress, area_of_flat, count_of_rooms, price) VALUES ('Distr_3', 'Jewelin str 35 app 4', 33.2,  1, 28700);
insert into Flat(district_of_city, adress, area_of_flat, count_of_rooms, price) VALUES ('Distr_4', 'Nlaw str 11 app 22', 64.7,  4, 49900);
insert into Flat(district_of_city, adress, area_of_flat, count_of_rooms, price) VALUES ('Distr_5', 'Stygna str 3 app 11', 42.1,  2, 31200);