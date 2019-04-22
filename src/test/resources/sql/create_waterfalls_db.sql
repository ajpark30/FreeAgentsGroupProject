-- ---------------------------------------------------------
-- This data was created in the Intellij database view by
-- right clicking "waterfalls" and selecting "SQL Generator"
-- ---------------------------------------------------------

DROP TABLE photo, waterfall, zip_coords;


create table waterfall
(
    waterfall_id  int auto_increment
        primary key,
    name          varchar(100) null,
    description   varchar(300) null,
    country       varchar(100) null,
    state         varchar(100) null,
    region        varchar(100) null,
    city          varchar(100) null,
    location_info varchar(100) null,
    latitude      float        not null,
    longitude     float        not null,
    preserve      varchar(100) null,
    river         varchar(100) null,
    url           varchar(333) null
);

create table photo
(
    photo_id      int auto_increment
        primary key,
    waterfall_id  int           null,
    dateAcquired  datetime      null,
    sourceURL     varchar(300)  null,
    linkedFromURL varchar(300)  null,
    attribution   varchar(1000) null,
    dateTaken     datetime      null,
    title         varchar(100)  null,
    caption       varchar(100)  null,
    description   varchar(100)  null,
    localPath     varchar(300)  null,
    height        int default 0 null,
    width         int default 0 null,
    constraint waterfall_photo_cx
        foreign key (waterfall_id) references waterfall (waterfall_id)
            on update cascade on delete cascade
);

create index watlatdex
    on waterfall (latitude);

create index watlondex
    on waterfall (longitude);

create index watnamedex
    on waterfall (name);

create table zip_coords
(
    zipcode   char(5) null,
    latitude  double  null,
    longitude double  null,
    constraint zipdex
        unique (zipcode)
);

create index ziplatdex
    on zip_coords (latitude);

create index ziplondex
    on zip_coords (longitude);

