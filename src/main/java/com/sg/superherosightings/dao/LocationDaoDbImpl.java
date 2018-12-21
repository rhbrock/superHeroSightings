/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperHuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Roger Brock
 */
public class LocationDaoDbImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    @Inject
    public LocationDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //PREPARED STATEMENTS
    private static final String SQL_INSERT_LOCATION
            = "insert into sightinglocations "
            + "(locName, locDesc, locStreetAddress, zip, lat, lon ) "
            + "values "
            + "(?,?,?,?,?,?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from sightinglocations where locId = ?";

    private static final String SQL_UPDATE_SIGHITNG_LOCID_TO_NULL
            = "update sighting set locId = null where locId = ?";

    private static final String SQL_EDIT_LOCATION
            = "update sightinglocations set locName = ?, locDesc = ?, "
            + "locStreetAddress = ?, zip = ?, lat = ?, lon = ? "
            + "where locId = ?";

    private static final String SQL_GET_ALL_LOCATIONS
            = "select * from sightinglocations";

    private static final String SQL_GET_LOCATION
            = "select * from sightinglocations where locId = ?";

    private static final String GET_LOCATIONS_BY_SUPERHUMAN
            = "select * from sightinglocations sl "
            + "join sighting sight on sight.locId = sl.locId "
            + "join supersightingbridge ssb on ssb.sightingId = sight.sightingId "
            + "join superhuman sh on sh.superId = ssb.superId "
            + "where sh.superId = ?";

    @Override
    public List<Location> getAllLoc() {
        return jdbcTemplate.query(SQL_GET_ALL_LOCATIONS,
                new LocationMapper());
    }

    @Override
    public Location create(Location loc) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                loc.getLocName(),
                loc.getLocDesc(),
                loc.getStreetAddress(),
                loc.getZip(),
                loc.getLat(),
                loc.getLon());

        loc.setLocId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Long.class));
        return loc;
    }

    @Override
    public Location read(Long locId) {

        String test = "test";

        try {
            return jdbcTemplate.queryForObject(SQL_GET_LOCATION,
                    new LocationMapper(), locId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Location update(Location loc) {
        jdbcTemplate.update(SQL_EDIT_LOCATION,
                loc.getLocName(),
                loc.getLocDesc(),
                loc.getStreetAddress(),
                loc.getZip(),
                loc.getLat(),
                loc.getLon(),
                loc.getLocId());

        return loc;
    }

    @Override
    public void delete(Long locId) {
        jdbcTemplate.update(SQL_UPDATE_SIGHITNG_LOCID_TO_NULL, locId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locId);
    }

    @Override
    public List<Location> getLocationListBySuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(GET_LOCATIONS_BY_SUPERHUMAN,
                new LocationMapper(),
                superHuman.getSuperId());
    }

    //MAPPING METHODS
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {

            Location loc = new Location();
            loc.setLocId(rs.getLong("locId"));
            loc.setLocName(rs.getString("locName"));
            loc.setLocDesc(rs.getString("locDesc"));
            loc.setStreetAddress(rs.getString("locStreetAddress"));
            loc.setZip(rs.getInt("zip"));
            loc.setLat(rs.getDouble("lat"));
            loc.setLon(rs.getDouble("lon"));

            return loc;
        }
    }
}
