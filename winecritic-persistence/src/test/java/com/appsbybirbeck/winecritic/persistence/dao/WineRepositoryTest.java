package com.appsbybirbeck.winecritic.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.appsbybirbeck.winecritic.api.WineType;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;

/**
 * Tests {@link WineRepository}
 *
 * @author Stewart Gateley
 */
@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
public class WineRepositoryTest
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
    public void testSave() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName("Test Wine");
        entity.setWinery("Test Winery");
        entity.setVarietal("Test Varietal");
        entity.setType(WineType.RED);
        entity.setAppellation("Test Appellation");
        entity.setPrice(new BigDecimal("19.99"));
        entity.setVintage(1995);
        wineRepository.save(entity);

        final WineEntity retrieved = wineRepository.findOne(entity.getId());
        Assert.assertNotSame(retrieved, entity);
        Assert.assertEquals(retrieved, entity);
        Assert.assertNotNull(retrieved.getId());
        Assert.assertEquals(retrieved.getName(), "Test Wine");
        Assert.assertEquals(retrieved.getWinery(), "Test Winery");
        Assert.assertEquals(retrieved.getVarietal(), "Test Varietal");
        Assert.assertEquals(retrieved.getType(), WineType.RED);
        Assert.assertEquals(retrieved.getAppellation(), "Test Appellation");
        Assert.assertEquals(retrieved.getPrice(), new BigDecimal("19.99"));
        Assert.assertEquals(retrieved.getVintage(), (Integer) 1995);
    }

    @Transactional
    @Test
    public void testSave_OnlyRequiredFields() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName("Test Wine");
        entity.setWinery("Test Winery");
        wineRepository.save(entity);
    }

    @Transactional
    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testSave_NullNameThrowsException() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName(null);
        entity.setWinery("Test");
        wineRepository.save(entity);
    }

    @Transactional
    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testSave_NullWineryThrowsException() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName("Test");
        entity.setWinery(null);
        wineRepository.save(entity);
    }

    @Transactional
    @Test
    public void testFindByNameLike() throws Exception {
        createWine("Boulder Bluff Pinot Noir", "Adelsheim", "Pinot Noir");

        List<WineEntity> list = wineRepository.findByNameLike("Boulder Bluff Pinot Noir");
        Assert.assertEquals(list.size(), 1);

        // Test full name
        list = wineRepository.findByNameLike("Boulder Bluff Pinot Noir");
        Assert.assertEquals(list.size(), 1);

        // Test starts with
        list = wineRepository.findByNameLike("Boulder Bluff");
        Assert.assertEquals(list.size(), 1);

        // Test ends with
        list = wineRepository.findByNameLike("Pinot Noir");
        Assert.assertEquals(list.size(), 1);

        // Test middle
        list = wineRepository.findByNameLike("Bluff Pinot");
        Assert.assertEquals(list.size(), 1);

        // Test lowercase
        list = wineRepository.findByNameLike("boulder bluff");
        Assert.assertEquals(list.size(), 1);

        // Test uppercase
        list = wineRepository.findByNameLike("BOULDER BLUFF");
        Assert.assertEquals(list.size(), 1);

        // Test trim
        list = wineRepository.findByNameLike("   Pinot Noir   ");
        Assert.assertEquals(list.size(), 1);

        // Test no match
        list = wineRepository.findByNameLike("Pinto");
        Assert.assertTrue(list.isEmpty());
    }

    @Transactional
    @Test
    public void testFindByWineryLike() throws Exception {
        createWine("Boulder Bluff Pinot Noir", "Adelsheim", "Pinot Noir");

        // Test full name
        List<WineEntity> list = wineRepository.findByWineryLike("Adelsheim");
        Assert.assertEquals(list.size(), 1);

        // Test starts with
        list = wineRepository.findByWineryLike("Adel");
        Assert.assertEquals(list.size(), 1);

        // Test ends with
        list = wineRepository.findByWineryLike("sheim");
        Assert.assertEquals(list.size(), 1);

        // Test middle
        list = wineRepository.findByWineryLike("elshe");
        Assert.assertEquals(list.size(), 1);

        // Test lowercase
        list = wineRepository.findByWineryLike("adelsheim");
        Assert.assertEquals(list.size(), 1);

        // Test uppercase
        list = wineRepository.findByWineryLike("ADELSHEIM");
        Assert.assertEquals(list.size(), 1);

        // Test trim
        list = wineRepository.findByWineryLike("   Adelsheim   ");
        Assert.assertEquals(list.size(), 1);

        // Test no match
        list = wineRepository.findByWineryLike("Adelshine");
        Assert.assertTrue(list.isEmpty());
    }

    @Transactional
    @Test
    public void testFindByVarietalLike() throws Exception {
        createWine("Boulder Bluff Pinot Noir", "Adelsheim", "Pinot Noir");

        // Test full name
        List<WineEntity> list = wineRepository.findByVarietalLike("Pinot Noir");
        Assert.assertEquals(list.size(), 1);

        // Test starts with
        list = wineRepository.findByVarietalLike("Pinot");
        Assert.assertEquals(list.size(), 1);

        // Test ends with
        list = wineRepository.findByVarietalLike("Noir");
        Assert.assertEquals(list.size(), 1);

        // Test middle
        list = wineRepository.findByVarietalLike("not");
        Assert.assertEquals(list.size(), 1);

        // Test lowercase
        list = wineRepository.findByVarietalLike("pinot noir");
        Assert.assertEquals(list.size(), 1);

        // Test uppercase
        list = wineRepository.findByVarietalLike("PINOT NOIR");
        Assert.assertEquals(list.size(), 1);

        // Test trim
        list = wineRepository.findByVarietalLike("   Pinot Noir   ");
        Assert.assertEquals(list.size(), 1);

        // Test no match
        list = wineRepository.findByVarietalLike("Pinto");
        Assert.assertTrue(list.isEmpty());
    }

    private WineEntity createWine(final String name, final String winery, final String varietal) {
        final WineEntity wineEntity = new WineEntity();
        wineEntity.setName(name);
        wineEntity.setWinery(winery);
        wineEntity.setVarietal(varietal);
        wineRepository.save(wineEntity);
        logger.info(wineEntity.toString());
        return wineEntity;
    }

}
