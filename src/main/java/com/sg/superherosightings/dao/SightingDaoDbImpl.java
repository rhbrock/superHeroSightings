/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roger Brock
 */
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Inject
    public SightingDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    

    //PREPARED STATEMENTS
    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting "
            + "(recordedDateTime, locId) "
            + "values (?,?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightingId = ?";
    
    private static final String SQL_DELETE_SUPERSIGHTINGBRIDGE
        = "delete from supersightingbridge where sightingId = ?";

    private static final String SQL_EDIT_SIGHTING
            = "update sighting set recordedDateTime = ?, "
            + "locId = ? "
            + "where sightingId = ?";

    private static final String SQL_GET_ALL_SIGHTINGS
            = "select * from sighting";

    private static final String SQL_GET_SIGHTING
            = "select * from sighting where sightingId = ?";

    private static final String SQL_GET_SIGHTING_BY_SUPERHUMAN
            = "select * from supersightingbridge where superId = ?";

    private static final String SQL_GET_SIGHTING_AT_LOCATION
            = "select * from sighting s, recordedDateTime s "
            + "join sightinglocations sl on sl.locId = s.locId "
            + "where locId = ?";

//
//    private static final String SQL_SELECT_SUPERHUMANS_BY_SIGHTING_ID
//            = "select sh.superId, sh.superHumanName, sh.superHumanType, "
//            + "sh.superPower, sh.superHumanDesc from superhuman sh "
//            + "join supersightingbridge ssb on sh.superId = ssb.superId "
//            + "where ssb.sightingId = ?";
//
//    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
//            = "select l.locId, l.locName, l.locDesc, l.locStreetAddress, "
//            + "l.zip, l.lat, l.lon from sightinglocations l "
//            + "join sighting s on s.locId = l.locId "
//            + "where s.sightingId = ?";
//
//    private static final String SQL_SELECT_SIGHTINGLIST_BY_LOC_ID
//            = "select s.sightingId, s.recordedDateTime, sh.superHumanName, sl.locName "
//            + "from sighting s "
//            + "join sightinglocations sl on sl.locId = s.locId "
//            + "join supersightingbridge ssb on ssb.sightingId = s.sightingId "
//            + "join superhuman sh on sh.superId = ssb.superId "
//            + "where s.locId = ?";
    //
    //    private static final String SQL_SELECT_SIGHTINGPIC_BY_LOC_ID
    //            = "select p.picId, p.picTitle, p.imgPath, p.userPic "
    //            + "from sightingpic p join supersightingsbridge ssb on "
    //            + "p.picId = ssb.picId where ssb.locId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {

        Long locId = null;

        if (sighting.getLoc() != null) {
            locId = sighting.getLoc().getLocId();
        }

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDateTime().toString(),
                locId);
                //sighting.getLoc().getLocId());
//                sighting.getPic().getPicId());

        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Long.class));

        //      insertSuperSightingBridge(sighting);
        return sighting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(Long sightingId) {
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTINGBRIDGE, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting editSighting(Sighting sighting) {

        Long locId = null;

        if (sighting.getLoc() != null) {
            locId = sighting.getLoc().getLocId();
        }

        jdbcTemplate.update(SQL_EDIT_SIGHTING,
                sighting.getDateTime().toString(),
                //sighting.getSuperHuman().getSuperId(),
                locId,
                //sighting.getPic().getPicId(),
                sighting.getSightingId());

//        jdbcTemplate.update(SQL_DELETE_FROM_SUPER_SIGHTING_BRIDGE, sighting.getSightingId());
//        insertSuperSightingBridge(sighting);
        return sighting;
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_GET_ALL_SIGHTINGS, new SightingMapper());

        //  return associateSuperHumanAndLocationForSighting(sightingList);
        return sightingList;
    }

    @Override
    public Sighting getSighting(Long sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_GET_SIGHTING,
                    new SightingMapper(), sightingId);

//            sighting.setSuperHuman(getSuperHumanListAtSighting(sighting));
//            sighting.setLoc(findLocationForSighting(sighting));
            return sighting;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsBySuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_GET_SIGHTING_BY_SUPERHUMAN,
                new SightingMapper(),
                superHuman);
    }

    @Override
    public List<Sighting> getSightingsAtLocation(Location location) {
        return jdbcTemplate.query(SQL_GET_SIGHTING_AT_LOCATION,
                new SightingMapper(),
                location);
    }

//    @Override
//    public List<SuperHuman> getSuperHumanListAtSighting(Sighting sighting) {
//        return jdbcTemplate.query(SQL_SELECT_SUPERHUMANS_BY_SIGHTING_ID,
//                new SuperHumanMapper(),
//                sighting.getSightingId());
//    }
//
//    @Override
//    public List<Sighting> getSightingsAtLocation(Long locId) {
//        return jdbcTemplate.query(SQL_SELECT_SIGHTINGLIST_BY_LOC_ID,
//                new SightingMapper(),
//                locId);
//    }
    // HELPER METHODS
    // ==============
//    private void insertSuperSightingBridge(Sighting sighting) {
//        final int SIGHTINGID = sighting.getSightingId();
//        final List<SuperHuman> superHumanList = sighting.getSuperHuman();
//
//        superHumanList.forEach((x) -> {
//            jdbcTemplate.update(SQL_INSERT_SUPER_SIGHTING_BRIDGE,
//                    x.getSuperId(),
//                    SIGHTINGID);
//        });
//    }
    //    private List<SuperHuman> findSuperHumanForSighting(Sighting sighting) {
//        return jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID,
//                new SuperHumanMapper(),
//                sighting.getSightingId());
//    }
//    private Location findLocationForSighting(Sighting sighting) {
//        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
//                new LocationMapper(),
//                sighting.getSightingId());
//    }
//    private List<Sighting> associateSuperHumanAndLocationForSighting(List<Sighting> sightingList) {
//        for (Sighting x : sightingList) {
//            x.setSuperHuman(getSuperHumanListAtSighting(x));
//            x.setLoc(findLocationForSighting(x));
//        }
//        return sightingList;
//    }
//MAPPING METHODS
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {

            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getLong("sightingId"));
            sighting.setDateTime(rs.getTimestamp("recordedDateTime")
                    .toLocalDateTime());
            
            Long locId = rs.getLong("locId");
            
            if(locId != null){
                Location loc = new Location();
                loc.setLocId(locId);
                
                sighting.setLoc(loc);
            }

            return sighting;
        }
    }

}
