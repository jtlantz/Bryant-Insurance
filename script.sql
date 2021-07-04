create table Client
(
    CID                 int auto_increment,
    Name                varchar(50)          not null,
    Phone_Number        varchar(10)          null,
    Email               varchar(30)          null,
    Date_Sold           date                 null,
    Quote_Date          date                 null,
    Expiry_Date         date                 null,
    Latest_Contact_Date date                 null,
    Quote_Status        char(20)             null,
    Number_of_policy    int                  null,
    Commission_Amount   int                  null,
    Has_Review          tinyint(1) default 0 null,
    Referral            varchar(50)          null,
    constraint Client_CID_uindex
        unique (CID)
);

alter table Client
    add primary key (CID);

create table Carrier
(
    Type varchar(20) not null,
    CID  int         not null,
    constraint CID
        foreign key (CID) references Client (CID)
            on delete cascade
);

create table Staff
(
    SID           int auto_increment,
    Username      varchar(255)         not null,
    Password      varchar(255)         not null,
    Is_Authorized tinyint(1) default 0 null,
    constraint Staff_SID_uindex
        unique (SID),
    constraint Staff_Username_uindex
        unique (Username)
);

alter table Staff
    add primary key (SID);


