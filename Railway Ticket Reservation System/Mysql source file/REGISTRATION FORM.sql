
CREATE TABLE T_USERDATA(
	USER_ID int NOT NULL auto_increment,
	NAME VARCHAR(30) not null,
    EMAIL VARCHAR(25) not null,
    PHONE_NUMBER VARCHAR(12) not null,
    PASSWORD BLOB DEFAULT NULL,
    GENDER VARCHAR(10) not null,
    COUNTRY VARCHAR(15) not null,
    STATE VARCHAR(20) not null,
    constraint PK_USER_ID primary key(USER_ID)
)auto_increment = 1000;

