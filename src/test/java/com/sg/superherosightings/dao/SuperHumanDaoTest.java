/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.SuperHuman;
import java.util.List;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanDaoTest {

    // @Inject
    private SuperHumanDao superHumanDao;

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        superHumanDao = ctx.getBean("superHumanDao", SuperHumanDao.class);
    }

    /**
     * Test of create method, of class SuperHumanDao.
     */
    @Test
    public void testCreate() {

        SuperHuman x = createSuperHuman();

        assert x.getSuperId() != null;
        assert "TestName".equals(x.getName());
        assert "TestDesc".equals(x.getDescription());
        assert "TestPower".equals(x.getSuperpower());
        assert "TestDesc".equals(x.getDescription());
    }

    /**
     * Test of read method, of class SuperHumanDao.
     */
    @Test
    public void testRead() {
        SuperHuman x = createSuperHuman();

        assert x.getSuperId() != null;
        assert "TestName".equals(x.getName());
        assert "TestDesc".equals(x.getDescription());
        assert "TestPower".equals(x.getSuperpower());
        assert "TestDesc".equals(x.getDescription());
    }

    /**
     * Test of update method, of class SuperHumanDao.
     */
    @Test
    public void testUpdate() {

        SuperHuman x = createSuperHuman();

        assert x.getSuperId() != null;
        assert "TestName".equals(x.getName());
        assert "TestDesc".equals(x.getDescription());
        assert "TestPower".equals(x.getSuperpower());
        assert "TestDesc".equals(x.getDescription());

        x.setName("TestNameTwo");

        SuperHuman y = superHumanDao.update(x);

        assert "TestNameTwo".equals(y.getName());

    }

    /**
     * Test of delete method, of class SuperHumanDao.
     */
    @Test
    public void testDelete() {

        SuperHuman x = createSuperHuman();

        superHumanDao.delete(x.getSuperId());

        SuperHuman y = superHumanDao.read(x.getSuperId());

        assertNull(y);
    }

    /**
     * Test of getAll method, of class SuperHumanDao.
     */
    @Test
    public void testGetAll() {

        SuperHuman x = createSuperHuman();

        List<SuperHuman> list = superHumanDao.getAll();

        assert list != null;

    }

    /**
     * Test of getSuperHumansBySighting method, of class SuperHumanDao.
     */
    @Test
    public void testGetSuperHumansBySighting() {
    }

    /**
     * Test of getSuperHumansByOrganization method, of class SuperHumanDao.
     */
    @Test
    public void testGetSuperHumansByOrganization() {
    }

    private SuperHuman createSuperHuman() {
        SuperHuman superHuman = new SuperHuman();
        superHuman.setName("TestName");
        superHuman.setType("hero");
        superHuman.setDescription("TestDesc");
        superHuman.setSuperpower("TestPower");

        return superHumanDao.create(superHuman);
    }
}
