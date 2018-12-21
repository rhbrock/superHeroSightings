use superhero;

select * from zipcitystate;

select * from superhuman;

select * from superorg;

select * from superhumanorgbridge;

select * from sighting;

select * from sightinglocations;

select * from supersightingbridge;



select * from supersightingbridge where superSightingId > 10 order by superSightingId ;

select sl.*, ifnull(locDesc, 'no description'), sh.*, s.recordedDateTime from sightinglocations sl
join sighting s on s.locId = sl.locId
join supersightingbridge ssb on ssb.sightingId = s.sightingId
join superhuman sh on sh.superId = ssb.superId
where ssb.superSightingId <=10 order by superSightingId;

delete from supersightingbridge where superId = 3;

update sighting set locId = null where locId = 1;
select * from sighting;
/*


select o.orgName, z.city, z.stateId, z.zip from
superorg o join zipcitystate z on z.zip = o.zip
where o.orgName = "Test";  

select o.orgName, z.city, z.stateId, z.zip from
superorg o join zipcitystate z on z.zip = o.zip
where o.orgName = "Test2";  

select l.locName, z.city, z.stateId, z.zip from
sightinglocations l join zipcitystate z on z.zip = l.zip
where l.locName = "LocSightingTest";  

select su.superHumanName, s.recordedDateTime, l.locName, z.city, z.stateId, z.zip from
sightinglocations l 
join zipcitystate z on z.zip = l.zip
join sighting s on s.locId = l.locId
join superhuman su on su.superId = s.superId
where l.locName = "LocSightingTest";  
*/




