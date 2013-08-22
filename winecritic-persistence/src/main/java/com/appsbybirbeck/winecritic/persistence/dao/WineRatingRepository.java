package com.appsbybirbeck.winecritic.persistence.dao;

import com.appsbybirbeck.winecritic.persistence.entity.WineRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineRatingRepository extends JpaRepository<WineRatingEntity, Long> {

    @Query("from WineRatingEntity where rating >= :rating")
    List<WineRatingEntity> findByRatingGreatherThanOrEqualTo(@Param("rating") int rating);

    @Query("from WineRatingEntity where wineEntity.id = :wineId")
    List<WineRatingEntity> findByWineId(@Param("wineId") Long wineId);

    @Query("from WineRatingEntity where wineEntity.winery = :winery")
    List<WineRatingEntity> findByWinery(@Param("winery") String winery);

    @Query("from WineRatingEntity where wineEntity.varietal = :varietal")
    List<WineRatingEntity> findByVarietal(@Param("varietal") String varietal);

    @Query("from WineRatingEntity where userEntity.id = :userId")
    List<WineRatingEntity> findByUserId(@Param("userId") Long userId);

}
