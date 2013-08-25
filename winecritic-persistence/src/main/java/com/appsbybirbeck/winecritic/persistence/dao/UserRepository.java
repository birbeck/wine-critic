package com.appsbybirbeck.winecritic.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;

/**
 * Repository for User entities.
 *
 * @author Stewart Gateley
 */
@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Long>
{

}
