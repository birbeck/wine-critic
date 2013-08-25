package com.appsbybirbeck.winecritic.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsbybirbeck.winecritic.persistence.entity.WineEntity;

/**
 * Repository for Wine entities.
 *
 * @author Stewart Gateley
 */
@Repository
public interface WineRepository
        extends JpaRepository<WineEntity, Long>
{

    /**
     * Get a list of wines by name. Wine names are case-insensitive and will match the search string anywhere in the
     * wine name.
     *
     * @param name the search term.
     * @return a non-null List of {@link WineEntity} matching the search term.
     */
    @Query("from WineEntity where lower(name) like concat('%', lower(trim(:name)), '%')")
    List<WineEntity> findByNameLike(@Param("name") String name);

    /**
     * Get a list of wines by wine maker. Winery names are case-insensitive and will match the search string anywhere in
     * the winery name.
     *
     * @param winery the search term.
     * @return a non-null List of {@link WineEntity} matching the search term.
     */
    @Query("from WineEntity where lower(winery) like concat('%', lower(trim(:winery)), '%')")
    List<WineEntity> findByWineryLike(@Param("winery") String winery);

    /**
     * Get a list of wines by varietal. Varietal names are case-insensitive and will match the search string anywhere in
     * the varietal name.
     *
     * @param varietal the search term.
     * @return a non-null List of {@link WineEntity} matching the search term.
     */
    @Query("from WineEntity where lower(varietal) like concat('%', lower(trim(:varietal)), '%')")
    List<WineEntity> findByVarietalLike(@Param("varietal") String varietal);

}
