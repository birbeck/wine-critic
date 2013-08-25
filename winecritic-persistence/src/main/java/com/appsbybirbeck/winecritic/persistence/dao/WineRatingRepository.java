package com.appsbybirbeck.winecritic.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsbybirbeck.winecritic.persistence.entity.WineRatingEntity;

/**
 * Repository for WineRating entities.
 *
 * @author Stewart Gateley
 */
@Repository
public interface WineRatingRepository
        extends JpaRepository<WineRatingEntity, Long>
{

    /**
     * Get a list of wine ratings by score.
     *
     * @param score the minimum score to return.
     * @return an ordered non-null List of {@link WineRatingEntity} with a score greater than or equal to the search
     *         score. The list ordered by score in ascending order.
     */
    @Query("from WineRatingEntity where score >= :score order by score asc")
    List<WineRatingEntity> findByRatingGreaterThanOrEqualTo(@Param("score") int score);

    /**
     * Get a list of wine ratings wine id.
     *
     * @param wineId the id of the wine.
     * @return a non-null List of {@link WineRatingEntity} for the provided wine id.
     */
    @Query("from WineRatingEntity where wineEntity.id = :wineId")
    List<WineRatingEntity> findByWineId(@Param("wineId") Long wineId);

    /**
     * Get a list of wine ratings by user id.
     *
     * @param userId the id of the user.
     * @return a non-null List of {@link WineRatingEntity} for the provided user id.
     */
    @Query("from WineRatingEntity where userEntity.id = :userId")
    List<WineRatingEntity> findByUserId(@Param("userId") Long userId);

    /**
     * Get a list of wine ratings by wine maker.
     *
     * @param winery the name of the winery.
     * @return a non-null List of {@link WineRatingEntity} for the provided winery.
     */
    @Query("from WineRatingEntity where wineEntity.winery = :winery")
    List<WineRatingEntity> findByWinery(@Param("winery") String winery);

    /**
     * Get a list of wine ratings by varietal.
     *
     * @param varietal the name of the varietal.
     * @return a non-null List of {@link WineRatingEntity} for the provided varietal.
     */
    @Query("from WineRatingEntity where wineEntity.varietal = :varietal")
    List<WineRatingEntity> findByVarietal(@Param("varietal") String varietal);

}
