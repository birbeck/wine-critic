package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:winecritic-persistence-context-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave_ValidUser() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUsername("Test User");
        userRepository.save(entity);

        entity = userRepository.findOne(entity.getId());
        Assert.assertEquals(entity.getUsername(), "Test User");
    }

    @Test(expectedExceptions = org.springframework.dao.DataAccessException.class)
    public void testUsername_Required() throws Exception {
        final UserEntity entity = new UserEntity();
        entity.setUsername(null);
        userRepository.save(entity);
    }

    @Test(enabled = false)
    public void testGetRatings() throws Exception {
        final UserEntity entity = userRepository.findOne(1L);
        Assert.assertEquals(1, entity.getRatings().size());
    }

}
