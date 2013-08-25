package com.appsbybirbeck.winecritic.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;
import com.appsbybirbeck.winecritic.persistence.entity.WineRatingEntity;

/**
 * Tests {@link WineRatingRepository}
 *
 * @author Stewart Gateley
 */
@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
public class WineRatingRepositoryTest
        extends AbstractTestNGSpringContextTests
{

    private static final Logger logger = LoggerFactory.getLogger(WineRatingRepositoryTest.class);

    @Autowired
    private WineRatingRepository wineRatingRepository;

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private UserRepository userRepository;

    private WineEntity wineEntity;

    private UserEntity userEntity;

    @Transactional
    @BeforeClass
    public void setUpClass() throws Exception {
        wineEntity = new WineEntity();
        wineEntity.setName("Test Wine");
        wineEntity.setWinery("Test Winery");
        wineEntity.setVarietal("Test Varietal");
        wineRepository.save(wineEntity);

        userEntity = new UserEntity();
        userEntity.setUsername("Test User");
        userRepository.save(userEntity);
    }

    @Transactional
    @AfterMethod
    public void tearDown() throws Exception {
        wineRatingRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testSave() throws Exception {
        final WineRatingEntity entity = createWineRating(5, "Amazing!!!");

        final WineRatingEntity retrieved = wineRatingRepository.findOne(entity.getId());
        Assert.assertNotSame(retrieved, entity);
        Assert.assertEquals(retrieved, entity);
        Assert.assertEquals(retrieved.getScore(), 5);
        Assert.assertEquals(retrieved.getReview(), "Amazing!!!");
        Assert.assertEquals(retrieved.getWine(), wineEntity);
        Assert.assertEquals(retrieved.getUser(), userEntity);
    }

    @Transactional
    @Test
    public void testFindByRatingGreaterThanOrEqualTo() throws Exception {
        createWineRating(1, "Really bad");
        createWineRating(3, "Mediocore");
        createWineRating(5, "Awesome");

        List<WineRatingEntity> wineRatingEntities = wineRatingRepository.findByRatingGreaterThanOrEqualTo(1);
        Assert.assertEquals(wineRatingEntities.size(), 3);

        wineRatingEntities = wineRatingRepository.findByRatingGreaterThanOrEqualTo(4);
        Assert.assertEquals(wineRatingEntities.size(), 1);

        wineRatingEntities = wineRatingRepository.findByRatingGreaterThanOrEqualTo(6);
        Assert.assertEquals(wineRatingEntities.size(), 0);
    }

    @Transactional
    @Test
    public void testFindByWineId() throws Exception {
        createWineRating(5, "Awesome");

        List<WineRatingEntity> wineRatingEntities = wineRatingRepository.findByWineId(wineEntity.getId());
        Assert.assertEquals(1, wineRatingEntities.size());

        wineRatingEntities = wineRatingRepository.findByWineId(wineEntity.getId() + 1);
        Assert.assertNotNull(wineRatingEntities);
        Assert.assertTrue(wineRatingEntities.isEmpty());
    }

    @Transactional
    @Test
    public void testFindByUserId() throws Exception {
        createWineRating(5, "Awesome");

        List<WineRatingEntity> wineRatingEntities = wineRatingRepository.findByUserId(userEntity.getId());
        Assert.assertEquals(1, wineRatingEntities.size());

        wineRatingEntities = wineRatingRepository.findByWineId(userEntity.getId() + 1);
        Assert.assertNotNull(wineRatingEntities);
        Assert.assertTrue(wineRatingEntities.isEmpty());
    }

    @Transactional
    @Test
    public void testFindByWinery() throws Exception {
        createWineRating(5, "Awesome");

        List<WineRatingEntity> wineRatingEntities = wineRatingRepository.findByWinery("Test Winery");
        Assert.assertEquals(1, wineRatingEntities.size());

        wineRatingEntities = wineRatingRepository.findByWinery("Unknown Winery");
        Assert.assertNotNull(wineRatingEntities);
        Assert.assertTrue(wineRatingEntities.isEmpty());
    }

    @Transactional
    @Test
    public void testFindByVarietal() throws Exception {
        createWineRating(5, "Awesome");

        List<WineRatingEntity> wineRatingEntities = wineRatingRepository.findByVarietal("Test Varietal");
        Assert.assertEquals(1, wineRatingEntities.size());

        wineRatingEntities = wineRatingRepository.findByWinery("Unknown Varietal");
        Assert.assertNotNull(wineRatingEntities);
        Assert.assertTrue(wineRatingEntities.isEmpty());
    }

    private WineRatingEntity createWineRating(final int score, final String review) {
        final WineRatingEntity wineRatingEntity = new WineRatingEntity();
        wineRatingEntity.setScore(score);
        wineRatingEntity.setReview(review);
        wineRatingEntity.setUserEntity(userEntity);
        wineRatingEntity.setWineEntity(wineEntity);
        wineRatingRepository.save(wineRatingEntity);
        logger.info(wineRatingEntity.toString());
        return wineRatingEntity;
    }

}
