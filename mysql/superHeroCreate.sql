drop database if exists superhero;

create database superhero;

use superhero;

# import uszipsv1.4.csv (located in mysqlscripts folder) to zipcitystate table at go live for associating all zips collected
# to the associated city and state information
# many fields available in csv
#only importing the columns listed in the below table  

create table if not exists zipcitystate(
 zip int not null,
 city varChar(30) not null,
 stateId varChar(5) not null,
 primary key (zip)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

#insert statement only to populate zips so that script will execute on current tables with zip as foreign key.  Remove below insert at go-live before data import
#see above regarding importing live data.  
insert into zipcitystate (zip, city, stateId) values 
(10031, "New York", "NY"), 
(11211, "Brooklyn", "NY"), 
(30303, "Atlanta", "GA"), 
(10560, "North Salem", "NY"),
(30329, "Atlanta", "GA"),
(10033, "New York", "NY"),
(11201, "Brooklyn", "NY"),
(30308, "Atlanta", "GA"),
(10009, "New York", "NY"),
(30339, "Atlanta", "GA");

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

insert into superhuman (superHumanName,superHumanType,superPower,superHumanDesc) values
("Aquaman","hero","Breaths under water","saves stuff"),
("Batman","hero","Strength and unique weaponry", "saves stuff"),
("Beast", "hero", "Intelligence", "saves stuff"),
("Cyclops","hero", "Shoots lasers from eyes", "saves stuff"),
("Flash","hero", "Speed", "saves stuff"),
("Gambit","hero", "Manipulates kinetic energy","saves stuff"),
("Storm","hero", "Weather control", "saves stuff"),
("Superman","hero","Flight", "saves stuff"),
("Wolverine","hero", "Steel claws","saves stuff"),
("Wonder Women","hero", "Possesses Lasso of Truth","saves stuff"),
("Blob","villain", "Strength","destroys stuff"),
("Joker","villain", "Lethal chemical engineer","destroys stuff"),
("Lex Luthor","villain", "Intelligence", "destroys stuff"),
("Magneto","villain", "Power of magnetism", "destroys stuff"),
("Mystique","villain", "Shapeshifter", "destroys stuff"),
("Poison Ivy","villain", "Mind control", "destroys stuff"),
("Pyro","villain", "Controls fire", "destroys stuff"),
("Queen Bee","villain", "Bee like characteristics","destroys stuff"),
("Scarecrow","villain","Toxins that exploit fear","destroys stuff");

 #use as insert later after implementing pictures
/*
("Aquaman", "Breaths under water", 1, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\aquaman.png"),
("Batman", "Strength and unique weaponry", 2, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\batman.png"),
("Beast", "Intelligence", 3, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\beast.jpg"),
("Cyclops", "Shoots lasers from eyes", 4, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\cyclops.jpg"),
("Flash", "Speed", 5, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\her\flash.png"),
("Gambit", "Manipulates kinetic energy", 6, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\gambit.png"),
("Storm", "Weather control", 6, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\storm.png"),
("Superman", "Flight", 7, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\superman.png"),
("Wolverine", "Steel claws", 2, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\wolverine.jpg"),
("Wonder Women", "Possesses Lasso of Truth", 6, 1, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\hero\wonderwomen.jpg"),
("Blob", "Strength", 2, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\blob.png"),
("Joker", "Lethal chemical engineer", 3, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\joker.jpg"),
("Lex Luthor", "Intelligence", 3, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\lexLuthor.png"),
("Magneto", "Power of magnetism", 6, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\magneto.png"),
("Mystique", "Shapeshifter", 4, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\mystique.png"),
("Poison Ivy", "Mind control", 6, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\poisonIvy.png"),
("Pyro", "Controls fire", 6, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\pyro.png"),
("Queen Bee", "Bee like characteristics", 4, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\queenBee.jpg"),
("Scarecrow", "Toxins that exploit fear", 3, 2, "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\super\villain\scarecrow.jpg");
*/

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
    primary key (orgId)
    #foreign key (zip) references zipcitystate (zip)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into superorg (orgName, orgDesc, orgStreetAddress, zip, orgEmail, orgPhone) values
("Brother Hood of Mutants", "Defeat the x-men", "524 Broadway", 10031, "bhm@bhm.com", "111-111-1111"), 
("Injustice Gang", "Seeks power via power-sealing devices", "178 Broadway", 11211, "injgang@injgang.com", "222-222-2222"),
("Justice League", "Protect planet Earth", "152 Luckie St NW", 30303, "justiceleague@justiceleague.com", "333-333-3333"),
("X-Men", "Fight for peace and equality", "1407 Graymalkin Lane", 10560, "xmen@xmen.com", "444-444-4444");

 #use as insert later after implementing pictures
 /*
("Brother Hood of Mutants", "Defeat the x-men", "524 Broadway", 10012, "bhm@bhm.com", "111-111-1111", "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\org\brotherhoodOfMutants.jpg"), 
("Injustice Gang", "Seeks power via power-sealing devices", "178 Broadway", 11211, "injgang@injgang.com", "222-222-2222", "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\org\injusticeGang.jpg"),
("Justice League", "Protect planet Earth", "152 Luckie St NW", 30303, "justiceleague@justiceleague.com", "333-333-3333", "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\org\justiceLeague.png"),
("X-Men", "Fight for peace and equality", "1407 Graymalkin Lane", 10560, "xmen@xmen.com", "444-444-4444", "C:\Users\Roger\tsg_gitRepo\roger-brock-individual-work\SuperHeroSightings\photos\org\xmen.jpg");
*/

create table if not exists superhumanorgbridge(
	superHumanOrgId int not null auto_increment,
	superId int not null,
    orgId int not null,
    primary key (superHumanOrgId, superId, orgId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE superhumanorgbridge ADD CONSTRAINT fk_superhumanorgbridge_super FOREIGN KEY (superId) REFERENCES superhuman(superId) ON DELETE NO ACTION;
ALTER TABLE superhumanorgbridge ADD CONSTRAINT fk_superhumanorgbridge_org FOREIGN KEY (orgId) REFERENCES superorg(orgId) ON DELETE NO ACTION;


insert into superhumanorgbridge (superId, orgId) values
(1, 3), (2, 3), (3, 4), (4, 4), (5, 3), (6, 4), (7, 4), (8, 3), (9, 4), (10, 3), (11, 1), (12, 2), (13, 2), (14, 1), (15, 1), (16, 2), (17, 1), (18, 2), (19, 2);


create table if not exists sightinglocations (
	locId int not null auto_increment,
    locName varChar(50) not null,
    locDesc text,
    locStreetAddress varChar(100),
    zip int not null,
    lat double not null, 
	lon double not null,
    primary key (locId)
	#foreign key (zip) references zipcitystate (zip)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

insert into sightinglocations (locName, locDesc, locStreetAddress, zip, lat, lon) values 
("NY Building", "Upper Manhatten building.", "547W 142nd St", 10031, 40.824577, -73.951372),
("Guitar Center", "In Atlanta", "1485 Northeast Expy", 30329, 33.833200, -84.331504),
("Highbridge Park", "Upper Manhattan public park.", "2340 Amsterdam Ave", 10033, 40.845281, -73.932970),
("Brooklyn Borough Hall", null, "209 Joralemon St", 11201, 40.693833, -73.990323),
("Waterfront Commission of New York Harbor", "Upper bay", "100 Columbia St", 11201, 40.692721, -73.002354),
("Emory University", "In mid-town.  Atlanta hospital.", "550 Peachtree St NE", 30308, 33.768814, -84.386441),
("Atlanta City Hall", "Downtown", "68 Mitchell St", 30303, 40.824577, -73.951372),
("North Salem Police Department", null, "66 June Rd" ,10560, 41.331591, -73.59665),
("Tompkins Square Park", "Lower Manhattan", "132-152 E 7th St", 10009, 40.725802, -43.982953),
("Suntrust Park", "Braves stadium", "755 Battery Ave SE", 30339, 33.891088, -84.467756);   


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

insert into sighting (recordedDateTime, locId) values
('2018-01-01 1:00:00', 1),
('2018-02-01 2:00:00', 2),
('2018-03-01 3:00:00', 3),
('2018-04-01 4:00:00', 4),
('2018-05-01 5:00:00', 5),
('2018-06-01 6:00:00', 6),
('2018-07-01 7:00:00', 7),
('2018-08-01 8:00:00', 8),
('2018-09-01 9:00:00', 9),
('2018-09-02 10:00:00', 10),
('2018-09-03 1:03:30', 10),
('2018-09-04 2:03:40', 5),
('2018-09-05 3:00:30', 9),
('2018-09-05 4:00:00', 4),
('2018-09-06 5:00:50', 8),
('2018-09-07 6:00:00', 8),
('2018-09-08 7:00:00', 7),
('2018-09-10 8:05:30', 8),
('2018-09-11 9:00:00', 3);


create table if not exists supersightingbridge(
	superSightingId int not null auto_increment,
	superId int not null,
    sightingId int not null,
    primary key (superSightingId, superId, sightingId)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER TABLE supersightingbridge ADD CONSTRAINT fk_supersightingbridge_super FOREIGN KEY (superId) REFERENCES superhuman(superId) ON DELETE NO ACTION;
ALTER TABLE supersightingbridge ADD CONSTRAINT fk_supersightingbridge_sighting FOREIGN KEY (sightingId) REFERENCES sighting(sightingId) ON DELETE NO ACTION;

insert into supersightingbridge (superId, sightingId) values
(3, 1), (19, 1), (5, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (15, 7), (8, 8), (16, 9), (11, 10),  
(5, 11), (6, 12), (7, 13), (15, 13), (8,14), (16, 15), (11, 16), (3, 17), (19, 18), (5, 19);




