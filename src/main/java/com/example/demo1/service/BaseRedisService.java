package com.example.demo1.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BaseRedisService {
    void set(String key, String value);
    void setTimeToLive(String key, int ttl);
    void hashSet(String key, String field, String value);
    boolean hashExists(String key, String field);
    Object get(String key);
    public Map<String, Object> getMap(String key);
    Object hashGet(String key, String field);
    List<Object> hashGetByFieldPrefix (String key , String fieldPrefix);
    Set<String> getFieldPrefixes(String key);
    void delete(String key);


}
