package com.appsbybirbeck.winecritic.persistence.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;

/**
 * Tests {@link UserRepository}
 *
 * @author Stewart Gateley
 */
@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
public class UserRepositoryTest
        extends AbstractTestNGSpringContextTests
{

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @AfterMethod
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testSave() throws Exception {
        final UserEntity userEntity = createUser("Test User");

        final UserEntity retrieved = userRepository.findOne(userEntity.getId());
        Assert.assertEquals(retrieved, userEntity);
        Assert.assertNotSame(retrieved, userEntity);
        Assert.assertEquals(retrieved.getUsername(), "Test User");
    }

    @Transactional
    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testSave_NullUsernameThrowsException() throws Exception {
        createUser(null);
    }

    @Transactional
    @Test
    public void testFindOne_InvalidIdIsNull() throws Exception {
        Assert.assertNull(userRepository.findOne(1L));
    }

    @Transactional
    @Test
    public void testFindOne_ValidId() throws Exception {
        Assert.assertNotNull(userRepository.findOne(createUser("Test User").getId()));
    }

    private UserEntity createUser(final String username) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userRepository.save(userEntity);
        logger.info("Created UserEntity " + userEntity);
        return userEntity;
    }
}
