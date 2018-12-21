/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanSighting;
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
public class SuperHumanSightingDaoDbImpl implements SuperHumanSightingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperHumanSightingDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //PREPARED STATEMENTS
    public static final String CREATE = "insert into supersightingbridge "
            + "(superId, sightingId) values (?,?)";

    public static final String READ = "select * from supersightingbridge "
            + "where superSightingId = ?";

    public static final String UPDATE = "update supersightingbridge "
            + "set superId = ?, sightingId = ? where superSightingId = ?";

    public static final String DELETE = "delete from supersightingbridge "
            + "where superSightingId = ?";

    public static final String BY_SUPERHUMAN = "select * from supersightingbridge "
            + "where superId = ?";

    public static final String BY_SIGHTING = "select * from supersightingbridge "
            + "where sightingId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHumanSighting create(SuperHumanSighting superHumanSighting) {

        Long superId = null;
        Long sightingId = null;

        if (superHumanSighting.getSuperHuman() != null) {
            superId = superHumanSighting.getSuperHuman().getSuperId();
        }

        if (superHumanSighting.getSighting() != null) {
            sightingId = superHumanSighting.getSighting().getSightingId();
        }

        jdbcTemplate.update(CREATE, superId, sightingId);

        superHumanSighting.setId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class));

        return superHumanSighting;
    }

    @Override
    public SuperHumanSighting read(Long id) {
        try {
            return jdbcTemplate.queryForObject(READ,
                    new SuperHumanSightingMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHumanSighting update(SuperHumanSighting superHumanSighting) {

        Long superId = null;
        Long sightingId = null;

        if (superHumanSighting.getSuperHuman() != null) {
            superId = superHumanSighting.getSuperHuman().getSuperId();
        }

        if (superHumanSighting.getSighting() != null) {
            sightingId = superHumanSighting.getSighting().getSightingId();
        }

        jdbcTemplate.update(UPDATE, superId, sightingId, superHumanSighting.getId());

        return superHumanSighting;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<SuperHumanSighting> getSuperHumanSightingBySighting(Sighting sighting) {
        return jdbcTemplate.query(BY_SIGHTING,
                new SuperHumanSightingMapper(),
                sighting.getSightingId());
    }

    @Override
    public List<SuperHumanSighting> getSuperHumanSightingBySuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(BY_SUPERHUMAN,
                new SuperHumanSightingMapper(),
                superHuman.getSuperId());
    }

    //MAPPING METHODS
    private static final class SuperHumanSightingMapper implements RowMapper<SuperHumanSighting> {

        @Override
        public SuperHumanSighting mapRow(ResultSet rs, int i) throws SQLException {

            SuperHumanSighting superHumanSighting = new SuperHumanSighting();

            superHumanSighting.setId(rs.getLong("superSightingId"));

            Long superId = rs.getLong("superId");

            if (superId != null) {
                SuperHuman superHuman = new SuperHuman();
                superHuman.setSuperId(superId);
                superHumanSighting.setSuperHuman(superHuman);
            }

            Long sightingId = rs.getLong("sightingId");

            if (sightingId != null) {
                Sighting sighting = new Sighting();
                sighting.setSightingId(sightingId);
                superHumanSighting.setSighting(sighting);
            }

            return superHumanSighting;
        }
    }
}
