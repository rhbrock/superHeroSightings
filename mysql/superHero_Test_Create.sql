drop database if exists superhero_test;

create database superhero_test;

use superhero_test;


create table if not exists zipcitystate(
 zip int not null,
 city varChar(30) not null,
 stateId varChar(5) not null,
 primary key (zip)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

#Only for testing purposes with limited and known zip code values
insert into zipcitystate (zip, city, stateId) values ("10012", "New York", "NY"), ("11211", "Brooklyn", "NY"), ("30303", "Atlanta", "GA"), ("10560", "North Salem", "NY");


create table if not exists superhuman (
	superId int not null auto_increment,
    superHumanName varChar(50) not null,
    superHumanType enum("hero", "villain") not null,
    superPower varChar(30),
    superHumanDesc text,
    #superHumanImgLink varChar(200),
    #superHumanImg longblob,
    primary key (superId)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into superhuman (superHumanName, superHumanType, superPower, superHumanDesc) values
("Aquaman", "hero", "Breaths under water", "TestDesc");


create table if not exists superorg(
	orgId int not null auto_increment,
    orgName varChar(50) not null,
    orgDesc text,
    orgStreetAddress varChar(100),
    zip int,
    orgEmail varChar(50),
    orgPhone varChar(20),
    #orgImgLink varChar(200),
    #orgImg longblob,
    primary key (orgId),
    foreign key (zip) references zipcitystate (zip)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into superorg (orgName, orgDesc, orgStreetAddress, zip, orgEmail, orgPhone) values
("Test", "Test", "123", 10012, "a", "111-111-1111"),
("Test2", "Test2", "456", 11211, "b", "222-222-2222"); 


create table if not exists superhumanorgbridge(
	superHumanOrgId int not null auto_increment,
	superId int not null,
    orgId int not null,
    primary key (superHumanOrgId, superId, orgId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE superhumanorgbridge ADD CONSTRAINT fk_superhumanorgbridge_super FOREIGN KEY (superId) REFERENCES superhuman(superId) ON DELETE NO ACTION;
ALTER TABLE superhumanorgbridge ADD CONSTRAINT fk_superhumanorgbridge_org FOREIGN KEY (orgId) REFERENCES superorg(orgId) ON DELETE NO ACTION;

insert into superhumanorgbridge (superId, orgId) values (1, 1);


create table if not exists sightinglocations (
	locId int not null auto_increment,
    locName varChar(50) not null,
    locDesc text,
    locStreetAddress varChar(100),
    zip int,
    lat double not null, 
	lon double not null,
    primary key (locId),
	foreign key (zip) references zipcitystate (zip)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into sightinglocations (locName, locDesc, locStreetAddress, zip, lat, lon) 
values ("LocSightingTest", "LocSightingTestDesc", "123 Over There Way", 10012, 00.000, -00.000);

create table if not exists sightingpic(
	picId int not null auto_increment,
    picTitle varChar(50),
    imgPath varChar(100) not null,
    userPic longblob,
    primary key (picId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


create table if not exists sighting(
	sightingId int not null auto_increment,
	recordedDateTime DateTime not null,
    locId int,
    picId int,
	primary key (sightingId),
	foreign key (locId) references sightinglocations (locId),
    foreign key (picId) references sightingpic (picId)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into sighting (recordedDateTime, locId) values ('1000-01-01 00:00:00', 1);

create table if not exists supersightingbridge(
	superSightingId int not null auto_increment,
	superId int not null,
    sightingId int not null,
    primary key (superSightingId, superId, sightingId)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE supersightingbridge ADD CONSTRAINT fk_supersightingbridge_super FOREIGN KEY (superId) REFERENCES superhuman(superId) ON DELETE NO ACTION;
ALTER TABLE supersightingbridge ADD CONSTRAINT fk_supersightingbridge_sighting FOREIGN KEY (sightingId) REFERENCES sighting(sightingId) ON DELETE NO ACTION;

insert into supersightingbridge (superId, sightingId) values (1,1);