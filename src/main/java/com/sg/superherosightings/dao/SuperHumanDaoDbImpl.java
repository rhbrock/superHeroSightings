/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
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
public class SuperHumanDaoDbImpl implements SuperHumanDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperHumanDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //PREPARED STATEMENTS
    private static final String SQL_CREATE_SUPERHUMAN
            = "insert into superhuman "
            + "(superHumanName, superHumanType, superPower, superHumanDesc) "
            + "values "
            + "(?,?,?,?)";

    private static final String SQL_DELETE_SUPERHUMAN
            = "delete from superhuman where superId = ?";

    private static final String SQL_DELETE_SUPERHUMANORGBRIDGE
            = "delete from superhumanorgbridge where superId = ?";

    private static final String SQL_DELETE_SUPERSIGHTINGBRIDGE
            = "delete from supersightingbridge where superId = ?";

    private static final String SQL_UPDATE_SUPERHUMAN
            = "update superhuman set superHumanName = ?, superHumanType = ?, "
            + "superPower = ?, superHumanDesc = ? where superId = ?";

    private static final String SQL_GET_ALL_SUPERHUMANS
            = "select * from superhuman";

    private static final String SQL_GET_SUPERHUMAN
            = "select * from superhuman where superId = ?";

    private static final String SQL_GET_SUPERHUMANS_BY_ORGANIZATION
            = "select * from superhuman sh "
            + "inner join superhumanorgbridge shob on shob.superId = sh.superId "
            + "where shob.orgId = ?";


//    private static final String SQL_GET_SUPERHUMANS_BY_SIGHTING
//            = "select * from supersightingbridge "
//            + "where sightingId = ?";
    private static final String SQL_GET_SUPERHUMANS_BY_SIGHTING
            = "select * from superhuman sh "
            + "inner join supersightingbridge ssb on ssb.superId = sh.superId "
            + "where ssb.sightingId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHuman create(SuperHuman superHuman) {

        jdbcTemplate.update(SQL_CREATE_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getType(),
                superHuman.getSuperpower(),
                superHuman.getDescription());

        superHuman.setSuperId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Long.class));

        return superHuman;
    }

    @Override
    public SuperHuman read(Long superId) {
        try {

            return jdbcTemplate.queryForObject(SQL_GET_SUPERHUMAN,
                    new SuperHumanMapper(),
                    superId);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHuman update(SuperHuman superHuman) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getType(),
                superHuman.getSuperpower(),
                superHuman.getDescription(),
                superHuman.getSuperId());

        return superHuman;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long superId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHUMANORGBRIDGE,
                superId);
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTINGBRIDGE,
                superId);
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN,
                superId);
    }

    @Override
    public List<SuperHuman> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_SUPERHUMANS,
                new SuperHumanMapper());
    }

    @Override
    public List<SuperHuman> getSuperHumansBySighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_GET_SUPERHUMANS_BY_SIGHTING,
                new SuperHumanMapper(),
                sighting.getSightingId());
    }

    @Override
    public List<SuperHuman> getSuperHumansByOrganization(Organization org) {
        return jdbcTemplate.query(SQL_GET_SUPERHUMANS_BY_ORGANIZATION,
                new SuperHumanMapper(),
                org.getOrgId());
    }

    //MAPPING METHODS
    private static final class SuperHumanMapper implements RowMapper<SuperHuman> {

        @Override
        public SuperHuman mapRow(ResultSet rs, int i) throws SQLException {

            SuperHuman x = new SuperHuman();
            x.setSuperId(rs.getLong("superId"));
            x.setName(rs.getString("superHumanName"));
            x.setType(rs.getString("superHumanType"));
            x.setSuperpower(rs.getString("superPower"));
            x.setDescription(rs.getString("superHumanDesc"));

            return x;
        }
    }
}
