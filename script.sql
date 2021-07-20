create table client
(
    cid                 int auto_increment,
    firstname           varchar(30)          not null,
    lastname            varchar(30)          not null,
    phone_number        varchar(10)          null,
    email               varchar(30)          null,
    quote_date          date                 null,
    date_sold           date                 null,
    latest_contact_date date                 null,
    expiry_date         date                 null,
    quote_status        char(20)             null,
    number_of_policy    int                  null,
    commission_amount   int                  null,
    has_review          tinyint(1) default 0 null,
    referral            varchar(50)          null,
    constraint client_cid_uindex
        unique (cid)
);

alter table client
    add primary key (cid);

create table carrier
(
    id   int auto_increment
        primary key,
    type varchar(20) not null,
    cid  int         not null,
    constraint cid
        foreign key (cid) references client (cid)
            on delete cascade
);

create table staff
(
    sid      int auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    constraint staff_sid_uindex
        unique (sid),
    constraint staff_username_uindex
        unique (username)
);

alter table staff
    add primary key (sid);

