package com.appsbybirbeck.winecritic.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.HibernateException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appsbybirbeck.winecritic.api.Wine;
import com.appsbybirbeck.winecritic.api.WineService;
import com.appsbybirbeck.winecritic.api.exceptions.WineNotFoundException;
import com.appsbybirbeck.winecritic.api.exceptions.WinecriticPersistenceException;
import com.appsbybirbeck.winecritic.persistence.dao.WineRepository;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;

public class WineServiceImplTest {

    private WineService wineService;

    @Mock
    private WineRepository wineRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wineService = new WineServiceImpl(wineRepository);
    }

    @Test
    public void testCreateWine() throws Exception {
        final String name = "Test Wine";
        final String winery = "Test Winey";

        final WineEntity wineEntity = mock(WineEntity.class);
        when(wineEntity.getId()).thenReturn(1L);
        when(wineEntity.getName()).thenReturn(name);
        when(wineEntity.getWinery()).thenReturn(winery);
        when(wineRepository.save(any(WineEntity.class))).thenReturn(wineEntity);

        final Wine wine = createWine(name, winery);
        Assert.assertNotNull(wine.getId());
        Assert.assertTrue(wine.getId() > 0);
        Assert.assertEquals(wine.getName(), name);
        Assert.assertEquals(wine.getWinery(), winery);
    }

    @Test(expectedExceptions = WinecriticPersistenceException.class)
    public void testCreate_ThrowsPersistenceException() throws Exception {
        when(wineRepository.save(any(WineEntity.class))).thenThrow(HibernateException.class);

        createWine(null, null);
    }

    @Test
    public void testFindById() throws Exception {
        final WineEntity wineEntity = mock(WineEntity.class);
        when(wineRepository.findOne(anyLong())).thenReturn(wineEntity);

        Assert.assertNotNull(wineService.findById(1));
    }

    @Test(expectedExceptions = WineNotFoundException.class)
    public void testFindById_ThrowsNotFoundException() throws Exception {
        when(wineRepository.findOne(anyLong())).thenReturn(null);

        wineService.findById(999);
    }

    private Wine createWine(final String name, final String winery) throws Exception {
        final Wine wine = new Wine();
        wine.setName(name);
        wine.setWinery(winery);
        return wineService.createWine(wine);
    }
}
