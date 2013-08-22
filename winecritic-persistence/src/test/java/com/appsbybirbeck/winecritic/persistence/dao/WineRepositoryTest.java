package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.api.WineType;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class WineRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WineRepository wineRepository;

    @Test
    public void testSave_AllFields() throws Exception {
        WineEntity entity = new WineEntity();
        entity.setName("Test Wine");
        entity.setWinery("Test Winery");
        entity.setVarietal("Test Varietal");
        entity.setType(WineType.RED);
        entity.setAppellation("Test Appellation");
        entity.setPrice(new BigDecimal("19.99"));
        entity.setVintage(1995);
        wineRepository.save(entity);

        entity = wineRepository.findOne(entity.getId());
        Assert.assertEquals(entity.getName(), "Test Wine");
        Assert.assertEquals(entity.getWinery(), "Test Winery");
        Assert.assertEquals(entity.getVarietal(), "Test Varietal");
        Assert.assertEquals(entity.getType(), WineType.RED);
        Assert.assertEquals(entity.getAppellation(), "Test Appellation");
        Assert.assertEquals(entity.getPrice(), new BigDecimal("19.99"));
        Assert.assertEquals(entity.getVintage(), (Integer) 1995);
    }

    @Test
    public void testSave_OnlyRequiredFields() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName("Test Wine");
        entity.setWinery("Test Winery");
        wineRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testSave_NullNameThrowsException() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName(null);
        entity.setWinery("Test");
        wineRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testSave_NullWineryThrowsException() throws Exception {
        final WineEntity entity = new WineEntity();
        entity.setName("Test");
        entity.setWinery(null);
        wineRepository.save(entity);
    }

    @Test
    public void testFindByNameLike() throws Exception {
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

        // Test no match
        list = wineRepository.findByNameLike("Pinto");
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByWineryLike() throws Exception {
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

        // Test no match
        list = wineRepository.findByWineryLike("Adelshine");
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByVarietalLike() throws Exception {
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

        // Test no match
        list = wineRepository.findByVarietalLike("Pinto");
        Assert.assertTrue(list.isEmpty());
    }

    @Test(enabled = false)
    public void testGetRatings() throws Exception {
        final WineEntity entity = wineRepository.findOne(1L);
        Assert.assertEquals(1, entity.getRatings().size());
    }

}
