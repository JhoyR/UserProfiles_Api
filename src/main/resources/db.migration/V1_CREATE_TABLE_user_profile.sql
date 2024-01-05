create table userProfile(
    id BIGINT not null auto_increment,
    name varchar (255),
    email varchar (255),
    password varchar (255),
    status varchar (255),
    dateAdd date not null,
    primary key (id)
);