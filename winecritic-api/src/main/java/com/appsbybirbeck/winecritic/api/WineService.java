package com.appsbybirbeck.winecritic.api;

import com.appsbybirbeck.winecritic.api.exceptions.WineNotFoundException;
import com.appsbybirbeck.winecritic.api.exceptions.WinecriticPersistenceException;

public interface WineService {

    Wine createWine(Wine wine) throws WinecriticPersistenceException;

    Wine findById(long id) throws WineNotFoundException;

}
