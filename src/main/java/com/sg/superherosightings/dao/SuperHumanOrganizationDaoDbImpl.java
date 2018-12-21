/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
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
public class SuperHumanOrganizationDaoDbImpl implements SuperHumanOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperHumanOrganizationDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //PREPARED STATEMENTS
    public static final String CREATE = "insert into superhumanorgbridge "
            + "(superId, orgId) values (?,?)";

    public static final String READ = "select * from superhumanorgbridge "
            + "where superHumanOrgId = ?";

    public static final String UPDATE = "update superhumanorgbridge "
            + "set superId = ?, orgId = ? where superHumanOrgId = ?";

    public static final String DELETE = "delete from superhumanorgbridge "
            + "where superHumanOrgId = ?";

    public static final String BY_SUPERHUMAN = "select * from superhumanorgbridge "
            + "where superId = ?";

    public static final String BY_ORG = "select * from superhumanorgbridge "
            + "where orgId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHumanOrganization create(SuperHumanOrganization superHumanOrg) {

        Long superId = null;
        Long orgId = null;

        if (superHumanOrg.getSuperHuman() != null) {
            superId = superHumanOrg.getSuperHuman().getSuperId();
        }

        if (superHumanOrg.getOrganization() != null) {
            orgId = superHumanOrg.getOrganization().getOrgId();
        }

        jdbcTemplate.update(CREATE, superId, orgId);

        superHumanOrg.setId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class));

        return superHumanOrg;
    }

    @Override
    public SuperHumanOrganization read(Long id) {

        try {
            return jdbcTemplate.queryForObject(READ,
                    new SuperHumanOrgMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHumanOrganization update(SuperHumanOrganization superHumanOrg) {

        Long superId = null;
        Long orgId = null;

        if (superHumanOrg.getSuperHuman() != null) {
            superId = superHumanOrg.getSuperHuman().getSuperId();
        }

        if (superHumanOrg.getOrganization() != null) {
            orgId = superHumanOrg.getOrganization().getOrgId();
        }

        jdbcTemplate.update(UPDATE, superId, orgId, superHumanOrg.getId());

        return superHumanOrg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<SuperHumanOrganization> getSuperHumanOrganizationBySuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(BY_SUPERHUMAN,
                new SuperHumanOrgMapper(),
                superHuman.getSuperId());
    }

    @Override
    public List<SuperHumanOrganization> getSuperHumanOrganizationByOrganization(Organization org) {
        return jdbcTemplate.query(BY_ORG,
                new SuperHumanOrgMapper(),
                org.getOrgId());
    }

    //MAPPING METHODS
    private static final class SuperHumanOrgMapper implements RowMapper<SuperHumanOrganization> {

        @Override
        public SuperHumanOrganization mapRow(ResultSet rs, int i) throws SQLException {

            SuperHumanOrganization superHumanOrg = new SuperHumanOrganization();

            superHumanOrg.setId(rs.getLong("superHumanOrgId"));

            Long superId = rs.getLong("superId");

            if (superId != null) {
                SuperHuman superHuman = new SuperHuman();
                superHuman.setSuperId(superId);
                superHumanOrg.setSuperHuman(superHuman);
            }

            Long orgId = rs.getLong("orgId");
            if (orgId != null) {
                Organization org = new Organization();
                org.setOrgId(orgId);
                superHumanOrg.setOrganization(org);
            }

            return superHumanOrg;
        }
    }
}
