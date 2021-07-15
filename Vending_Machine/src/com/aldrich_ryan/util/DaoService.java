package com.aldrich_ryan.util;

import java.util.List;

public interface DaoService<T> {
    List<T> fetchAll();

    int addData(T object);

    int udpateData(T object);

    int deleteData(T object);
}
