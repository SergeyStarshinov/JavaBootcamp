package org.s21.datasource.model;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private final ConcurrentHashMap<UUID, GameBoardData> store;

    public Storage() {
        store = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<UUID, GameBoardData> getStore() {
        return store;
    }

}
