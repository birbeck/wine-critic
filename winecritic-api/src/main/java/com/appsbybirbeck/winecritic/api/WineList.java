package com.appsbybirbeck.winecritic.api;

import java.util.List;

/**
 * A collection of {@link Wine}.
 *
 * @author Stewart Gateley
 */
public interface WineList extends List<Wine> {

    /**
     * A collection of {@link Wine}.
     *
     * @return a list of {@link Wine}
     */
    List<Wine> wines();

}
