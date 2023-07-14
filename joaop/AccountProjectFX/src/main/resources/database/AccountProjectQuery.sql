create database AccountProject;

use AccountProject;

create table Account(
                     idAcc       INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
					 nameAcc     VARCHAR(500) NOT NULL,
					 emailAcc    VARCHAR(500) NOT NULL,
					 passwordAcc VARCHAR(500) NOT NULL,
					 imageAcc    VARBINARY(8000) NULL
					 );