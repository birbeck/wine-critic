package com.appsbybirbeck.winecritic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appsbybirbeck.winecritic.api.Wine;
import com.appsbybirbeck.winecritic.api.WineService;
import com.appsbybirbeck.winecritic.api.exceptions.WineNotFoundException;
import com.appsbybirbeck.winecritic.api.exceptions.WinecriticPersistenceException;
import com.appsbybirbeck.winecritic.persistence.dao.WineRepository;
import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;

@Service
public class WineServiceImpl
        implements WineService
{

    private final WineRepository wineRepository;

    @Autowired
    public WineServiceImpl(final WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Wine createWine(final Wine wine) throws WinecriticPersistenceException {
        WineEntity entity = WineTranslator.toEntity(wine);
        try {
            entity = wineRepository.save(entity);
        } catch (final Exception e) {
            throw new WinecriticPersistenceException("Failed to create wine " + wine, e);
        }
        return WineTranslator.fromEntity(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Wine findById(final long id) throws WineNotFoundException {
        WineEntity entity = wineRepository.findOne(id);
        if (entity == null) {
            throw new WineNotFoundException("No wine found with id " + id);
        }
        return WineTranslator.fromEntity(entity);
    }

    static final class WineTranslator {
        static Wine fromEntity(final WineEntity entity) {
            final Wine wine = new Wine();
            if (entity.getId() != null) {
                wine.setId(entity.getId());
            }
            if (entity.getName() != null) {
                wine.setName(entity.getName());
            }
            if (entity.getWinery() != null) {
                wine.setWinery(entity.getWinery());
            }
            if (entity.getVarietal() != null) {
                wine.setVarietal(entity.getVarietal());
            }
            if (entity.getType() != null) {
                wine.setType(entity.getType());
            }
            if (entity.getAppellation() != null) {
                wine.setAppellation(entity.getAppellation());
            }
            if (entity.getVintage() != null) {
                wine.setVintage(entity.getVintage());
            }
            if (entity.getPrice() != null) {
                wine.setPrice(entity.getPrice());
            }
            return wine;
        }

        static WineEntity toEntity(final Wine wine) {
            final WineEntity entity = new WineEntity();
            if (wine.getName() != null) {
                entity.setName(wine.getName().trim());
            }
            if (wine.getWinery() != null) {
                entity.setWinery(wine.getWinery().trim());
            }
            if (wine.getVarietal() != null) {
                entity.setVarietal(wine.getVarietal());
            }
            if (wine.getType() != null) {
                entity.setType(wine.getType());
            }
            if (wine.getAppellation() != null) {
                entity.setAppellation(wine.getAppellation());
            }
            if (wine.getVintage() > 0) {
                entity.setVintage(wine.getVintage());
            }
            if (wine.getPrice() != null) {
                entity.setPrice(wine.getPrice());
            }
            return entity;
        }
    }

}
