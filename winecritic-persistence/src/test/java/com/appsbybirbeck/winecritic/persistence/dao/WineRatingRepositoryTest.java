package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;
import com.appsbybirbeck.winecritic.persistence.entity.WineRatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class WineRatingRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = -1L;
    private static final String WINERY = "Adelsheim";

    @Autowired
    private WineRatingRepository wineRatingRepository;

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private UserRepository userRepository;

    //@Mock
    private WineEntity wineEntity;

    //@Mock
    private UserEntity userEntity;

    @BeforeClass
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        //when(wineEntity.getId()).thenReturn(1L) ;
        //when(userEntity.getId()).thenReturn(1L);
        wineEntity = wineRepository.findOne(1L);
        userEntity = userRepository.findOne(1L);
    }

    @Test()
    public void testSave_ValidRating() throws Exception {
        WineRatingEntity entity = new WineRatingEntity();
        entity.setRating(1);
        entity.setWineEntity(wineEntity);
        entity.setUserEntity(userEntity);
        entity.setReview("Test Review");
        wineRatingRepository.save(entity);

        entity = wineRatingRepository.findOne(entity.getId());
        Assert.assertEquals(entity.getRating(), 1);
        Assert.assertEquals(entity.getWine(), wineEntity);
        Assert.assertEquals(entity.getUser(), userEntity);
        Assert.assertEquals(entity.getReview(), "Test Review");
    }

    @Test
    public void testFindByWineId_ValidId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByWineId(VALID_ID);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testFindByWineId_InvalidId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByWineId(INVALID_ID);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByWineId_NullId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByWineId(null);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByUserId_ValidId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByUserId(VALID_ID);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testFindByUserId_InvalidId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByUserId(INVALID_ID);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByUserId_NullId() throws Exception {
        final List<WineRatingEntity> list = wineRatingRepository.findByUserId(null);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByWinery_ValidWinery() throws Exception {
        List<WineRatingEntity> list = wineRatingRepository.findByWinery(WINERY);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testFindByWinery_InvalidWinery() throws Exception {
        List<WineRatingEntity> list = wineRatingRepository.findByWinery("Foo");
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByWinery_NullWinery() throws Exception {
        List<WineRatingEntity> list = wineRatingRepository.findByWinery(null);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testCreateRating_UnsavedWineThrowsException() {
        final WineRatingEntity entity = new WineRatingEntity();
        entity.setRating(1);
        entity.setWineEntity(new WineEntity());
        entity.setUserEntity(userEntity);
        wineRatingRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testCreateRating_UnsavedUserThrowsException() {
        final WineRatingEntity entity = new WineRatingEntity();
        entity.setRating(1);
        entity.setWineEntity(wineEntity);
        entity.setUserEntity(new UserEntity());
        wineRatingRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testCreateRating_NullWineryThrowsException() {
        final WineRatingEntity entity = new WineRatingEntity();
        entity.setRating(1);
        entity.setWineEntity(null);
        entity.setUserEntity(userEntity);
        wineRatingRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testCreateRating_NullUserThrowsException() {
        final WineRatingEntity entity = new WineRatingEntity();
        entity.setRating(1);
        entity.setWineEntity(wineEntity);
        entity.setUserEntity(null);
        wineRatingRepository.save(entity);
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class, enabled = false)
    public void testCreateRating_NullRatingThrowsException() {
        final WineRatingEntity entity = new WineRatingEntity();
        entity.setWineEntity(wineEntity);
        entity.setUserEntity(userEntity);
        wineRatingRepository.save(entity);
    }

}
