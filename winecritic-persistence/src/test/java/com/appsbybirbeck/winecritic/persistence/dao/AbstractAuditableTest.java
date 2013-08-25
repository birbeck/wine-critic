package com.appsbybirbeck.winecritic.persistence.dao;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;

/**
 * Tests {@link com.appsbybirbeck.winecritic.persistence.entity.AbstractAuditable}
 *
 * @author Stewart Gateley
 */
@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
public class AbstractAuditableTest
        extends AbstractTestNGSpringContextTests
{

    @Autowired
    private WineRepository wineRepository;

    @Transactional
    @AfterMethod
    public void tearDown() throws Exception {
        wineRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testCreate() throws Exception {
        final WineEntity entity = createWine("Test Wine", "Test Winery");
        Assert.assertTrue(entity.isNew());
        Assert.assertNull(entity.getId());
        Assert.assertNull(entity.getCreatedDate());
        Assert.assertNull(entity.getLastModifiedDate());

        wineRepository.save(entity);
        Assert.assertFalse(entity.isNew());
        Assert.assertNotNull(entity.getId());
        Assert.assertNotNull(entity.getCreatedDate());
        Assert.assertNotNull(entity.getLastModifiedDate());
    }

    @Transactional
    @Test
    public void testUpdate() throws Exception {
        WineEntity entity = createWine("Test Wine", "Test Winery");
        wineRepository.save(entity);

        final Long id = entity.getId();
        final DateTime createdDate = entity.getCreatedDate();
        final DateTime lastModifiedDate = entity.getLastModifiedDate();

        entity.setName("FOO");
        entity = wineRepository.save(entity);
        Assert.assertEquals(entity.getId(), id);
        Assert.assertEquals(entity.getCreatedDate(), createdDate);
        Assert.assertTrue(entity.getLastModifiedDate().isAfter(lastModifiedDate));
    }

    private WineEntity createWine(final String name, final String winery) {
        final WineEntity entity = new WineEntity();
        entity.setName(name);
        entity.setWinery(winery);
        return entity;
    }

}
