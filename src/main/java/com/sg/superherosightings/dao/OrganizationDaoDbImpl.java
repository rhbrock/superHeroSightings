/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    @Inject
    public OrganizationDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //PREPARED STATEMENTS
    private static final String SQL_INSERT_ORG
            = "insert into superorg "
            + "(orgName, orgDesc, orgStreetAddress, "
            + "zip, orgEmail, orgPhone) "
            + "values "
            + "(?,?,?,?,?,?)";

    private static final String SQL_DELETE_ORG
            = "delete from superOrg where orgId = ?";

    private static final String SQL_DELETE_FROM_SUPERHUMANORGBRIDGE
            = "delete from superhumanorgbridge where orgId = ?";

    private static final String SQL_EDIT_ORG
            = "update superorg set orgName = ?, orgDesc = ?, "
            + "orgStreetAddress = ?, zip = ?, orgEmail = ?, "
            + "orgPhone = ? "
            + "where orgId = ?";

    private static final String SQL_GET_ALL_FROM_ORG
            = "select * from superorg";

    private static final String SQL_GET_ORG
            = "select * from superorg where orgId = ?";

    private static final String SQL_GET_SUPERORG_BY_SUPERHUMAN 
            = "select * from superorg so "
            + "join superhumanorgbridge shob on shob.orgId = so.orgId "
            + "where superId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addSuperOrg(Organization org) {

        jdbcTemplate.update(SQL_INSERT_ORG,
                org.getName(),
                org.getDescription(),
                org.getStreetAddress(),
                org.getZip(),
                org.getEmail(),
                org.getPhoneNumber());

        org.setOrgId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Long.class));

        return org;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperOrg(Long id) {
        jdbcTemplate.update(SQL_DELETE_FROM_SUPERHUMANORGBRIDGE, id);
        jdbcTemplate.update(SQL_DELETE_ORG, id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization editSuperOrg(Organization org) {
        jdbcTemplate.update(SQL_EDIT_ORG,
                org.getName(),
                org.getDescription(),
                org.getStreetAddress(),
                org.getZip(),
                org.getEmail(),
                org.getPhoneNumber(),
                org.getOrgId());

        return org;
    }

    @Override
    public List<Organization> getAllSuperOrgsAsList() {
        List<Organization> org
                = jdbcTemplate.query(SQL_GET_ALL_FROM_ORG,
                        new SuperOrgMapper());

        return org;
    }

    @Override
    public Organization getSuperOrg(Long id) {
        try {
            Organization org
                    = jdbcTemplate.queryForObject(SQL_GET_ORG,
                            new SuperOrgMapper(),
                            id);

            return org;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getSuperOrgBySuperHuman(SuperHuman superHuman) {

        List<Organization> orgList
                = jdbcTemplate.query(SQL_GET_SUPERORG_BY_SUPERHUMAN,
                        new SuperOrgMapper(),
                        superHuman.getSuperId());
        return orgList;
    }

    //MAPPING METHODS
    private static final class SuperOrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {

            Organization org = new Organization();
            org.setOrgId(rs.getLong("orgId"));
            org.setName(rs.getString("orgName"));
            org.setDescription(rs.getString("orgDesc"));
            org.setStreetAddress(rs.getString("orgStreetAddress"));
            org.setZip(rs.getInt("zip"));
            org.setEmail(rs.getString("orgEmail"));
            org.setPhoneNumber(rs.getString("orgPhone"));

            return org;
        }
    }

}
