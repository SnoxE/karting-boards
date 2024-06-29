create table driver
(
    id         VARCHAR(100) NOT NULL primary key,
    first_name varchar(30)  NOT NULL,
    last_name  varchar(30),
    nickname   varchar(30),
    sex        varchar(1)   NOT NULL,
    email      varchar(50)  NOT NULL,
    password   text         NOT NULL,
    role       varchar(20)  NOT NULL
);

create table track
(
    id            VARCHAR(100) NOT NULL primary KEY,
    name          varchar(30)  NOT NULL,
    street        varchar(30)  NOT NULL,
    street_no     varchar(5),
    city          varchar(30)  NOT NULL,
    post_code     varchar(30)  NOT NULL,
    configuration integer,
    length        integer
);

CREATE TABLE session
(
    id       VARCHAR(100) NOT NULL primary key,
    track_id VARCHAR(100) NOT NULL,
    date     DATE         NOT NULL,
    time     TIME         NOT NULL,
    FOREIGN KEY (track_id) REFERENCES track (id)
);

CREATE TABLE laptime
(
    id         varchar(100) NOT NULL primary key,
    track_id   VARCHAR(100) NOT NULL,
    session_id VARCHAR(100) NOT NULL,
    driver_id  VARCHAR(100) NOT NULL,
    time       INTERVAL     NOT NULL,
    FOREIGN KEY (track_id) REFERENCES track (id),
    FOREIGN KEY (session_id) REFERENCES session (id),
    FOREIGN KEY (driver_id) REFERENCES driver (id)
);

insert into track (ID, name, street, street_no, city, post_code, configuration, length)
values ('wrt.karting', 'WRT Karting', 'Kornela Makuszyńskiego', '30', '31-752', 'Kraków', 1, 300);
insert into track (ID, name, street, street_no, city, post_code)
values ('karting.arena', 'Karting Arena', 'Legnicka', '5', '31-216', 'Kraków');

insert into session (id, track_id, date, time)
values ('wrt.karting.13-06-2024', 'wrt.karting', '2024-06-13', '15:00:00');

insert into laptime (id, track_id, session_id, driver_id, time)
values ('wrt.karting.13-06-2024.15_20_00.snoxe.id', 'wrt.karting', 'wrt.karting.13-06-2024',
        'snoxe.id', '00:26.443')

