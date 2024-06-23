create table driver(
                       id integer NOT NULL primary key generated always as identity,
                       first_name varchar(30) NOT NULL,
                       last_name varchar(30),
                       nickname varchar(30),
                       sex varchar(1) NOT NULL,
                       email varchar(50) NOT NULL,
                       password text NOT NULL,
                       role varchar(20) NOT NULL
);

create table track(
                       id integer NOT NULL primary key generated always as identity,
                       name varchar(30) NOT NULL,
                       address varchar(30) NOT NULL,
                       length integer NOT NULL
);

CREATE TABLE session (
                         id integer NOT NULL primary key generated always as identity,
                         track_id INT NOT NULL,
                         date DATE NOT NULL,
                         time TIME NOT NULL,
                         FOREIGN KEY (track_id) REFERENCES track(id)
);

CREATE TABLE laptime (
                         id integer NOT NULL primary key generated always as identity,
                         track_id INT NOT NULL,
                         session_id INT NOT NULL,
                         driver_id INT NOT NULL,
                         time INTERVAL NOT NULL,
                         FOREIGN KEY (track_id) REFERENCES track(id),
                         FOREIGN KEY (session_id) REFERENCES session(id),
                         FOREIGN KEY (driver_id) REFERENCES driver(id)
);

insert into track(name, address, length) values ('WRT Karting', 'Kornela Makuszyńskiego 30, 31-752 Kraków', 300)