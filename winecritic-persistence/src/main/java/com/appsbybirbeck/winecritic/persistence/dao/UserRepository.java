package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
