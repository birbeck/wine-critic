package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineRepository extends JpaRepository<WineEntity, Long> {

    @Query("from WineEntity where lower(name) like concat('%', lower(:name), '%')")
    List<WineEntity> findByNameLike(@Param("name") String name);

    @Query("from WineEntity where lower(winery) like concat('%', lower(:winery), '%')")
    List<WineEntity> findByWineryLike(@Param("winery") String winery);

    @Query("from WineEntity where lower(varietal) like concat('%', lower(:varietal), '%')")
    List<WineEntity> findByVarietalLike(@Param("varietal") String varietal);

}
